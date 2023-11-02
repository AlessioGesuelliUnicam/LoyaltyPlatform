package LoyaltyPlatform.Components.Coalition;

import LoyaltyPlatform.Components.FidelityProgram.FidelityProgram;
import LoyaltyPlatform.Components.FidelityProgram.FidelityProgramsController;
import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Db.Db;
import LoyaltyPlatform.Exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/coalitions")
public class CoalitionsController {

    private final Db db;

    @Autowired
    public CoalitionsController(Db db) {
        this.db = db;
    }

    /**
     * Returns the set of existing coalitions
     *
     * @return the set
     */
    @GetMapping("/getCoalitions")
    public HashSet<Coalition> getCoalitions() {
        return db.getCoalitionsTable().getRecords();
    }

    /**
     * Returns the coalition tho which the shop belongs
     *
     * @param shop the shop
     * @return the coalition of the shop if it has one
     * @throws NullPointerException if the given shop is null
     */
    @GetMapping("/getCoalitionOf")
    public Coalition getCoalitionOf(@RequestBody Shop shop) {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        HashSet<Coalition> coalitions = getCoalitions();
        for (Coalition coalition : coalitions) {
            if (coalition.hasMember(shop)) return coalition;
        }
        return null;
    }


    /**
     * Creates a new coalition starting from a shop
     *
     * @param shop the first shop of the coalition
     * @return true if the coalition has been created, false otherwise
     * @throws NullPointerException          if the given shop is null
     * @throws HasAlreadyACoalitionException if the given shop already belongs to a coalition
     */
    @PostMapping("/createCoalition")
    public boolean createCoalition(@RequestBody Shop shop) throws HasAlreadyACoalitionException {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        if (getCoalitionOf(shop) != null)
            throw new HasAlreadyACoalitionException("Can't create a coalition with a member of another coalition");
        CoalitionWithLeader coalition = new CoalitionWithLeader(shop);
        return db.getCoalitionsTable().add(coalition);
    }

    /**
     * Deletes an empty coalition
     *
     * @param coalition the coalition to delete
     * @return true if the coalition has been deleted, false otherwise
     * @throws NullPointerException       if the given coalition is null
     * @throws CoalitionNotEmptyException if the given coalition is not empty
     */
    @DeleteMapping("/deleteCoalition")
    public boolean deleteCoalition(@RequestBody Coalition coalition) throws CoalitionNotEmptyException {
        if (coalition == null) throw new NullPointerException("Field coalition can't be null");
        if (!coalition.isEmpty()) throw new CoalitionNotEmptyException("Can't delete a coalition with some members");
        FidelityProgramsController fidelityProgramsController = new FidelityProgramsController(db);
        FidelityProgram fidelityProgram = getFidelityProgramOf(coalition);
        fidelityProgramsController.deleteFidelityProgram(fidelityProgram);
        return db.getCoalitionsTable().delete(coalition);
    }


    /**
     * Returns the name of the given coalition
     *
     * @param coalition the coalition
     * @return the name of the coalition
     * @throws NullPointerException if the given coalition is null
     */
    @GetMapping("/getNameOf")
    public String getNameOf(@RequestBody Coalition coalition) {
        if (coalition == null) throw new NullPointerException("Field coalition can't be null");
        return coalition.getName();
    }

    /**
     * Sets the name of the given coalition
     *
     * @param coalition the coalition
     * @param name      the new name
     * @throws NullPointerException     if any of the fields is null
     * @throws IllegalArgumentException if the given name is blank
     */
    @PostMapping("/setNameOf")
    public void setNameOf(@RequestBody Coalition coalition, @RequestParam String name) {
        if (coalition == null) throw new NullPointerException("Field coalition can't be null");
        if (name == null) throw new NullPointerException("Field name can't be null");
        if (name.isEmpty()) throw new IllegalArgumentException("Field name can't be blank");
        coalition.setName(name);
    }


    /**
     * Returns the Fidelity Program of the given coalition
     *
     * @param coalition the coalition
     * @return the fidelity program
     * @throws NullPointerException if the given coalition is null
     */
    @GetMapping("/getFidelityProgramOf")
    public FidelityProgram getFidelityProgramOf(@RequestBody Coalition coalition) {
        if (coalition == null) throw new NullPointerException("Field coalition can't be null");
        return coalition.getFidelityProgram();
    }

    /**
     * Sets the fidelity program for the given coalition
     *
     * @param coalition       the coalition
     * @param fidelityProgram the new fidelity program
     * @throws NullPointerException if the given coalition is null or
     *                              if the fhe fidelityProgram is null and there is more than a member in the coalition
     */
    @PostMapping("/setFidelityProgramOf")
    public void setFidelityProgramOf(@RequestBody Coalition coalition, @RequestBody FidelityProgram fidelityProgram) {
        if (coalition == null) throw new NullPointerException("Field coalition can't be null");
        if (!coalition.hasOneMember() && fidelityProgram == null)
            throw new NullPointerException("Field fidelityProgram can't be null if the coalition has more than one member");
        coalition.setFidelityProgram(fidelityProgram);
    }


    /**
     * Sends a participation request from a requesting shop to a hosting coalition
     *
     * @param coalition the hosting coalition
     * @param shop      the requesting shop
     * @return true if the request has been sent, false otherwise
     */
    @PostMapping("/sendParticipationRequest")
    public boolean sendParticipationRequest(@RequestBody CoalitionWithLeader coalition, @RequestBody Shop shop) {
        if (coalition == null) throw new NullPointerException("Field coalition can't be null");
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        if (!coalition.hasFidelityProgram()) throw new IllegalArgumentException();
        return coalition.addToParticipationRequests(shop);
    }

    /**
     * Accepts a participation request from a requesting shop in a hosting coalition
     * Migrates a shop from a coalition to another
     *
     * @param shop      the requesting shop
     * @param coalition the hosting coalition
     * @return true if the request has been accepted and the shop migrated coalition, false otherwise
     * @throws NullPointerException                if any of the fields is null
     * @throws ShopNotInQueueException             if the shop wasn't found in the participation requests queue of the new coalition
     * @throws FidelityProgramNotProvidedException if the new coalition doesn't provide a fidelity program
     */
    @PostMapping("/acceptParticipationRequest")
    public boolean acceptParticipationRequest(@RequestBody CoalitionWithLeader coalition, @RequestBody Shop shop) throws CoalitionNotEmptyException, ShopNotInQueueException, FidelityProgramNotProvidedException {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        if (coalition == null) throw new NullPointerException("Field newCoalition can't be null");
        if (!coalition.hasFidelityProgram())
            throw new FidelityProgramNotProvidedException("Can't migrate a shop in a coalition who doesn't provide a fidelity program");
        Coalition oldCoalition = getCoalitionOf(shop);
        if (!addShopToCoalition(coalition, shop)) return false;
        if (!removeShopFromCoalition(coalition, shop)) return false;
        if (oldCoalition.isEmpty()) deleteCoalition(oldCoalition);
        clearParticipationRequestsFor(shop);
        return true;
    }

    /**
     * Refuses a participation request from a requesting shop in a hosting coalition
     *
     * @param coalition the hosting coalition
     * @param shop      the requesting shop
     * @return true if the request has been refused, false otherwise
     */
    @PostMapping("/refuseParticipationRequest")
    public boolean refuseParticipationRequest(@RequestBody CoalitionWithLeader coalition, @RequestBody Shop shop) {
        if (shop == null || coalition == null) return false;
        return coalition.refuseMember(shop);
    }


    /**
     * Makes a shop leave the coalition
     *
     * @param shop the leaving Shop
     * @return true if the shop left the coalition, false otherwise
     * @throws NullPointerException       if any of the field is null
     * @throws LastMemberLeavingException if the given shop is the only member of the coalition
     */
    @PostMapping("/leaveCoalition")
    public boolean leftCoalition(@RequestBody Shop shop) throws LastMemberLeavingException, HasAlreadyACoalitionException {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        Coalition coalition = getCoalitionOf(shop);
        if (coalition.hasOneMember())
            throw new LastMemberLeavingException("A shop can't leave a coalition with only one member");
        if (!removeShopFromCoalition(coalition, shop)) return false;
        return createCoalition(shop);
    }


    /**
     * Adds a shop in a coalition
     *
     * @param coalition the coalition where to add the shop
     * @param shop      the shop to add
     * @return true if the shop has been added, false otherwise
     * @throws NullPointerException    if any of the fields is null
     * @throws ShopNotInQueueException if the shop wasn't found in the participation requests queue of the coalition
     */
    @PostMapping("/addShopToCoalition")
    private boolean addShopToCoalition(@RequestBody CoalitionWithLeader coalition, @RequestBody Shop shop) throws ShopNotInQueueException {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        if (coalition == null) throw new NullPointerException("Field coalition can't be null");
        if (coalition.acceptMember(shop)) {
            FidelityProgramsController fidelityProgramsController = new FidelityProgramsController(db);
            return fidelityProgramsController.addShopToFidelityProgram(coalition.getFidelityProgram(), shop);
        }
        return false;
    }

    /**
     * Removes a shop from a coalition
     *
     * @param coalition the coalition where to remove the shop
     * @param shop      the shop to remove
     * @return true if the shop has been removed, false otherwise
     * @throws NullPointerException if any of the fields is null
     */
    @PostMapping("/removeShopFromCoalition")
    private boolean removeShopFromCoalition(@RequestBody Coalition coalition, @RequestBody Shop shop) {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        if (coalition == null) throw new NullPointerException("Field coalition can't be null");
        if (coalition.removeMember(shop)) {
            FidelityProgramsController fidelityProgramsController = new FidelityProgramsController(db);
            return fidelityProgramsController.deleteShopFromFidelityProgram(coalition.getFidelityProgram(), shop);
        }
        return false;
    }

    /**
     * Deletes a shop from all the participation requests queues
     *
     * @param shop the shop
     * @throws NullPointerException if the given shop is null
     */
    @PostMapping("/clearParticipationRequestsFor")
    private void clearParticipationRequestsFor(@RequestBody Shop shop) {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        HashSet<Coalition> coalitions = getCoalitions();
        for (Coalition coalition : coalitions) {
            if (coalition instanceof CoalitionWithLeader coalitionWithLeader) {
                if (coalitionWithLeader.isWaiting(shop)) coalitionWithLeader.refuseMember(shop);
            }
        }
    }

}

package LoyaltyPlatform.Components.Coalition;

import LoyaltyPlatform.Components.FidelityProgram.*;
import LoyaltyPlatform.Components.Shop.GenericShop;
import LoyaltyPlatform.Db.Db;
import LoyaltyPlatform.Exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

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
    public HashSet<GenericCoalition> getCoalitions() {
        return db.getCoalitionsTable().getRecords();
    }

    /**
     * Returns the coalition tho which the shop belongs
     *
     * @param shopId the id of the shop
     * @return the coalition of the shop if it has one
     * @throws NullPointerException if the given shop is null
     */
    @GetMapping("/getCoalitionOf")
    public GenericCoalition getCoalitionOf(@RequestParam int shopId) {
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        HashSet<GenericCoalition> coalitions = getCoalitions();
        for (GenericCoalition coalition : coalitions) {
            if (coalition.hasMember(shop)) return coalition;
        }
        return null;
    }

    /**
     * Creates a new coalition starting from a shop
     *
     * @param shopId the id of the first shop of the coalition
     * @return the new coalition
     * @throws HasAlreadyACoalitionException if the given shop already belongs to a coalition
     */
    public GenericCoalition createCoalition(int shopId) throws HasAlreadyACoalitionException {
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (getCoalitionOf(shopId) != null)
            throw new HasAlreadyACoalitionException("Can't create a coalition with a member of another coalition");
        GenericCoalition coalition = new GenericCoalition(shop);
        if (!db.getCoalitionsTable().add(coalition)) return null;
        return coalition;
    }

    /**
     * Deletes an empty coalition
     *
     * @param coalitionId the id of the coalition to delete
     * @return true if the coalition has been deleted, false otherwise
     * @throws CoalitionNotEmptyException if the given coalition is not empty
     */
    public boolean deleteCoalition(int coalitionId) throws CoalitionNotEmptyException {
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        if (!coalition.isEmpty()) throw new CoalitionNotEmptyException("Can't delete a coalition with some members");
        if (removeFidelityProgramFromCoalition(coalition.getId())) return db.getCoalitionsTable().delete(coalition);
        return false;
    }


    /**
     * Sets the name of the given coalition
     *
     * @param coalitionId the id of the coalition
     * @param name        the new name
     */
    @PostMapping("/setNameOf")
    public void setNameOf(@RequestParam int coalitionId, @RequestParam String name) {
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        if (coalition != null) coalition.setName(name);
    }

    /**
     * Adds a giftsProgram in the given coalition
     *
     * @param coalitionId the id of the coalition
     * @param multiplier  the multiplier for the giftsProgram
     * @param description the description for the giftsProgram
     */
    @PostMapping("/addGiftsProgramToCoalition")
    public boolean addGiftsProgramToCoalition(@RequestParam int coalitionId, @RequestParam double multiplier, @RequestParam String description) {
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        if (coalition == null) return false;
        GiftsProgramsController giftsProgramsController = new GiftsProgramsController(db);
        GiftsProgram giftsProgram = giftsProgramsController.createGiftsProgram(multiplier, description);
        if (giftsProgram == null) return false;
        if(!coalition.setFidelityProgram(giftsProgram)) return false;
        List<GenericShop> members = coalition.getMembers();
        for(GenericShop member : members)
            if(!giftsProgramsController.addShopToGiftsProgram(giftsProgram.getId(),member.getId())) return false;
        return true;
    }

    /**
     * Adds a levelsProgram in the given coalition
     *
     * @param coalitionId the id of the coalition
     * @param multiplier  the multiplier for the levelsProgram
     * @param description the description for the levelsProgram
     */
    @PostMapping("/addLevelsProgramToCoalition")
    public boolean addLevelsProgramToCoalition(@RequestParam int coalitionId, @RequestParam double multiplier, @RequestParam String description) {
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        if (coalition == null) return false;
        LevelsProgramsController levelsProgramsController = new LevelsProgramsController(db);
        LevelsProgram levelsProgram = levelsProgramsController.createLevelsProgram(multiplier, description);
        if (levelsProgram == null) return false;
        return coalition.setFidelityProgram(levelsProgram);
    }

    /**
     * Adds a level to the levelsProgram of the given coalition
     *
     * @param coalitionId    the id of the coalition
     * @param pointsThreshold the pointsThreshold for the level
     */
    @PostMapping("/addLevelToLevelsProgramOfCoalition")
    public boolean addLevelToLevelsProgramOfCoalition(@RequestParam int coalitionId, @RequestParam int pointsThreshold){
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        if (coalition == null) return false;
        if(!(coalition.getFidelityProgram() instanceof LevelsProgram levelsProgram)) return false;
        LevelsProgramsController levelsProgramsController = new LevelsProgramsController(db);
        if(!levelsProgramsController.addLevelToLevelsProgram(levelsProgram.getId(), pointsThreshold)) return false;
        List<GenericShop> members = coalition.getMembers();
        for(GenericShop member : members)
            if(!levelsProgramsController.addShopToLevelsProgram(levelsProgram.getId(),member.getId())) return false;
        return true;

    }

    @PostMapping("/removeLevelFromLevelsProgramOfCoalition")
    public boolean removeLevelFromLevelsProgramOfCoalition(@RequestParam int coalitionId, @RequestParam int levelId){
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        if (coalition == null) return false;
        if(!(coalition.getFidelityProgram() instanceof LevelsProgram levelsProgram)) return false;
        LevelsProgramsController levelsProgramsController = new LevelsProgramsController(db);
        return levelsProgramsController.deleteLevelFromLevelsProgram(levelsProgram.getId(), levelId);
    }

    /**
     * Removes the fidelityProgram from a given coalition
     *
     * @param coalitionId the id of the coalition where to remove the fidelityProgram
     * @return true if the fidelityProgram has been removed, false otherwise
     */
    @DeleteMapping("/removeFidelityProgramFromCoalition")
    public boolean removeFidelityProgramFromCoalition(@RequestParam int coalitionId) {
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        if (coalition == null) return false;
        FidelityProgram fidelityProgram = coalition.getFidelityProgram();
        if (fidelityProgram == null) return true;
        if (coalition.setFidelityProgram(null)) {
            if (fidelityProgram instanceof GiftsProgram giftsProgram) {
                GiftsProgramsController giftsProgramsController = new GiftsProgramsController(db);
                return giftsProgramsController.deleteGiftsProgram(giftsProgram.getId());
            }
            if (fidelityProgram instanceof LevelsProgram levelsprogram) {
                LevelsProgramsController levelsProgramsController = new LevelsProgramsController(db);
                return levelsProgramsController.deleteLevelsProgram(levelsprogram.getId());
            }
        }
        return false;
    }


    /**
     * Sends a participation request from a requesting shop to a hosting coalition
     *
     * @param coalitionId the id of the hosting coalition
     * @param shopId      the id of the requesting shop
     * @return true if the request has been sent, false otherwise
     * @throws FidelityProgramNotProvidedException if the hosting coalition has no fidelityProgram
     */
    @PostMapping("/sendParticipationRequest")
    public boolean sendParticipationRequest(@RequestParam int coalitionId, @RequestParam int shopId) throws FidelityProgramNotProvidedException {
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (coalition == null) return false;
        if (!coalition.hasFidelityProgram())
            throw new FidelityProgramNotProvidedException("Can't send a partecipation request to a coalition with no fidelityProgram");
        return coalition.addToParticipationRequests(shop);
    }

    /**
     * Accepts a participation request from a requesting shop in a hosting coalition
     * and then migrates the shop from its old coalition to the new one
     *
     * @param shopId      the id of the requesting shop
     * @param coalitionId the id of the hosting coalition
     * @return true if the request has been accepted and the shop migrated coalition, false otherwise
     * @throws ShopNotInQueueException             if the shop wasn't found in the participation requests queue of the new coalition
     * @throws FidelityProgramNotProvidedException if the new coalition doesn't provide a fidelity program
     */
    @PostMapping("/acceptParticipationRequest")
    public boolean acceptParticipationRequest(@RequestParam int coalitionId, @RequestParam int shopId) throws CoalitionNotEmptyException, ShopNotInQueueException, FidelityProgramNotProvidedException {
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (coalition == null) return false;
        if (!coalition.hasFidelityProgram())
            throw new FidelityProgramNotProvidedException("Can't migrate a shop in a coalition who doesn't provide a fidelity program");
        Coalition oldCoalition = getCoalitionOf(shop.getId());
        if (!addShopToCoalition(coalition.getId(), shop.getId())) return false;
        if (!removeShopFromCoalition(oldCoalition.getId(), shop.getId())) return false;
        clearParticipationRequestsFor(shop);
        return true;
    }

    /**
     * Refuses a participation request from a requesting shop in a hosting coalition
     *
     * @param coalitionId the id of the hosting coalition
     * @param shopId      the id of the requesting shop
     * @return true if the request has been refused, false otherwise
     */
    @PostMapping("/refuseParticipationRequest")
    public boolean refuseParticipationRequest(@RequestParam int coalitionId, @RequestParam int shopId) {
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (coalition == null) return false;
        return coalition.refuseMember(shop);
    }

    /**
     * Makes a shop leave the coalition
     *
     * @param shopId the id of the leaving Shop
     * @return true if the shop left the coalition, false otherwise
     * @throws LastMemberLeavingException if the given shop is the only member of the coalition
     */
    @PostMapping("/leftCoalition")
    public boolean leftCoalition(@RequestParam int shopId) throws LastMemberLeavingException, HasAlreadyACoalitionException, CoalitionNotEmptyException {
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        Coalition coalition = getCoalitionOf(shop.getId());
        if (coalition == null) return false;
        if (coalition.hasOneMember())
            throw new LastMemberLeavingException("A shop can't leave a coalition with only one member");
        if (!removeShopFromCoalition(coalition.getId(), shop.getId())) return false;
        return createCoalition(shop.getId()) != null;
    }


    /**
     * Adds a shop in a coalition
     *
     * @param coalitionId the id of the coalition where to add the shop
     * @param shopId      the id of the shop to add
     * @return true if the shop has been added, false otherwise
     * @throws ShopNotInQueueException if the shop wasn't found in the participation requests queue of the coalition
     */
    private boolean addShopToCoalition(int coalitionId, int shopId) throws ShopNotInQueueException {
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (coalition == null) return false;
        if (coalition.acceptMember(shop)) {
            FidelityProgram fidelityProgram = coalition.getFidelityProgram();
            if (fidelityProgram instanceof GiftsProgram giftsProgram) {
                GiftsProgramsController giftsProgramsController = new GiftsProgramsController(db);
                return giftsProgramsController.addShopToGiftsProgram(giftsProgram.getId(), shop.getId());
            }
            if (fidelityProgram instanceof LevelsProgram levelsProgram) {
                LevelsProgramsController levelsProgramsController = new LevelsProgramsController(db);
                return levelsProgramsController.addShopToLevelsProgram(levelsProgram.getId(), shop.getId());
            }
        }
        return false;
    }

    /**
     * Removes a shop from a coalition
     *
     * @param coalitionId the id of the coalition where to remove the shop
     * @param shopId      the id of the shop to remove
     * @return true if the shop has been removed, false otherwise
     */
    public boolean removeShopFromCoalition(int coalitionId, int shopId) throws CoalitionNotEmptyException {
        GenericCoalition coalition = db.getCoalitionsTable().getRecordById(coalitionId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (coalition == null) return false;
        if (coalition.removeMember(shop)) {
            if (coalition.isEmpty()) return deleteCoalition(coalition.getId());
            FidelityProgram fidelityProgram = coalition.getFidelityProgram();
            if (fidelityProgram instanceof GiftsProgram giftsProgram) {
                GiftsProgramsController giftsProgramsController = new GiftsProgramsController(db);
                return giftsProgramsController.removeShopFromGiftsProgram(giftsProgram.getId(), shop.getId());
            }
            if (fidelityProgram instanceof LevelsProgram levelsProgram) {
                LevelsProgramsController levelsProgramsController = new LevelsProgramsController(db);
                return levelsProgramsController.removeShopFromLevelsProgram(levelsProgram.getId(), shop.getId());
            }
        }
        return false;
    }

    /**
     * Deletes a shop from all the participation requests queues
     *
     * @param shop the shop
     * @throws NullPointerException if the given shop is null
     */
    private void clearParticipationRequestsFor(GenericShop shop) {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        HashSet<GenericCoalition> coalitions = getCoalitions();
        for (GenericCoalition coalition : coalitions)
            if (coalition.isWaiting(shop)) coalition.refuseMember(shop);
    }

}

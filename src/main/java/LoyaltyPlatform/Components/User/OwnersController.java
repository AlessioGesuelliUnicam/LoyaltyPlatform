package LoyaltyPlatform.Components.User;

import LoyaltyPlatform.Components.Shop.GenericShop;
import LoyaltyPlatform.Components.Shop.ShopsController;
import LoyaltyPlatform.Db.Db;
import LoyaltyPlatform.Exceptions.CoalitionNotEmptyException;
import LoyaltyPlatform.Exceptions.HasAlreadyACoalitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/owners")
public class
OwnersController {

    private final Db db;

    @Autowired
    public OwnersController(Db db) {
        this.db = db;
    }

    /**
     * Returns all the owners
     * @return the set of owners
     */
    @GetMapping("/getOwners")
    public HashSet<Owner> getOwners(){
        return db.getOwnersTable().getRecords();
    }

    /**
     * Creates a new owner and a related shop
     * @param name the name of the owner
     * @param surname the surname of the owner
     * @param email the email of the owner
     * @param partitaIva the partitaIva of the new shop
     * @return true if the owner and its shop have been created, false otherwise
     */
    @PostMapping("/createOwner")
    public boolean createOwner(@RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String partitaIva) throws HasAlreadyACoalitionException {
        Owner owner = new Owner(name, surname, email);
        if(!db.getOwnersTable().add(owner)) return false;
        ShopsController shopsController = new ShopsController(db);
        return shopsController.createShop(partitaIva, owner.getId()) != null;
    }

    /**
     * Deletes an owner and its shop
     * @param ownerId the id of the owner to delete
     * @return true if the owner and its shop have been deleted, false otherwise
     */
    @DeleteMapping("/deleteOwner")
    public boolean deleteOwner(@RequestParam int ownerId) throws CoalitionNotEmptyException {
        Owner owner = db.getOwnersTable().getRecordById(ownerId);
        if (owner == null) return false;
        ShopsController shopsController = new ShopsController(db);
        GenericShop shop = shopsController.getShopOf(owner.getId());
        if(!shopsController.deleteShop(shop.getId())) return false;
        return db.getOwnersTable().delete(owner);
    }



    /**
     * Sets the name for the given owner
     * @param ownerId the id of the owner
     * @param name the new name
     */
    @PostMapping("/setOwnerName")
    void setOwnerName(@RequestParam int ownerId, @RequestParam String name){
        Owner owner = db.getOwnersTable().getRecordById(ownerId);
        owner.setName(name);
    }

    /**
     * Sets the surname for the given owner
     * @param ownerId the id of the owner
     * @param surname the new surname
     */
    @PostMapping("/setOwnerSurname")
    void setOwnerSurname(@RequestParam int ownerId, @RequestParam String surname){
        Owner owner = db.getOwnersTable().getRecordById(ownerId);
        owner.setSurname(surname);
    }

    /**
     * Sets the email for the given owner
     * @param ownerId the id of the owner
     * @param email the new email
     */
    @PostMapping("/setOwnerEmail")
    void setOwnerEmail(@RequestParam int ownerId, @RequestParam String email){
        Owner owner = db.getOwnersTable().getRecordById(ownerId);
        owner.setEmail(email);
    }


}

package LoyaltyPlatform.Components.User;

import LoyaltyPlatform.Db.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/owners")
public class OwnersController {

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
     * Creates a new owner
     * @param name the name of the owner
     * @param surname the surname of the owner
     * @param email the email of the owner
     * @return true if the owner has been created, false otherwise
     */
    @PostMapping("/createOwner")
    public boolean createOwner(@RequestParam String name, @RequestParam String surname, @RequestParam String email){
        Owner owner = new Owner(name, surname, email);
        return db.getOwnersTable().add(owner);
    }

    /**
     * Deletes an owner
     * @param ownerId the id of the owner to delete
     * @return true if the owner has been deleted, false otherwise
     */
    @DeleteMapping("/deleteOwner")
    public boolean deleteOwner(@RequestParam int ownerId){
        Owner owner = db.getOwnersTable().getRecordById(ownerId);
        if (owner == null) return false;
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

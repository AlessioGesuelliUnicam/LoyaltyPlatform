package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.User.Owner;

import java.util.HashSet;

public class OwnersTable implements Table<Owner> {

    private HashSet<Owner> owners;

    public OwnersTable() {
        owners = new HashSet<>();
    }

    /**
     * Returns the collection of Owner
     *
     * @return the collection
     */
    public HashSet<Owner> getRecords() {
        return owners;
    }

    /**
     * Adds an Owner to the collection
     *
     * @param record the Owner to add
     * @return true if the Owner is added, false otherwise
     */
    public boolean add(Owner record) {
        if (record == null) return false;
        return owners.add(record);
    }

    /**
     * Removes an Owner from the collection
     *
     * @param record the Owner to remove
     * @return true if the Owner is deleted, false otherwise
     */
    public boolean remove(Owner record) {
        if (record == null) return false;
        return owners.remove(record);
    }

    /**
     * Finds an Owner by id
     *
     * @param id the Owner of the record
     * @return the Owner if found
     */
    public Owner findById(int id) {
        for (Owner owner : owners) {
            if (owner.getId() == id) return owner;
        }
        return null;
    }
}

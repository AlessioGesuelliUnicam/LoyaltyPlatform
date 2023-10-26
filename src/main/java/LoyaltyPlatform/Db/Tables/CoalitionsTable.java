package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.Coalition.Coalition;

import java.util.HashSet;


public class CoalitionsTable implements Table<Coalition> {
    private HashSet<Coalition> coalitions;

    public CoalitionsTable() {
        coalitions = new HashSet<>();
    }

    /**
     * Returns the collection of records
     *
     * @return the collection
     */
    public HashSet<Coalition> getRecords() {
        return coalitions;
    }

    /**
     * Adds a record to the collection
     *
     * @param record the record to add
     * @return true if the record is added, false otherwise
     * @throws NullPointerException if the given record is null
     */
    public boolean add(Coalition record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        return coalitions.add(record);
    }

    /**
     * Removes a record from the collection
     *
     * @param record the record to remove
     * @return true if the record is deleted, false otherwise
     * @throws NullPointerException if the given record is null
     */
    public boolean remove(Coalition record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        return coalitions.remove(record);
    }

    /**
     * Finds a record by id
     *
     * @param id the id of the record
     * @return the record if found
     */
    public Coalition findById(int id) {
        for (Coalition coalition : coalitions) {
            if (coalition.getId() == id) {
                return coalition;
            }
        }
        return null;
    }
}

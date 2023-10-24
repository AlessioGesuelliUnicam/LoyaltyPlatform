package LoyaltyPlatform.Db;

import LoyaltyPlatform.Coalition.Coalition;

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
     */
    public void add(Coalition record) {
        coalitions.add(record);
    }

    /**
     * Removes a record from the collection
     *
     * @param record the record to remove
     */
    public void remove(Coalition record) {
        coalitions.remove(record);
    }

    /**
     * Finds a record by id
     *
     * @param id the id of the record
     * @return the record
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

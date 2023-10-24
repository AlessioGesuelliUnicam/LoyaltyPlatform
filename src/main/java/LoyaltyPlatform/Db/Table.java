package LoyaltyPlatform.Db;

import java.util.Set;

/**
 * A Table represents a collection of records
 * @param <T> the type of the records
 */
public interface Table <T> {

    /**
     * Returns the collection of records
     * @return the collection
     */
    Set<T> getRecords();

    /**
     * Adds a record to the collection
     * @param record the record to add
     */
    void add(T record);

    /**
     * Removes a record from the collection
     * @param record the record to remove
     */
    void remove(T record);

    /**
     * Finds a record by id
     * @param id the id of the record
     * @return the record
     */
    T findById(int id);

}

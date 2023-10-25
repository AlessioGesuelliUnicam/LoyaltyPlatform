package LoyaltyPlatform.Db.Tables;

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
     * @return true if the record is added, false otherwise
     */
    boolean add(T record);

    /**
     * Removes a record from the collection
     * @param record the record to remove
     * @return true if the record is deleted, false otherwise
     */
    boolean remove(T record);

    /**
     * Finds a record by id
     * @param id the id of the record
     * @return the record if found
     */
    T findById(int id);

}

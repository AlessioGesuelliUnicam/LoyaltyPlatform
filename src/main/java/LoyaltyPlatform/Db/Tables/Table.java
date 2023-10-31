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
     * Deletes a record in the collection
     * @param record the record to delete
     * @return true if the record is deleted, false otherwise
     */
    boolean delete(T record);

}

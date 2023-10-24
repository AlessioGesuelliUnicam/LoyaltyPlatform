package LoyaltyPlatform.Db;

import java.util.List;

/**
 * A Table represents a collection of records
 * @param <T> the type of the records
 */
public interface Table <T> {

    /**
     * Returns the collection of records
     * @return the collection
     */
    List<T> getRecords();

}

package LoyaltyPlatform.Db.Tables;

import java.util.HashSet;

public abstract class GenericTable<T> implements Table<T>{

    HashSet<T> records;

    public GenericTable(){
        records = new HashSet<T>();
    }

    /**
     * Returns the collection of records
     * @return the records
     */
    public HashSet<T> getRecords() {
        return records;
    }

    /**
     * Adds a record to the collection
     * @param record the record to add
     * @return true if the element is added, false otherwise
     * @throws NullPointerException if the given Client is null
     */
    public boolean add(T record) {
        if(record == null) throw new NullPointerException("Field record can't be null");
        return records.add(record);
    }

    /**
     * Removes a record from the collection
     * @param record the record to remove
     * @return true if the record is deleted, false otherwise
     * @throws NullPointerException if the given record is null
     */
    public boolean remove(T record) {
        if(record == null) throw new NullPointerException("Field record can't be null");
        return records.remove(record);
    }

}

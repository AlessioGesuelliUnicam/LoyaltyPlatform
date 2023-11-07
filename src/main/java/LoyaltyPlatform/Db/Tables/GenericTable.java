package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Utilities.ObjectWithId;

import java.util.HashSet;

public abstract class GenericTable<T> implements Table<T> {

    HashSet<T> records;

    public GenericTable() {
        records = new HashSet<T>();
    }

    /**
     * Returns the collection of records
     *
     * @return the records
     */
    public HashSet<T> getRecords() {
        return records;
    }

    /**
     * Adds a record to the collection
     *
     * @param record the record to add
     * @return true if the element is added, false otherwise
     * @throws NullPointerException if the given Client is null
     */
    public boolean add(T record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        return records.add(record);
    }

    /**
     * Deletes a record in the collection
     *
     * @param record the record to delete
     * @return true if the record is deleted, false otherwise
     * @throws NullPointerException if the given record is null
     */
    public boolean delete(T record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        return records.remove(record);
    }

    /**
     * Returns a record by its id
     *
     * @param id the id of the record to find
     * @return the record if found, null otherwise
     * @throws IllegalArgumentException if the given id is negative
     */
    public T getRecordById(int id) {
        if (id < 0) throw new IllegalArgumentException("Field id can't be negative");
        for (T record : records)
            if (record instanceof ObjectWithId recordWithId)
                if (recordWithId.getId() == id) return record;
        return null;
    }

}

package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.Reward.Gift;

import java.util.HashSet;

public class GiftsTable implements Table<Gift> {

    private HashSet<Gift> gifts;

    public GiftsTable() {
        gifts = new HashSet<>();
    }

    /**
     * Returns the collection of gifts
     *
     * @return the collection
     */
    public HashSet<Gift> getRecords() {
        return gifts;
    }

    /**
     * Adds a Gift to the collection
     *
     * @param record the Gift to add
     * @return true if the Gift is added, false otherwise
     */
    public boolean add(Gift record) {
        if (record == null) return false;
        return gifts.add(record);
    }

    /**
     * Removes a Gift from the collection
     *
     * @param record the Gift to remove
     * @return true if the Gift is deleted, false otherwise
     */
    public boolean remove(Gift record) {
        if (record == null) return false;
        return gifts.remove(record);
    }

    /**
     * Finds a Gift by id
     *
     * @param id the id of the Gift
     * @return the Gift if found
     */
    public Gift findById(int id) {
        for (Gift gifts : gifts) {
            if (gifts.getId() == id) return gifts;
        }
        return null;
    }

}

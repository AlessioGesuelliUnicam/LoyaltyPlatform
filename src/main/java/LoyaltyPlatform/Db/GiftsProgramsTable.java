package LoyaltyPlatform.Db;

import LoyaltyPlatform.FidelityProgram.GiftsProgram;

import java.util.HashSet;

public class GiftsProgramsTable implements Table<GiftsProgram> {

    private HashSet<GiftsProgram> giftPrograms;

    public GiftsProgramsTable(){
        giftPrograms = new HashSet<>();
    }

    /**
     * Returns the collection of GiftPrograms
     * @return the collection
     */
    public HashSet<GiftsProgram> getRecords() {
        return giftPrograms;
    }

    /**
     * Adds a GiftProgram to the collection
     * @param record the GiftProgram to add
     */
    public void add(GiftsProgram record) {
        giftPrograms.add(record);
    }

    /**
     * Removes a GiftProgram from the collection
     * @param record the GiftProgram to remove
     */
    public void remove(GiftsProgram record) {
        giftPrograms.remove(record);
    }
}

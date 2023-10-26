package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.FidelityProgram.GiftsProgram;

import java.util.HashSet;

public class GiftsProgramsTable implements Table<GiftsProgram> {

    private HashSet<GiftsProgram> giftsPrograms;

    public GiftsProgramsTable() {
        giftsPrograms = new HashSet<>();
    }

    /**
     * Returns the collection of GiftPrograms
     *
     * @return the collection
     */
    public HashSet<GiftsProgram> getRecords() {
        return giftsPrograms;
    }

    /**
     * Adds a GiftProgram to the collection
     *
     * @param record the GiftProgram to add
     * @return true if the GiftProgram is added, false otherwise
     * @throws NullPointerException if the given GiftProgram is null
     */
    public boolean add(GiftsProgram record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        return giftsPrograms.add(record);

    }

    /**
     * Removes a GiftProgram from the collection
     *
     * @param record the GiftProgram to remove
     * @return true if the GiftProgram is deleted, false otherwise
     * @throws NullPointerException if the given GiftProgram is null
     */
    public boolean remove(GiftsProgram record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        return giftsPrograms.remove(record);
    }

    /**
     * Finds a GiftsProgram by id
     *
     * @param id the id of the GiftsProgram
     * @return the GiftsProgram if found
     */
    public GiftsProgram findById(int id) {
        for (GiftsProgram giftsProgram : giftsPrograms) {
            if (giftsProgram.getId() == id) return giftsProgram;
        }
        return null;
    }
}

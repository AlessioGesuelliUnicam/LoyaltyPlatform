package LoyaltyPlatform.Db;

import LoyaltyPlatform.FidelityProgram.GiftsProgram;

import java.util.HashSet;

public class GiftsProgramsTable implements Table<GiftsProgram> {

    private HashSet<GiftsProgram> giftsPrograms;

    public GiftsProgramsTable(){
        giftsPrograms = new HashSet<>();
    }

    /**
     * Returns the collection of GiftPrograms
     * @return the collection
     */
    public HashSet<GiftsProgram> getRecords() {
        return giftsPrograms;
    }

    /**
     * Adds a GiftProgram to the collection
     * @param record the GiftProgram to add
     */
    public void add(GiftsProgram record) {
        giftsPrograms.add(record);
    }

    /**
     * Removes a GiftProgram from the collection
     * @param record the GiftProgram to remove
     */
    public void remove(GiftsProgram record) {
        giftsPrograms.remove(record);
    }

    /**
     * Finds a GiftsProgram by id
     * @param id the id of the GiftsProgram
     * @return the GiftsProgram if found
     */
    public GiftsProgram findById(int id) {
        for(GiftsProgram giftsProgram: giftsPrograms){
            if (giftsProgram.getId() == id) return giftsProgram;
        }
        return null;
    }
}

package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.FidelityProgram.GiftsProgram;

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
     * @return true if the GiftProgram is added, false otherwise
     */
    public boolean add(GiftsProgram record) {
        if(record == null) return false;
        giftsPrograms.add(record);
        return true;
    }

    /**
     * Removes a GiftProgram from the collection
     * @param record the GiftProgram to remove
     * @return true if the GiftProgram is deleted, false otherwise
     */
    public boolean remove(GiftsProgram record) {
        if(record == null) return false;
        giftsPrograms.remove(record);
        return true;
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

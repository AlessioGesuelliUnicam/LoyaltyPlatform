package LoyaltyPlatform.Db;

import LoyaltyPlatform.FidelityProgram.LevelsProgram;
import LoyaltyPlatform.Reward.Gift;

import java.util.HashSet;

public class LevelsProgramsTable implements Table<LevelsProgram>{

    private HashSet<LevelsProgram> levelsPrograms;

    public LevelsProgramsTable(){
        levelsPrograms = new HashSet<>();
    }

    /**
     * Returns the collection of LevelsPrograms
     * @return the collection
     */
    public HashSet<LevelsProgram> getRecords() {
        return levelsPrograms;
    }

    /**
     * Adds a LevelsProgram to the collection
     * @param record the LevelsProgram to add
     */
    public void add(LevelsProgram record) {
        levelsPrograms.add(record);
    }

    /**
     * Removes a LevelsProgram from the collection
     * @param record the LevelsProgram to remove
     */
    public void remove(LevelsProgram record) {
        levelsPrograms.remove(record);
    }

    /**
     * Finds a LevelsProgram by id
     * @param id the id of the LevelsProgram
     * @return the LevelsProgram if found
     */
    public LevelsProgram findById(int id) {
        for(LevelsProgram levelsProgram: levelsPrograms){
            if (levelsProgram.getId() == id) return levelsProgram;
        }
        return null;
    }

}

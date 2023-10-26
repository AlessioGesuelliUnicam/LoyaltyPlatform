package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.FidelityProgram.LevelsProgram;

import java.util.HashSet;

public class LevelsProgramsTable implements Table<LevelsProgram> {

    private HashSet<LevelsProgram> levelsPrograms;

    public LevelsProgramsTable() {
        levelsPrograms = new HashSet<>();
    }

    /**
     * Returns the collection of LevelsPrograms
     *
     * @return the collection
     */
    public HashSet<LevelsProgram> getRecords() {
        return levelsPrograms;
    }

    /**
     * Adds a LevelsProgram to the collection
     *
     * @param record the LevelsProgram to add
     * @return true if the LevelsProgram is added, false otherwise
     * @throws NullPointerException if the given LevelsProgram is null
     */
    public boolean add(LevelsProgram record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        levelsPrograms.add(record);
        return true;
    }

    /**
     * Removes a LevelsProgram from the collection
     *
     * @param record the LevelsProgram to remove
     * @return true if the LevelsProgram is deleted, false otherwise
     * @throws NullPointerException if the given LevelsProgram is null
     */
    public boolean remove(LevelsProgram record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        levelsPrograms.remove(record);
        return true;
    }

    /**
     * Finds a LevelsProgram by id
     *
     * @param id the id of the LevelsProgram
     * @return the LevelsProgram if found
     */
    public LevelsProgram findById(int id) {
        for (LevelsProgram levelsProgram : levelsPrograms) {
            if (levelsProgram.getId() == id) return levelsProgram;
        }
        return null;
    }

}

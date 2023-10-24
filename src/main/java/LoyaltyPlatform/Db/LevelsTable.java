package LoyaltyPlatform.Db;

import LoyaltyPlatform.Level.Level;

import java.util.HashSet;

public class LevelsTable implements Table<Level>{

    private HashSet<Level> levels;

    public LevelsTable(){
        levels = new HashSet<>();
    }

    /**
     * Returns the collection of levels
     * @return the collection
     */
    public HashSet<Level> getRecords() {
        return levels;
    }

    /**
     * Adds a Level to the collection
     * @param record the Level to add
     */
    public void add(Level record) {
        levels.add(record);
    }

    /**
     * Removes a Level from the collection
     * @param record the Level to remove
     */
    public void remove(Level record) {
        levels.remove(record);
    }
}

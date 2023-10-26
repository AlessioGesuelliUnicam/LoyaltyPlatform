package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.Level.Level;

import java.util.HashSet;

public class LevelsTable implements Table<Level> {

    private HashSet<Level> levels;

    public LevelsTable() {
        levels = new HashSet<>();
    }

    /**
     * Returns the collection of levels
     *
     * @return the collection
     */
    public HashSet<Level> getRecords() {
        return levels;
    }

    /**
     * Adds a Level to the collection
     *
     * @param record the Level to add
     * @return true if the Level is added, false otherwise
     * @throws NullPointerException if the record is null
     */
    public boolean add(Level record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        return levels.add(record);
    }

    /**
     * Removes a Level from the collection
     *
     * @param record the Level to remove
     * @return true if the Level is deleted, false otherwise
     * @throws NullPointerException if the record is null
     */
    public boolean remove(Level record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        return levels.remove(record);
    }

    /**
     * Finds a Level by id
     *
     * @param id the id of the Level
     * @return the Level if found
     */
    public Level findById(int id) {
        for (Level level : levels) {
            if (level.getId() == id) return level;
        }
        return null;
    }

}

package LoyaltyPlatform.Components.Level;

import LoyaltyPlatform.Db.Db;

public class LevelsController {

    private final Db db;

    public LevelsController(Db db) {
        this.db = db;
    }

    /**
     * Creates a new Level
     * @param pointsThreshold the pointsThreshold
     * @return true if the Level has been created, false otherwise
     */
    public boolean createLevel(int pointsThreshold){
        if(pointsThreshold < 0) return false;
        Level level = new Level(pointsThreshold);
        return db.getLevelsTable().add(level);
    }

    /**
     * Deletes a level
     * @param id the id of the level to delete
     * @return true if the level has been deleted, false otherwise
     */
    public boolean deleteLevel(int id){
        if(id < 0) return false;
        Level level = db.getLevelsTable().findById(id);

        return db.getLevelsTable().remove(level);
    }
}
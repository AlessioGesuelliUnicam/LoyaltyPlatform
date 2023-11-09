package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Coalition.Coalition;
import LoyaltyPlatform.Components.Level.Level;
import LoyaltyPlatform.Components.Level.LevelsController;
import LoyaltyPlatform.Components.Shop.GenericShop;
import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Db.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/levelsPrograms")
public class LevelsProgramsController {

    private final Db db;

    @Autowired
    public LevelsProgramsController(Db db) {
        this.db = db;
    }



    /**
     * Returns all the LevelsPrograms
     * @return the LevelsPrograms
     */
    @GetMapping("/getLevelsPrograms")
    public Set<LevelsProgram> getLevelsPrograms(){
        return db.getLevelsProgramsTable().getRecords();
    }

    /**
     * Creates a new levelsProgram
     *
     * @param multiplier  the multiplier used to convert euros into points
     * @param description the description of the program
     * @return the new levelsProgram
     */
    public LevelsProgram createLevelsProgram(double multiplier, String description) {
        LevelsProgram levelsProgram = new LevelsProgram(multiplier, description);
        if(!db.getLevelsProgramsTable().add(levelsProgram)) return null;
        return levelsProgram;
    }

    /**
     * Deletes a levelsProgram
     *
     * @param levelsProgramId the id of the levelsProgram to delete
     * @return true if the program has been deleted, false otherwise
     */
    public boolean deleteLevelsProgram(int levelsProgramId) {
        LevelsProgram levelsProgram = db.getLevelsProgramsTable().getRecordById(levelsProgramId);
        if(levelsProgram == null) return false;
        LevelsController levelsController = new LevelsController(db);
        TreeSet<Level> levels = levelsProgram.getLevels();
        for (Level level : levels)
            if(!levelsController.deleteLevel(level.getId())) return false;
        return db.getLevelsProgramsTable().delete(levelsProgram);
    }

    /**
     * Adds a shop in a levelsProgram
     * @param levelsProgramId the id of the levelsProgram where to add the shop
     * @param shopId the id of the shop to add
     * @return true if the shop has been added, false otherwise
     */
    public boolean addShopToLevelsProgram(int levelsProgramId, int shopId){
        LevelsProgram levelsProgram = db.getLevelsProgramsTable().getRecordById(levelsProgramId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if(levelsProgram == null) return false;
        LevelsController levelsController = new LevelsController(db);
        TreeSet<Level> levels = levelsProgram.getLevels();
        for(Level level : levels)
            if(!levelsController.addShopToLevel(level.getId(), shop.getId())) return false;
        return true;
    }

    /**
     * Removes a shop from a levelsProgram
     * @param levelsProgramId the id of the levelsProgram from which to remove the shop
     * @param shopId the id of the shop to remove
     * @return true if the shop has been removed, false otherwise
     */
    public boolean removeShopFromLevelsProgram(int levelsProgramId, int shopId){
        LevelsProgram levelsProgram = db.getLevelsProgramsTable().getRecordById(levelsProgramId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if(levelsProgram == null || shop == null) return false;
        LevelsController levelsController = new LevelsController(db);
        TreeSet<Level> levels = levelsProgram.getLevels();
        for(Level level : levels)
            if(!levelsController.removeShopFromLevel(level.getId(), shop.getId())) return false;
        return true;
    }

    /**
     * Adds a level in a given levelsProgram
     * @param levelsProgramId the id of the levelsProgram from which to add the level
     * @param pointsThreshold the pointsThreshold of the new level
     * @return true if the level has been added, false otherwise
     */
    public boolean addLevelToLevelsProgram(int levelsProgramId, int pointsThreshold){
        LevelsProgram levelsProgram = db.getLevelsProgramsTable().getRecordById(levelsProgramId);
        if(levelsProgram == null) return false;
        LevelsController levelsController = new LevelsController(db);
        Level level = levelsController.createLevel(pointsThreshold);
        return levelsProgram.addLevel(level);
    }

    /**
     * Deletes a level from a given levelsProgram
     * @param levelsProgramId the id of the levelsProgram from which to remove the level
     * @param levelId the id of the level to remove
     * @return true if the level has been deleted, false otherwise
     */
    public boolean deleteLevelFromLevelsProgram(int levelsProgramId, int levelId){
        LevelsProgram levelsProgram = db.getLevelsProgramsTable().getRecordById(levelsProgramId);
        Level level = db.getLevelsTable().getRecordById(levelId);
        if(levelsProgram == null || level == null) return false;
        LevelsController levelsController = new LevelsController(db);
        if(!levelsProgram.deleteLevel(level)) return false;
        return levelsController.deleteLevel(level.getId());
    }

}

package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Level.Level;
import LoyaltyPlatform.Components.Level.LevelsController;
import LoyaltyPlatform.Components.Reward.Gift;
import LoyaltyPlatform.Components.Reward.RewardsController;
import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Db.Db;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class FidelityProgramsController {

    private final Db db;

    public FidelityProgramsController(Db db) {
        this.db = db;
    }

    /**
     * Returns the complete set of existing LevelsPrograms
     * @return the GiftsPrograms
     */
    public Set<GiftsProgram> getGiftsPrograms(){
        return db.getGiftsProgramsTable().getRecords();
    }

    /**
     * Creates a new Gifts Program
     *
     * @param multiplier  the multiplier used to convert euros into points
     * @param description the description of the program
     * @return true if the program has been created, false otherwise
     * @throws IllegalArgumentException if the field multiplier is out of range 0-1
     */
    public boolean createGiftsProgram(double multiplier, String description) {
        if(multiplier < 0 || multiplier > 1) throw new IllegalArgumentException("Field multiplier out of range 0-1");
        GiftsProgram giftsProgram = new GiftsProgram(multiplier, description);
        return db.getGiftsProgramsTable().add(giftsProgram);
    }

    /**
     * Deletes a Gifts Program
     *
     * @param giftsProgram the giftProgram to delete
     * @return true if the program has been deleted, false otherwise
     * @throws NullPointerException if field giftsProgram is null
     */
    public boolean deleteGiftsProgram(GiftsProgram giftsProgram) {
        if (giftsProgram == null) throw new NullPointerException("Field giftsProgram can't be null");
        RewardsController rewardsController = new RewardsController(db);
        HashMap<Shop, Set<Gift>> shopsGift = giftsProgram.getShopsGift();
        for (Map.Entry<Shop, Set<Gift>> entry : shopsGift.entrySet()){
            Set<Gift> gifts = entry.getValue();
            for (Gift gift : gifts) rewardsController.deleteGift(gift);
        }
        return db.getGiftsProgramsTable().remove(giftsProgram);
    }

    /**
     * Adds a shop in the given giftsProgram
     * @param giftsProgram the giftsProgram from which to add a new shop
     * @param shop the shop to add
     * @return true if the shop has been added to the level, false if the shop was already present
     */
    public boolean addShopToLevel(GiftsProgram giftsProgram, Shop shop){
        if(giftsProgram == null) throw new NullPointerException("Field level can't be null");
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        return giftsProgram.addShop(shop);
    }

    /**
     * Removes a shop from the given giftsProgram
     * @param giftsProgram the giftsProgram from which to remove the shop
     * @param shop the shop to remove
     * @return true if the shop has been removed, false if the shop was not found
     * @throws NullPointerException if any of the fields is null
     */
    public boolean deleteShopFromLevel(GiftsProgram giftsProgram, Shop shop){
        if(giftsProgram == null) throw new NullPointerException("Field level can't be null");
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        RewardsController rewardsController = new RewardsController(db);
        HashMap<Shop, Set<Gift>> shopsDiscount = giftsProgram.getShopsGift();
        Set<Gift> gifts = shopsDiscount.get(shop);
        for(Gift gift : gifts) rewardsController.deleteGift(gift);
        return giftsProgram.deleteShop(shop);
    }





    /**
     * Returns the complete set of existing LevelsPrograms
     * @return the LevelsPrograms
     */
    public Set<LevelsProgram> getLevelsPrograms(){
        return db.getLevelsProgramsTable().getRecords();
    }

    /**
     * Creates a new Levels Program
     *
     * @param multiplier  the multiplier used to convert euros into points
     * @param description the description of the program
     * @return true if the program has been created, false otherwise
     * @throws NullPointerException if field multiplier is null
     */
    public boolean createLevelsProgram(double multiplier, String description) {
        if(multiplier < 0 || multiplier > 1) throw new IllegalArgumentException("Field multiplier out of range 0-1");
        LevelsProgram levelsProgram = new LevelsProgram(multiplier, description);
        return db.getLevelsProgramsTable().add(levelsProgram);
    }

    /**
     * Deletes a Levels Program
     *
     * @param levelsProgram the levelsProgram to delete
     * @return true if the program has been deleted, false otherwise
     * @throws NullPointerException if field levelsProgram is null
     */
    public boolean deleteLevelsProgram(LevelsProgram levelsProgram) {
        if(levelsProgram == null) throw new NullPointerException("Field levelsProgram can't be null");
        LevelsController levelsController = new LevelsController(db);
        TreeSet<Level> levels = levelsProgram.getLevels();
        for (Level level : levels) levelsController.deleteLevel(level);
        return db.getLevelsProgramsTable().remove(levelsProgram);
    }

    /**
     * Adds a level in a given levelsProgram
     * @param levelsProgram the levelsProgram from which to add the level
     * @param level the level to add
     * @return true if the level has been added, false otherwise
     * @throws NullPointerException if any of the fields is null
     */
    public boolean addLevelToLevelsProgram(LevelsProgram levelsProgram, Level level){
        if(levelsProgram == null) throw new NullPointerException("Field levelsProgram can't be null");
        if(level == null) throw new NullPointerException("Field level can't be null");
        return levelsProgram.addLevel(level);
    }

    /**
     * Deletes a level from a given levelsProgram
     * @param levelsProgram he levelsProgram from which to remove the level
     * @param level the level to remove
     * @return true if the level has been deleted, false otherwise
     * @throws NullPointerException if any of the fields is null
     */
    public boolean deleteLevelFromLevelsProgram(LevelsProgram levelsProgram, Level level){
        if(levelsProgram == null) throw new NullPointerException("Field levelsProgram can't be null");
        if(level == null) throw new NullPointerException("Field level can't be null");
        LevelsController levelsController = new LevelsController(db);
        levelsProgram.deleteLevel(level);
        return levelsController.deleteLevel(level);
    }

}

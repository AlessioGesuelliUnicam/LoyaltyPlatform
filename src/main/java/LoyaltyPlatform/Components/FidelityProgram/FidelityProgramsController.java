package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Coalition.Coalition;
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
     * Deletes a fidelity program
     * @param fidelityProgram the fidelityProgram to delete
     * @return true if the fidelityProgram has been deleted, false otherwise
     * @throws NullPointerException if the given fidelityProgram is null
     */
    public boolean deleteFidelityProgram(FidelityProgram fidelityProgram){
        if(fidelityProgram == null) throw new NullPointerException("Field fidelityProgram can't be null");
        if(fidelityProgram instanceof GiftsProgram giftsProgram)
            return deleteGiftsProgram(giftsProgram);
        if(fidelityProgram instanceof LevelsProgram levelsProgram){
            return deleteLevelsProgram(levelsProgram);
        }
        return false;
    }

    /**
     * Adds a shop in a fidelityProgram
     * @param fidelityProgram the fidelityProgram where to add the shop
     * @param shop the shop to add
     * @return true if the shop has been added, false otherwise
     * @throws NullPointerException if any of the fields is null
     */
    public boolean addShopToFidelityProgram(FidelityProgram fidelityProgram, Shop shop){
        if(fidelityProgram == null) throw new NullPointerException("Field fidelityProgram can't be null");
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(fidelityProgram instanceof GiftsProgram giftsProgram)
            return addShopToGiftsProgram(giftsProgram, shop);
        if(fidelityProgram instanceof LevelsProgram levelsProgram)
            return addShopToLevelsProgram(levelsProgram, shop);
        return false;
    }

    /**
     * Removes a shop from a fidelityProgram
     * @param fidelityProgram the fidelityProgram from which to remove the shop
     * @param shop the shop to remove
     * @return true if the shop has been removed, false otherwise
     * @throws NullPointerException if any of the fields is null
     */
    public boolean deleteShopFromFidelityProgram(FidelityProgram fidelityProgram, Shop shop){
        if(fidelityProgram == null) throw new NullPointerException("Field fidelityProgram can't be null");
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(fidelityProgram instanceof GiftsProgram giftsProgram)
            return removeShopFromGiftsProgram(giftsProgram, shop);
        if(fidelityProgram instanceof LevelsProgram levelsProgram)
            return removeShopFromLevelsProgram(levelsProgram, shop);
        return false;
    }



    /**
     * Returns the complete set of existing LevelsPrograms
     * @return the GiftsPrograms
     */
    public Set<GiftsProgram> getGiftsPrograms(){
        return db.getGiftsProgramsTable().getRecords();
    }

    /**
     * Creates a new giftsProgram
     *
     * @param coalition the coalition which the giftsProgram belongs
     * @param multiplier  the multiplier used to convert euros into points
     * @param description the description of the program
     * @return true if the program has been created, false otherwise
     * @throws IllegalArgumentException if the field multiplier is out of range 0-1
     */
    public boolean createGiftsProgram(Coalition coalition, double multiplier, String description) {
        if(multiplier < 0 || multiplier > 1) throw new IllegalArgumentException("Field multiplier out of range 0-1");
        GiftsProgram giftsProgram = new GiftsProgram(coalition, multiplier, description);
        return db.getGiftsProgramsTable().add(giftsProgram);
    }

    /**
     * Deletes a giftsProgram
     *
     * @param giftsProgram the giftProgram to delete
     * @return true if the program has been deleted, false otherwise
     * @throws NullPointerException if field giftsProgram is null
     */
    private boolean deleteGiftsProgram(GiftsProgram giftsProgram) {
        if (giftsProgram == null) throw new NullPointerException("Field giftsProgram can't be null");
        RewardsController rewardsController = new RewardsController(db);
        HashMap<Shop, Set<Gift>> shopsGift = giftsProgram.getShopsGift();
        for (Map.Entry<Shop, Set<Gift>> entry : shopsGift.entrySet()){
            Set<Gift> gifts = entry.getValue();
            for (Gift gift : gifts)
                if(!rewardsController.deleteGift(gift)) return false;
        }
        return db.getGiftsProgramsTable().delete(giftsProgram);
    }

    /**
     * Adds a shop in a giftsProgram
     * @param giftsProgram the giftsProgram where to add the shop
     * @param shop the shop to add
     * @return true if the shop has been added, false otherwise
     * @throws NullPointerException if any of the fields is null
     */
    private boolean addShopToGiftsProgram(GiftsProgram giftsProgram, Shop shop){
        if(giftsProgram == null) throw new NullPointerException("Field giftsProgram can't be null");
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        return giftsProgram.addShop(shop);
    }

    /**
     * Removes a shop from a giftsProgram
     * @param giftsProgram the giftsProgram from which to remove the shop
     * @param shop the shop to remove
     * @return true if the shop has been removed, false otherwise
     * @throws NullPointerException if any of the fields is null
     */
    private boolean removeShopFromGiftsProgram(GiftsProgram giftsProgram, Shop shop){
        if(giftsProgram == null) throw new NullPointerException("Field giftsProgram can't be null");
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        RewardsController rewardsController = new RewardsController(db);
        HashMap<Shop, Set<Gift>> shopsGift = giftsProgram.getShopsGift();
        Set<Gift> gifts = shopsGift.get(shop);
        for (Gift gift : gifts)
            if(!rewardsController.deleteGift(gift)) return false;
        return giftsProgram.removeShop(shop);
    }





    /**
     * Returns the complete set of existing LevelsPrograms
     * @return the LevelsPrograms
     */
    public Set<LevelsProgram> getLevelsPrograms(){
        return db.getLevelsProgramsTable().getRecords();
    }

    /**
     * Creates a new levelsProgram
     *
     * @param coalition the coalition which the levelsProgram belongs
     * @param multiplier  the multiplier used to convert euros into points
     * @param description the description of the program
     * @return true if the program has been created, false otherwise
     * @throws NullPointerException if field multiplier is null
     */
    public boolean createLevelsProgram(Coalition coalition, double multiplier, String description) {
        if(multiplier < 0 || multiplier > 1) throw new IllegalArgumentException("Field multiplier out of range 0-1");
        LevelsProgram levelsProgram = new LevelsProgram(coalition, multiplier, description);
        return db.getLevelsProgramsTable().add(levelsProgram);
    }

    /**
     * Deletes a levelsProgram
     *
     * @param levelsProgram the levelsProgram to delete
     * @return true if the program has been deleted, false otherwise
     * @throws NullPointerException if field levelsProgram is null
     */
    private boolean deleteLevelsProgram(LevelsProgram levelsProgram) {
        if(levelsProgram == null) throw new NullPointerException("Field levelsProgram can't be null");
        LevelsController levelsController = new LevelsController(db);
        TreeSet<Level> levels = levelsProgram.getLevels();
        for (Level level : levels) levelsController.deleteLevel(level);
        return db.getLevelsProgramsTable().delete(levelsProgram);
    }

    /**
     * Adds a shop in a levelsProgram
     * @param levelsProgram the levelsProgram where to add the shop
     * @param shop the shop to add
     * @return true if the shop has been added, false otherwise
     * @throws NullPointerException if any of the fields is null
     */
    private boolean addShopToLevelsProgram(LevelsProgram levelsProgram, Shop shop){
        if(levelsProgram == null) throw new NullPointerException("Field levelsProgram can't be null");
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        LevelsController levelsController = new LevelsController(db);
        TreeSet<Level> levels = levelsProgram.getLevels();
        for(Level level : levels)
            if(!levelsController.addShopToLevel(level, shop)) return false;
        return true;
    }

    /**
     * Removes a shop from a levelsProgram
     * @param levelsProgram the levelsProgram from which to remove the shop
     * @param shop the shop to remove
     * @return true if the shop has been removed, false otherwise
     * @throws NullPointerException if any of the fields is null
     */
    private boolean removeShopFromLevelsProgram(LevelsProgram levelsProgram, Shop shop){
        if(levelsProgram == null) throw new NullPointerException("Field levelsProgram can't be null");
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        LevelsController levelsController = new LevelsController(db);
        TreeSet<Level> levels = levelsProgram.getLevels();
        for(Level level : levels)
            if(!levelsController.removeShopFromLevel(level, shop)) return false;
        return true;
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

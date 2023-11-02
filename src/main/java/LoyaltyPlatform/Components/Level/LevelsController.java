package LoyaltyPlatform.Components.Level;

import LoyaltyPlatform.Components.FidelityProgram.LevelsProgram;
import LoyaltyPlatform.Components.Reward.Discount;
import LoyaltyPlatform.Components.Reward.RewardsController;
import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Db.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/levels")
public class LevelsController {

    private final Db db;

    @Autowired
    public LevelsController(Db db) {
        this.db = db;
    }

    /**
     * Returns the complete set of existing levels
     * @return the levels
     */
    @GetMapping("/getLevels")
    public Set<Level> getLevels(){
        return db.getLevelsTable().getRecords();
    }

    /**
     * Creates a new Level
     * @param levelsProgram the levelsProgram which the level belongs
     * @param pointsThreshold the pointsThreshold
     * @return true if the Level has been created, false otherwise
     */
    @PostMapping("/createLevel")
    public boolean createLevel(@RequestBody LevelsProgram levelsProgram, @RequestParam int pointsThreshold){
        if(pointsThreshold < 0) return false;
        Level level = new Level(levelsProgram, pointsThreshold);
        return db.getLevelsTable().add(level);
    }

    /**
     * Deletes a level
     * @param level the level to delete
     * @return true if the level has been deleted, false otherwise
     */
    @DeleteMapping("/deleteLevel")
    public boolean deleteLevel(@RequestBody Level level){
        if(level == null) throw new NullPointerException("Field level can't be nul");
        RewardsController rewardsController = new RewardsController(db);
        HashMap<Shop, Set<Discount>> shopsDiscount = level.getShopsDiscount();
        for (Map.Entry<Shop, Set<Discount>> entry : shopsDiscount.entrySet()) {
            Set<Discount> discounts = entry.getValue();
            for (Discount discount : discounts)
                if(!rewardsController.deleteDiscount(discount)) return false;
        }
        return db.getLevelsTable().delete(level);
    }

    /**
     * Adds a shop to the given level
     * @param level the level from which to add a new shop
     * @param shop the shop to add
     * @return true if the shop has been added to the level, false if the shop was already present
     */
    @PostMapping("/addShopToLevel")
    public boolean addShopToLevel(@RequestBody Level level, @RequestBody Shop shop){
        if(level == null) throw new NullPointerException("Field level can't be null");
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        return level.addShop(shop);
    }

    /**
     * Removes a shop from the given level
     * @param level the level from which to remove the shop
     * @param shop the shop to remove
     * @return true if the shop has been removed, false if the shop was not found
     * @throws NullPointerException if any of the fields is null
     */
    @DeleteMapping("/removeShopFromLevel")
    public boolean removeShopFromLevel(@RequestBody Level level, @RequestBody Shop shop){
        if(level == null) throw new NullPointerException("Field level can't be null");
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        HashMap<Shop, Set<Discount>> shopsDiscount = level.getShopsDiscount();
        Set<Discount> discounts = shopsDiscount.get(shop);
        for(Discount discount : discounts)
            if(!db.getDiscountsTable().delete(discount)) return false;
        return level.removeShop(shop);
    }

}
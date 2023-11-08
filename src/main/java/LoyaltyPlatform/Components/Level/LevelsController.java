package LoyaltyPlatform.Components.Level;

import LoyaltyPlatform.Components.Reward.Discount;
import LoyaltyPlatform.Components.Reward.RewardsController;
import LoyaltyPlatform.Components.Shop.GenericShop;
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
     *
     * @return the levels
     */
    @GetMapping("/getLevels")
    public Set<Level> getLevels() {
        return db.getLevelsTable().getRecords();
    }

    /**
     * Creates a new Level
     *
     * @param pointsThreshold the pointsThreshold
     * @return true if the Level has been created, false otherwise
     */
    @PostMapping("/createLevel")
    public boolean createLevel(@RequestParam int pointsThreshold) {
        Level level = new Level(pointsThreshold);
        return db.getLevelsTable().add(level);
    }

    /**
     * Deletes a level
     *
     * @param levelId the id of the level to delete
     * @return true if the level has been deleted, false otherwise
     */
    @DeleteMapping("/deleteLevel")
    public boolean deleteLevel(@RequestParam int levelId) {
        Level level = db.getLevelsTable().getRecordById(levelId);
        if (level == null) return false;
        RewardsController rewardsController = new RewardsController(db);
        HashMap<GenericShop, Set<Discount>> shopsDiscount = level.getShopsDiscount();
        for (Map.Entry<GenericShop, Set<Discount>> entry : shopsDiscount.entrySet()) {
            Set<Discount> discounts = entry.getValue();
            for (Discount discount : discounts)
                if (!rewardsController.deleteDiscount(discount)) return false;
        }
        return db.getLevelsTable().delete(level);
    }

    /**
     * Adds a shop to the given level
     *
     * @param levelId the id of the level from which to add a new shop
     * @param shopId  the id of the shop to add
     * @return true if the shop has been added to the level, false if the shop was already present
     */
    @PostMapping("/addShopToLevel")
    public boolean addShopToLevel(@RequestParam int levelId, @RequestParam int shopId) {
        Level level = db.getLevelsTable().getRecordById(levelId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (level == null || shop == null) return false;
        return level.addShop(shop);
    }

    /**
     * Removes a shop from the given level
     *
     * @param levelId the id of the level from which to remove the shop
     * @param shopId  the id of the shop to remove
     * @return true if the shop has been removed, false if the shop was not found
     * @throws NullPointerException if any of the fields is null
     */
    @DeleteMapping("/removeShopFromLevel")
    public boolean removeShopFromLevel(@RequestParam int levelId, @RequestParam int shopId) {
        Level level = db.getLevelsTable().getRecordById(levelId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (level == null || shop == null) return false;
        HashMap<GenericShop, Set<Discount>> shopsDiscount = level.getShopsDiscount();
        Set<Discount> discounts = shopsDiscount.get(shop);
        for (Discount discount : discounts)
            if (!db.getDiscountsTable().delete(discount)) return false;
        return level.removeShop(shop);
    }

}
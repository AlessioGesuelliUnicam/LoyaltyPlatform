package LoyaltyPlatform.Components.Level;

import LoyaltyPlatform.Components.Reward.Discount;
import LoyaltyPlatform.Components.Reward.DiscountsController;
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
     * @return the new level
     */
    public Level createLevel(int pointsThreshold) {
        Level level = new Level(pointsThreshold);
        if (!db.getLevelsTable().add(level)) return null;
        return level;
    }

    /**
     * Deletes a level
     *
     * @param levelId the id of the level to delete
     * @return true if the level has been deleted, false otherwise
     */
    public boolean deleteLevel(int levelId) {
        Level level = db.getLevelsTable().getRecordById(levelId);
        if (level == null) return false;
        DiscountsController discountsController = new DiscountsController(db);
        HashMap<GenericShop, Set<Discount>> shopsDiscount = level.getShopsDiscount();
        for (Map.Entry<GenericShop, Set<Discount>> entry : shopsDiscount.entrySet()) {
            Set<Discount> discounts = entry.getValue();
            for (Discount discount : discounts)
                if (!discountsController.deleteDiscount(discount.getId())) return false;
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
    public boolean addShopToLevel(int levelId, int shopId) {
        Level level = db.getLevelsTable().getRecordById(levelId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (level == null) return false;
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
    public boolean removeShopFromLevel(int levelId, int shopId) {
        Level level = db.getLevelsTable().getRecordById(levelId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (level == null || shop == null) return false;
        HashMap<GenericShop, Set<Discount>> shopsDiscount = level.getShopsDiscount();
        Set<Discount> discounts = shopsDiscount.get(shop);
        for (Discount discount : discounts)
            if (!db.getDiscountsTable().delete(discount)) return false;
        return level.removeShop(shop);
    }

    /**
     * Adds a discount for a shop in a level
     *
     * @param levelId            the id of the level
     * @param shopId             the id of the shop
     * @param label              the label of the discount
     * @param percentageDiscount the percentageDiscount of the discount
     * @return true if the discount has been added, false otherwise
     */
    @PostMapping("/addShopDiscountToLevel")
    public boolean addShopDiscountToLevel(@RequestParam int levelId, @RequestParam int shopId, @RequestParam String label, @RequestParam int percentageDiscount) {
        Level level = db.getLevelsTable().getRecordById(levelId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (level == null) return false;
        DiscountsController discountsController = new DiscountsController(db);
        Discount discount = discountsController.createDiscount(label, percentageDiscount);
        return level.addShopDiscount(shop, discount);
    }

    /**
     * Removes a discount of a shop in a level
     *
     * @param levelId    the id of the level
     * @param shopId     the id of the shop
     * @param discountId the label of the discount
     * @return true if the discount has been removed, false otherwise
     */
    @PostMapping("/removeShopDiscountFromLevel")
    public boolean removeShopDiscountFromLevel(@RequestParam int levelId, @RequestParam int shopId, @RequestParam int discountId) {
        Level level = db.getLevelsTable().getRecordById(levelId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        Discount discount = db.getDiscountsTable().getRecordById(discountId);
        if (level == null) return false;
        DiscountsController discountsController = new DiscountsController(db);
        if (!level.removeShopDiscount(shop, discount)) return false;
        return discountsController.deleteDiscount(discount.getId());
    }

}
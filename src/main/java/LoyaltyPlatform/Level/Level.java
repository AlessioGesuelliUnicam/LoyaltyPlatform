package LoyaltyPlatform.Level;

import LoyaltyPlatform.Reward.Discount;
import LoyaltyPlatform.Shop.Shop;

import java.util.HashMap;
import java.util.List;

/**
 * A level represents a collection of unlockable discounts
 * upon reaching a specific points threshold.
 */
public class Level {

    private final int id;
    private final int pointsThreshold;
    private HashMap<Shop, List<Discount>> discountMap;

    public Level(int id, int pointsThreshold){
        this.id = id;
        this.pointsThreshold = pointsThreshold;
    }

    /**
     * Returns the id of the level
     * @return the id of the level
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the points threshold
     * @return the points threshold
     */
    public int getPointsThreshold() {
        return pointsThreshold;
    }
}

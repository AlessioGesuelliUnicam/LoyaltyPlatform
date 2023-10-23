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

    private final int pointsThreshold;
    private HashMap<Shop, List<Discount>> discountMap;

    public Level(int pointsThreshold){
        this.pointsThreshold = pointsThreshold;
    }

    /**
     * Returns the points threshold
     * @return the points threshold
     */
    public int getPointsThreshold() {
        return pointsThreshold;
    }
}

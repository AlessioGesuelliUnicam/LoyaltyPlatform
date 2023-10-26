package LoyaltyPlatform.Components.Level;

import LoyaltyPlatform.Components.Reward.Discount;
import LoyaltyPlatform.Components.Shop.Shop;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * A level represents a collection of unlockable discounts
 * upon reaching a specific points threshold.
 */
public class Level {

    private static int idCounter = 0;
    private final int id;
    private final int pointsThreshold;
    private HashMap<Shop, List<Discount>> discountMap;

    public Level(int pointsThreshold) {
        id = idCounter;
        idCounter++;
        this.pointsThreshold = pointsThreshold;
    }

    /**
     * Returns the id of the level
     *
     * @return the id of the level
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the points threshold
     *
     * @return the points threshold
     */
    public int getPointsThreshold() {
        return pointsThreshold;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Level)) return false;
        Level level = (Level) o;
        return this.id == level.id;
    }

    public int hashCode() {
        return Objects.hash(id);
    }
}

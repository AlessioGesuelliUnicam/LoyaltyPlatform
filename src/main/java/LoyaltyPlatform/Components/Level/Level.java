package LoyaltyPlatform.Components.Level;

import LoyaltyPlatform.Components.Reward.Discount;
import LoyaltyPlatform.Components.Shop.Shop;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A level represents a collection of unlockable discounts
 * upon reaching a specific points threshold.
 */
public class Level {

    private static int idCounter = 0;
    private final int id;
    private final int pointsThreshold;
    private HashMap<Shop, Set<Discount>> shopsDiscount;

    public Level(int pointsThreshold, Set<Shop> shops){
        if(pointsThreshold < 0) throw new IllegalArgumentException("Field pointThreshold must be positive");
        id = idCounter;
        idCounter++;
        this.pointsThreshold = pointsThreshold;
        shopsDiscount = new HashMap<>();
        for(Shop shop: shops) addShop(shop);
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

    /**
     * Returns all the shops in this level and the associated discounts
     * @return the shops discount
     */
    public HashMap<Shop, Set<Discount>> getShopsDiscount(){
        return shopsDiscount;
    }

    /**
     * Adds a shop to the list of shops of the level with an empty set of discounts
     * @param shop the shop to add
     * @return true if the shop is added, false if the shop was already present
     * @throws NullPointerException if the given shop is null
     */
    public boolean addShop(Shop shop){
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(shopsDiscount.containsKey(shop)) return false;
        shopsDiscount.put(shop, new HashSet<>());
        return true;
    }

    /**
     * Removes a shop from the list of shops of the level
     * @param shop the shop to remove
     * @return true if the shop has been removed, false if the shop was not found
     * @throws NullPointerException if the given shop is null
     */
    public boolean deleteShop(Shop shop){
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(!shopsDiscount.containsKey(shop)) return false;
        shopsDiscount.remove(shop);
        return true;
    }

    /**
     * Adds a discount in a given shop
     * @param shop the shop on which to add the discount
     * @param discount the discount to add
     * @return true if the discounts of the shop has been added, false otherwise
     * @throws NullPointerException if any of the fields is null
     * @throws IllegalArgumentException if the shop doesn't belong to this level
     */
    public boolean addShopDiscount(Shop shop, Discount discount){
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(discount == null) throw new NullPointerException("Field discount can't be null");
        if(!shopsDiscount.containsKey(shop)) throw new IllegalArgumentException(("Field shop is invalid"));
        return shopsDiscount.get(shop).add(discount);
    }

    /**
     * Removes a discount in a given shop
     * @param shop the shop on which to remove the discount
     * @param discount the discount to remove
     * @return true if the discount of the shop has been removed, false otherwise
     * @throws NullPointerException if any of the fields is null
     * @throws IllegalArgumentException if the shop doesn't belong to this level
     */
    public boolean removeShopDiscount(Shop shop, Discount discount){
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(discount == null) throw new NullPointerException("Field discount can't be null");
        if(!shopsDiscount.containsKey(shop)) throw new IllegalArgumentException(("Field shop is invalid"));
        return shopsDiscount.get(shop).remove(discount);
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

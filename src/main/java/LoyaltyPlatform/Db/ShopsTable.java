package LoyaltyPlatform.Db;

import LoyaltyPlatform.Shop.Shop;
import java.util.HashSet;

public class ShopsTable implements Table<Shop>{

    private HashSet<Shop> shops;

    public ShopsTable() {
        shops = new HashSet<Shop>();
    }

    /**
     * Returns the collection of shop
     * @return the collection
     */
    public HashSet<Shop> getRecords() {
        return shops;
    }

    /**
     * Adds a Shop to the collection
     * @param record the Shop to add
     */
    public void add(Shop record) {
        shops.add(record);
    }

    /**
     * Removes a Shop from the collection
     * @param record the Shop to remove
     */
    public void remove(Shop record) {
        shops.remove(record);
    }
}

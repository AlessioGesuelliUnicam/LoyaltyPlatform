package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.Reward.Discount;

import java.util.HashSet;


public class DiscountsTable implements Table<Discount> {

    private HashSet<Discount> discounts;

    public DiscountsTable() {
        discounts = new HashSet<>();
    }

    /**
     * Returns the collection of discounts
     *
     * @return the collection
     */
    public HashSet<Discount> getRecords() {
        return discounts;
    }

    /**
     * Adds a Discount to the collection
     *
     * @param record the Discount to add
     * @return true if the Discount has been added, false otherwise
     */
    public boolean add(Discount record) {
        if (record == null) return false;
        return discounts.add(record);
    }

    /**
     * Removes a Discount from the collection
     *
     * @param record the Discount to remove
     * @return true if the Discount has been removed, false otherwise
     */
    public boolean remove(Discount record) {
        if (record == null) return false;
        return discounts.remove(record);
    }

    /**
     * Finds a Discount by id
     *
     * @param id the id of the Discount
     * @return the Discount if found
     */
    public Discount findById(int id) {
        for (Discount discount : discounts) {
            if (discount.getId() == id) return discount;
        }
        return null;
    }

}

package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.Reward.Discount;

import java.util.HashSet;


public class DiscountsTable implements Table<Discount> {

    private HashSet<Discount> discounts;

    public DiscountsTable(){
        discounts = new HashSet<>();
    }

    /**
     * Returns the collection of discounts
     * @return the collection
     */
    public HashSet<Discount> getRecords() {
        return discounts;
    }

    /**
     * Adds a Discount to the collection
     * @param record the Discount to add
     */
    public void add(Discount record) {
        discounts.add(record);
    }

    /**
     * Removes a Discount from the collection
     * @param record the Discount to remove
     */
    public void remove(Discount record) {
        discounts.remove(record);
    }

    /**
     * Finds a Discount by id
     * @param id the id of the Discount
     * @return the Discount if found
     */
    public Discount findById(int id) {
        for(Discount discount: discounts){
            if (discount.getId() == id) return discount;
        }
        return null;
    }

}
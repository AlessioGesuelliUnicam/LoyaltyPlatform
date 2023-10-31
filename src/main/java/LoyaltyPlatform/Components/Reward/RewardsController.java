package LoyaltyPlatform.Components.Reward;

import LoyaltyPlatform.Db.Db;

public class RewardsController {

    private final Db db;

    public RewardsController(Db db) {
        this.db = db;
    }


    /**
     * Creates a new Gift
     *
     * @param label           the label of the Gift
     * @param necessaryPoints the necessaryPoints to redeem the Gift
     * @param addition        the addition to redeem the Gift
     * @return true if the Gift has been created, false otherwise
     */
    public boolean createGift(String label, int necessaryPoints, double addition) {
        if (label == null) return false;
        if (necessaryPoints < 0) return false;
        if (addition < 0) return false;
        Gift gift = new Gift(label, necessaryPoints, addition);
        return db.getGiftsTable().add(gift);
    }

    /**
     * Deletes a gift
     *
     * @param gift the gift to delete
     * @return true if the gift has been deleted, false otherwise
     */
    public boolean deleteGift(Gift gift) {
        if (gift == null) throw new NullPointerException("Field gift can't be null");
        return db.getGiftsTable().delete(gift);
    }

    /**
     * Creates a new Discount
     *
     * @param label              the label of the Discount
     * @param percentageDiscount the percentageDiscount
     * @return true if the Discount has been created, false otherwise
     */
    public boolean createDiscount(String label, int percentageDiscount) {
        if (label == null) return false;
        if (percentageDiscount < 0 || percentageDiscount > 100) return false;
        Discount discount = new Discount(label, percentageDiscount);
        return db.getDiscountsTable().add(discount);
    }

    /**
     * Deletes a discount
     *
     * @param discount the discount to delete
     * @return true if the discount has been deleted, false otherwise
     */
    public boolean deleteDiscount(Discount discount) {
        if (discount == null) throw new NullPointerException("Field discount can't be null");
        return db.getDiscountsTable().delete(discount);
    }

}

package LoyaltyPlatform.Components.Reward;

import LoyaltyPlatform.Db.Db;

public class RewardController {

    private final Db db;

    public RewardController(Db db) {
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
     * Deletes a Gift
     *
     * @param id the id of the Gift to delete
     * @return true if the Gift has been deleted, false otherwise
     */
    public boolean deleteGift(int id) {
        if (id < 0) return false;
        return db.getGiftsTable().remove(db.getGiftsTable().findById(id));
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
     * Deletes a Discount
     *
     * @param id the id of the Discount to delete
     * @return true if the Discount has been deleted, false otherwise
     */
    public boolean deleteDiscount(int id) {
        if (id < 0) return false;
        return db.getDiscountsTable().remove(db.getDiscountsTable().findById(id));
    }

}

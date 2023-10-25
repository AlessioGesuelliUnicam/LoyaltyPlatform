package LoyaltyPlatform.Components.Reward;

/**
 * A discount represents a percentage deduction on a product for sale
 */
public class Discount extends GenericReward {

    private int percentageDiscount;

    public Discount(String label, int percentageDiscount) {
        super(label);
        this.percentageDiscount = percentageDiscount;
    }

    /**
     * Returns the percentage of the discount
     *
     * @return the percentage of the discount
     */
    public int getPercentageDiscount() {
        return percentageDiscount;
    }

    /**
     * Sets the percentage discount for the discount
     *
     * @param percentageDiscount the percentage discount
     */
    public void setPercentageDiscount(int percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

}

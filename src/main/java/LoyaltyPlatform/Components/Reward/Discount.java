package LoyaltyPlatform.Components.Reward;

/**
 * A discount represents a percentage deduction on a product for sale
 */
public class Discount extends GenericReward {

    private int percentageDiscount;

    public Discount(String label, int percentageDiscount) {
        super(label);
        if(percentageDiscount < 0 || percentageDiscount > 100) throw new IllegalArgumentException("Field percentageDiscount out of range 0-100");
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
     * @throws IllegalArgumentException when the given percentage discount is out of range 0-100
     */
    public void setPercentageDiscount(int percentageDiscount) {
        if(percentageDiscount < 0 || percentageDiscount > 100) throw new IllegalArgumentException("Field percentageDiscount out of range 0-100");
        this.percentageDiscount = percentageDiscount;
    }

}

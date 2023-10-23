package LoyaltyPlatform.Reward;

/**
 * A discount represents a percentage deduction on a product for sale
 */
public class Discount implements Reward {

    private final int id;
    private String label;
    private int percentageDiscount;

    public Discount(int id, String label, int percentageDiscount) {
        this.id = id;
        this.label = label;
        this.percentageDiscount = percentageDiscount;
    }

    /**
     * Returns the ID of the reward
     * @return the ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the label of the reward
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the label for the reward
     * @param label the new label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Returns the percentage of the discount
     * @return the percentage of the discount
     */
    public int getPercentageDiscount() {
        return percentageDiscount;
    }

    /**
     * Sets the percentage discount for the discount
     * @param percentageDiscount the percentage discount
     */
    public void setPercentageDiscount(int percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

}

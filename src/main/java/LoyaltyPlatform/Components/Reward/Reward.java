package LoyaltyPlatform.Components.Reward;

public interface Reward {

    /**
     * Returns the ID of the reward
     * @return the ID
     */
    int getId();

    /**
     * Returns the label of the reward
     * @return the label
     */
    String getLabel();

    /**
     * Sets the label for the reward
     * @param label the new label
     */
    void setLabel(String label);

}

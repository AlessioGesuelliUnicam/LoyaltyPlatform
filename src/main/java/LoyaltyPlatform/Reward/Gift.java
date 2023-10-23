package LoyaltyPlatform.Reward;

public class Gift implements Reward {

    private final int id;
    private String label;
    private int necessaryPoints;
    private double addition;


    public Gift(int id, String label, int necessaryPoints, double addition) {
        this.id = id;
        this.label = label;
        this.necessaryPoints = necessaryPoints;
        this.addition = addition;
    }


    /**
     * Returns the ID of the reward
     * @return the ID
     */
    public int getId() {
        return id;
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
     * Returns the number of necessary points to redeem the reward
     * @return the number of necessary points
     */
    public int getNecessaryPoints() {
        return necessaryPoints;
    }

    /**
     * Sets the number of necessary points to redeem the reward
     * @param necessaryPoints the number of necessary points
     */
    public void setNecessaryPoints(int necessaryPoints) {
        this.necessaryPoints = necessaryPoints;
    }

    /**
     * Retuns the necessary addition in euros to be paid to redeem the reward
     * @return the addition
     */
    public double getAddition() {
        return addition;
    }

    /**
     * Sets the necessary addition in euros to be paid to redeem the reward
     * @param addition the necessary addition
     */
    public void setAddition(double addition) {
        this.addition = addition;
    }
}

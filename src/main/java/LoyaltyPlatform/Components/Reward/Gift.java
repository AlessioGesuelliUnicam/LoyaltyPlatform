package LoyaltyPlatform.Components.Reward;

public class Gift extends GenericReward {
    private int necessaryPoints;
    private double addition;

    public Gift(String label, int necessaryPoints, double addition) {
        super(label);
        this.necessaryPoints = necessaryPoints;
        this.addition = addition;
    }

    /**
     * Returns the number of necessary points to redeem the reward
     *
     * @return the number of necessary points
     */
    public int getNecessaryPoints() {
        return necessaryPoints;
    }

    /**
     * Sets the number of necessary points to redeem the reward
     *
     * @param necessaryPoints the number of necessary points
     */
    public void setNecessaryPoints(int necessaryPoints) {
        this.necessaryPoints = necessaryPoints;
    }

    /**
     * Retuns the necessary addition in euros to be paid to redeem the reward
     *
     * @return the addition
     */
    public double getAddition() {
        return addition;
    }

    /**
     * Sets the necessary addition in euros to be paid to redeem the reward
     *
     * @param addition the necessary addition
     */
    public void setAddition(double addition) {
        this.addition = addition;
    }
}

package LoyaltyPlatform.Components.Reward;

public class Gift extends GenericReward {
    private int necessaryPoints;
    private double addition;

    public Gift(String label, int necessaryPoints, double addition) {
        super(label);
        if(necessaryPoints < 1) throw new IllegalArgumentException("Field necessaryPoints can't be less than 1");
        if(addition < 0) throw new IllegalArgumentException("Field addition must be positive");
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
     * @throws IllegalArgumentException when the given necessaryPoints is negative
     */
    public void setNecessaryPoints(int necessaryPoints) {
        if(necessaryPoints < 1) throw new IllegalArgumentException("Field necessaryPoints can't be less than 1");
        this.necessaryPoints = necessaryPoints;
    }

    /**
     * Returns the necessary addition in euros to be paid to redeem the reward
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
     * @throws IllegalArgumentException when the given addition is negative
     */
    public void setAddition(double addition) {
        if(addition < 0) throw new IllegalArgumentException("Field addition must be positive");
        this.addition = addition;
    }
}

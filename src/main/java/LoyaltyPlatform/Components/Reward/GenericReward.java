package LoyaltyPlatform.Components.Reward;

public abstract class GenericReward implements Reward{

    private static int idCounter = 0;
    private final int id;
    private String label;


    public GenericReward(String label){
        if(label == null) throw new NullPointerException("Field label can't be null");
        this.id = idCounter;
        idCounter++;
        this.label = label;
    }

    /**
     * Returns the ID of the Generic reward
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the label of the Generic reward
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the label for the Generic reward
     * @param label the new label
     * @throws NullPointerException when the given label is null
     */
    public void setLabel(String label) {
        if(label == null) throw new NullPointerException("Field label can't be null");
        this.label = label;
    }

}

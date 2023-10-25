package LoyaltyPlatform.Components.FidelityProgram;

public abstract class GenericFidelityProgram implements FidelityProgram {

    private static int idCounter = 0;
    private final int id;
    private final double multiplier;
    private String description;

    public GenericFidelityProgram(double multiplier, String description) {
        id = idCounter;
        idCounter++;
        this.multiplier = multiplier;
        this.description = description;
    }

    /**
     * Returns the ID of the FidelityProgram
     * @return the ID
     */
    public int getId(){
        return id;
    }

    /**
     * Returns the multiplier used to convert Euros into Points
     * @return the multiplier
     */
    public double getMultiplier(){
        return multiplier;
    }

    /**
     * Returns the description of the GenericFidelityProgram
     * @return the description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Sets the description for the GenericFidelityProgram
     * @param description the new description
     */
    public void setDescription(String description){
        this.description = description;
    }


}

package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Coalition.Coalition;

public interface FidelityProgram {

    /**
     * Returns the ID of the FidelityProgram
     * @return the ID
     */
    int getId();

    /**
     * Returns the coalition which the program belongs
     * @return the coalition
     */
    public Coalition getCoalition();

    /**
     * Returns the multiplier used to convert Euros into Points
     * @return the multiplier
     */
    double getMultiplier();

    /**
     * Returns the description of the FidelityProgram
     * @return the description
     */
    String getDescription();

    /**
     * Sets the description for the FidelityProgram
     * @param description the new description
     */
    void setDescription(String description);

}

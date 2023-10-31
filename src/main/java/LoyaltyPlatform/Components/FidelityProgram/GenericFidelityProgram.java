package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Coalition.Coalition;
import LoyaltyPlatform.Components.Level.Level;
import LoyaltyPlatform.Components.Shop.Shop;

public abstract class GenericFidelityProgram implements FidelityProgram {

    private static int idCounter = 0;
    private final int id;
    private final Coalition coalition;
    private final double multiplier;
    private String description;


    public GenericFidelityProgram(Coalition coalition, double multiplier, String description) {
        if(multiplier < 0 || multiplier > 1) throw new IllegalArgumentException("Field multiplier out of range 0-1");
        id = idCounter;
        idCounter++;
        this.coalition = coalition;
        this.multiplier = multiplier;
        this.description = description;
    }

    /**
     * Returns the ID of the FidelityProgram
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the coalition which the program belongs
     * @return the coalition
     */
    public Coalition getCoalition(){
        return coalition;
    }

    /**
     * Returns the multiplier used to convert Euros into Points
     *
     * @return the multiplier
     */
    public double getMultiplier() {
        return multiplier;
    }

    /**
     * Returns the description of the GenericFidelityProgram
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the GenericFidelityProgram
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof GenericFidelityProgram)) return false;
        GenericFidelityProgram fidelityProgram = (GenericFidelityProgram) o;
        return this.id == fidelityProgram.id;
    }

}

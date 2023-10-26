package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Level.Level;

import java.util.List;

/**
 * A LevelsProgram represents a GenericFidelityProgram
 * where a list of levels is provided
 */
public class LevelsProgram extends GenericFidelityProgram {

    private List<Level> levels;

    public LevelsProgram(double multiplier, String description) {
        super(multiplier, description);
    }


}

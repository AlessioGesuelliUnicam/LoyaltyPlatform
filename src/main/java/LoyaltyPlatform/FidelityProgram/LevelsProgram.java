package LoyaltyPlatform.FidelityProgram;

import LoyaltyPlatform.Level.Level;

import java.util.List;

/**
 * A LevelsProgram represents a GenericFidelityProgram
 * where a list of levels is provided
 */
public class LevelsProgram extends GenericFidelityProgram {

    private List<Level> levels;

    public LevelsProgram(int id, double multiplier, String description) {
        super(id, multiplier, description);
    }

}

package LoyaltyPlatform.Model;

import java.util.List;

public class LevelsProgram extends GenericFidelityProgram {

    private List<Level> levels;

    public LevelsProgram(int id, double multiplier, String description) {
        super(id, multiplier, description);
    }

}

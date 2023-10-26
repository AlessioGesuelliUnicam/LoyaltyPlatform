package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Level.Level;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * A LevelsProgram represents a GenericFidelityProgram
 * where a list of levels is provided
 */
public class LevelsProgram extends GenericFidelityProgram {

    private TreeSet<Level> levels;

    public LevelsProgram(double multiplier, String description) {
        super(multiplier, description);
        levels = new TreeSet<>(Comparator.comparing(Level::getPointsThreshold));
    }

    /**
     * Returns the levels of the program
     *
     * @return the levels
     */
    public TreeSet<Level> getLevels(){
        return levels;
    }

    /**
     * Adds a level to the list of levels of the levelsProgram
     *
     * @param level the level to add
     * @return true if the level has been added, false otherwise
     * @throws NullPointerException if th given level is null
     */
    public boolean addLevel(Level level){
        if(level == null) throw new NullPointerException("Field level can't be null");
        return levels.add(level);
    }

    /**
     * Adds a level to the list of levels of the levelsProgram
     *
     * @param level the level to add
     * @return true if the level has been added, false otherwise
     * @throws NullPointerException if th given level is null
     */
    public boolean deleteLevel(Level level){
        if(level == null) throw new NullPointerException("Field level can't be null");
        return levels.remove(level);
    }


}

package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Level.Level;
import LoyaltyPlatform.Components.Shop.Shop;

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
     * Adds a shop in all the lists of shop in the levels of this program
     * @param shop the shop to add
     * @return true if the shop has been added, false otherwise
     * @throws NullPointerException if the given shop is null
     */
    public boolean addShop(Shop shop){
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        for(Level level : levels)
            if(!level.addShop(shop)) return false;
        return true;
    }

    /**
     * Removes a shop from all the lists of shop in the levels of this program
     * @param shop the shop to add
     * @return true if the shop has been removed, false otherwise
     * @throws NullPointerException if the given shop is null
     */
    public boolean removeShop(Shop shop){
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        for(Level level : levels)
            if(!level.removeShop(shop)) return false;
        return true;
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

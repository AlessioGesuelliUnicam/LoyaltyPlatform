package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Db.Db;

public class FidelityProgramController {

    private final Db db;

    public FidelityProgramController(Db db){
        this.db = db;
    }

    /**
     * Creates a new Levels Program
     * @param multiplier the multiplier used to convert euros into points
     * @param description the description of the program
     * @return true if the program has been created, false otherwise
     */
    boolean createLevelsProgram(double multiplier, String description){
        LevelsProgram levelsProgram = new LevelsProgram(multiplier, description);
        return db.getLevelsProgramsTable().add(levelsProgram);
    }

    /**
     * Creates a new Gifts Program
     * @param multiplier the multiplier used to convert euros into points
     * @param description the description of the program
     * @return true if the program has been created, false otherwise
     */
    boolean createGiftsProgram(double multiplier, String description){
        GiftsProgram giftsProgram = new GiftsProgram(multiplier, description);
        return db.getGiftsProgramsTable().add(giftsProgram);
    }

}

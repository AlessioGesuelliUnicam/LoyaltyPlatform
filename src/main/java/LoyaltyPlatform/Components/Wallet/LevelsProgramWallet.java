package LoyaltyPlatform.Components.Wallet;


import LoyaltyPlatform.Components.Coalition.GenericCoalition;
import LoyaltyPlatform.Components.Level.Level;
import LoyaltyPlatform.Components.User.Client;

/**
 * This class represents the wallet of a client in a level program
 */
public class LevelsProgramWallet extends GenericWallet {

    private Level level;

    public LevelsProgramWallet(int id, GenericCoalition coalition, Client client, int points, Level level) {
        super(id, coalition, client, points);
        this.level = level;
    }


    /**
     * Returns the level of the wallet
     *
     * @return level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Set the level of the wallet
     *
     * @param level
     */
    public void setLevel(Level level) {
        this.level = level;
    }
}

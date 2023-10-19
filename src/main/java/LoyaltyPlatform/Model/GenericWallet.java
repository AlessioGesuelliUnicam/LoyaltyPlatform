package LoyaltyPlatform.Model;

import LoyaltyPlatform.Model.Interface.Coalition;
import LoyaltyPlatform.Model.Interface.Wallet;

/**
 * This class is the abstract class for the different types of wallets
 */
public abstract class GenericWallet implements Wallet {
    private int id;
    private Coalition coalition;
    private Client client;
    private int points;

    /**
     * Return the Id of the wallet
     *
     * @return Id
     */
    public abstract int getId();

    /**
     * Return the coalition of the wallet
     *
     * @return coalition
     */
    public abstract Coalition getCoalition();

    /**
     * Set the coalition of the wallet
     *
     * @param coalition
     */
    public abstract void setCoalition(Coalition coalition);

    /**
     * Return the owner of the wallet
     *
     * @return client
     */
    public abstract Client getClient();

    /**
     * Set the owner of the wallet
     *
     * @param client
     */
    public abstract void setClient(Client client);

    /**
     * Return the points of the wallet
     *
     * @return points
     */
    public abstract int getPoints();

    /**
     * Set the points of the wallet
     *
     * @param points
     */
    public abstract void setPoints(int points);
}

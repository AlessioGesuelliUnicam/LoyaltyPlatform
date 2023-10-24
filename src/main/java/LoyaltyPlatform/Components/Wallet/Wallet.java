package LoyaltyPlatform.Components.Wallet;

import LoyaltyPlatform.Components.Coalition.Coalition;
import LoyaltyPlatform.Components.User.Client;

/**
 * Interface for the wallet
 */
public interface Wallet {
    /**
     * Return the ID of the wallet
     *
     * @return Id
     */
    int getId();

    /**
     * Return the coalition of the wallet
     *
     * @return coalition
     */
    Coalition getCoalition();

    /**
     * Set the coalition of the wallet
     *
     * @param coalition
     */
    void setCoalition(Coalition coalition);

    /**
     * Return the owner of the wallet
     *
     * @return client
     */
    Client getClient();

    /**
     * Set the owner of the wallet
     *
     * @param client
     */
    void setClient(Client client);

    /**
     * Return the points of the wallet
     *
     * @return points
     */
    int getPoints();

    /**
     * Set the points of the wallet
     *
     * @param points
     */
    void setPoints(int points);

}

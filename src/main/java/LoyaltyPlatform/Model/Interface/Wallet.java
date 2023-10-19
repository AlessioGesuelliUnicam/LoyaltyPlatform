package LoyaltyPlatform.Model.Interface;

import LoyaltyPlatform.Model.Client;

public interface Wallet {
    /**
     * Return the ID of the wallet
     * @return ID
     */
    int getID();
    /**
     * Return the coalition of the wallet
     * @return coalition
     */
    Coalition getCoalition();
    /**
     * Set the coalition of the wallet
     * @param coalition
     */
    void setCoalition(Coalition coalition);
    /**
     * Return the owner of the wallet
     * @return client
     */
    Client getClient();
    /**
     * Set the owner of the wallet
     * @param client
     */
    void setClient(Client client);
    /**
     * Return the points of the wallet
     * @return points
     */
    int getPoints();
    /**
     * Set the points of the wallet
     * @param points
     */
    void setPoints(int points);

}

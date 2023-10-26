package LoyaltyPlatform.Components.Wallet;

import LoyaltyPlatform.Components.Coalition.Coalition;
import LoyaltyPlatform.Components.User.Client;

/**
 * A generic wallet keeps track of the points
 * earned by a client into a coalition
 */
public abstract class GenericWallet implements Wallet {
    private final int id;
    private Coalition coalition;
    private Client client;
    private int points;

    public GenericWallet(int id, Coalition coalition, Client client, int points) {
        this.id = id;
        this.coalition = coalition;
        this.client = client;
        this.points = points;
    }

    /**
     * Returns the id of the wallet
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    ;

    /**
     * Return the coalition of the wallet
     *
     * @return coalition
     */
    public Coalition getCoalition() {
        return coalition;
    }

    ;

    /**
     * Set the coalition of the wallet
     *
     * @param coalition
     */
    public void setCoalition(Coalition coalition) {
        this.coalition = coalition;
    }

    ;

    /**
     * Return the owner of the wallet
     *
     * @return client
     */
    public Client getClient() {
        return client;
    }

    ;

    /**
     * Set the owner of the wallet
     *
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    ;

    /**
     * Return the points of the wallet
     *
     * @return points
     */
    public int getPoints() {
        return points;
    }

    ;

    /**
     * Set the points of the wallet
     *
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof GenericWallet)) return false;
        GenericWallet wallet = (GenericWallet) o;
        return this.id == wallet.id;
    }
}

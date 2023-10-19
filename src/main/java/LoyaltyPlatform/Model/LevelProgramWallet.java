package LoyaltyPlatform.Model;

import LoyaltyPlatform.Model.Interface.Coalition;


/**
 * This class represents the wallet of a client in a level program
 */
public class LevelProgramWallet extends GenericWallet {
    private final int id;
    private Coalition coalition;
    private Client client;
    private int points;

    public LevelProgramWallet(int id, GenericCoalition coalition, Client client, int points) {
        this.id = id;
        this.coalition = coalition;
        this.client = client;
        this.points = points;
    }

    /**
     * Return the Id of the wallet
     *
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * Return the coalition of the wallet
     *
     * @return coalition
     */
    public Coalition getCoalition() {
        return coalition;
    }

    /**
     * Set the coalition of the wallet
     *
     * @param coalition
     */
    public void setCoalition(Coalition coalition) {
        this.coalition = coalition;
    }


    /**
     * Return the owner of the wallet
     *
     * @return client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Set the owner of the wallet
     *
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Return the points of the wallet
     *
     * @return points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Set the points of the wallet
     *
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }
}

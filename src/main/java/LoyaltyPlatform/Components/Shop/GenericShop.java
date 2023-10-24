package LoyaltyPlatform.Components.Shop;

import LoyaltyPlatform.Components.Coalition.Coalition;
import LoyaltyPlatform.Components.User.Owner;

/**
 * A Generic Shop represent the individual shop of the Loyalty Platform
 */
public class GenericShop implements Shop {
    private final int id;
    private String partitaIva;
    private String name;
    private Owner owner;
    private Coalition coalition;

    public GenericShop(int id, String partitaIva, String name, Owner owner, Coalition coalition) {
        this.id = id;
        this.partitaIva = partitaIva;
        this.name = name;
        this.owner = owner;
        this.coalition = coalition;
    }


    /**
     * Return the ID of the Generic User
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Return the Partita Iva of the Shop
     * @return the Partita Iva
     */
    public String getPartitaIva() {
        return partitaIva;
    }

    /**
     * Return the Name of the Shop
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name for the Shop
     * @param name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Owner of the Shop
     * @return the Owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Sets the Owner for the Shop
     * @param Owner the Owner to set
     */
    public void setOwner(Owner Owner) {
        this.owner = owner;
    }

    /**
     * Return Coalition of the Shop
     * @return the Coalition
     */
    public Coalition getCoalition() {
        return coalition;
    }

    /**
     * Sets the Coalition for the Shop
     * @param Coalition the Coalition to set
     */
    public void setCoalition(Coalition Coalition) {
        this.coalition = coalition;
    }
}
package LoyaltyPlatform.Components.Shop;

import LoyaltyPlatform.Components.Coalition.Coalition;
import LoyaltyPlatform.Components.User.Owner;

public interface Shop {
    /**
     *  Return the ID of the User
     *  @return the ID
     */
    int getId();

    /**
     * Return the Partita Iva of the Shop
     * @return the Partita Iva
     */
    String getPartitaIva();

    /**
     * Return the Name of the Shop
     * @return the Name
     */
    String getName();

    /**
     * Sets the Name for the Shop
     * @param name the Name to set
     */
    void setName(String name);

    /**
     * Return the Owner of the Shop
     * @return the Owner
     */
    Owner getOwner();

    /**
     * Sets the Owner for the Shop
     * @param Owner the Owner to set
     */
    void setOwner(Owner Owner);

    /**
     * Return Coalition of the Shop
     * @return the Coalition
     */
    Coalition getCoalition();

    /**
     * Sets the Coalition for the Shop
     * @param Coalition the Coalition to set
     */
    void setCoalition(Coalition Coalition);

}
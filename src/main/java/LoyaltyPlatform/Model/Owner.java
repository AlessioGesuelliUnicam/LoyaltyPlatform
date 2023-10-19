package LoyaltyPlatform.Model;

import LoyaltyPlatform.Model.Interface.Shop;
import LoyaltyPlatform.Model.Interface.User;

/**
 * An Owner represent the head of a single shop.
 */
public class Owner implements User {
    private final int id;
    private String name;
    private String surname;
    private String email;
    private Shop shop;

    public Owner(int id, String name, String surname, String email, Shop shop) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.shop = shop;
    }

    /**
     * Return the ID of the Owner
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Return the Name of the Owner
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name for the Owner
     * @param name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Surname of the Owner
     * @return the Surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the Surname for the Owner
     * @param surname the Surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Return the Email of the Owner
     * @return the Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the Email for the Owner
     * @param email the Email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return the Shop of the Owner
     * @return the Shop
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * Sets the Shop for the Owner
     * @param Shop the Shop to set
     */
    public void setShop(Shop Shop) {
        this.shop = shop;
    }
}
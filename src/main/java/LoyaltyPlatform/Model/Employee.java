package LoyaltyPlatform.Model;

import LoyaltyPlatform.Model.Interface.User;
import LoyaltyPlatform.Model.Interface.Shop;

/**
 * An Employee represent the worker of one of the shops.
 */
public class Employee implements User {
    private final int id;
    private String name;
    private String surname;
    private String email;
    private Shop shop;

    public Employee(int id, String name, String surname, String email, Shop shop) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.shop = shop;
    }


    /**
     * Return the ID of the Employee
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Return the Name of the Employee
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name for the Employee
     * @param name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Surname of the Employee
     * @return the Surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the Surname for the Employee
     * @param surname the Surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Return the Email of the Employee
     * @return the Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the Email for the Employee
     * @param email the Email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return the Shop of the Employee
     * @return the Shop
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * Sets the Shop of the Employee
     * @param Shop the Shop to set
     */
    public void setShop(Shop Shop) {
        this.shop = shop;
    }
}

package LoyaltyPlatform.Model;

import LoyaltyPlatform.Model.Interface.User;

/**
 * A Client represent the Final Consumer of the Loyalty Platform
 */
public class Client implements User {
    private final int id;
    private String name;
    private String surname;
    private String email;
    private int telephoneNumber;

    public Client(int id, String name, String surname, String email, int telephoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Return the ID of the Client
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Return the Name of the Client
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name for the Client
     * @param name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Surname of the Client
     * @return the Surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the Surname for the Client
     * @param surname the Surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Return the Email of the Client
     * @return the Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the Email for the Client
     * @param email the Email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return the Telephone Number of the Client
     * @return the Telephone Number
     */
    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets the Telephone Number for the Client
     * @param telephoneNumber the Telephone Number to set
     */
    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
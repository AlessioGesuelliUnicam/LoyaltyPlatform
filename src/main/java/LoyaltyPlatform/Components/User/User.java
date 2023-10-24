package LoyaltyPlatform.Components.User;

public interface User {
    /**
     * Return the ID of the User
     * @return the ID
     */
    int getId();

    /**
     * Return the Name of the User
     * @return the Name
     */
    String getName();

    /**
     * Sets the Name for the User
     * @param name the Name to set
     */
    void setName(String name);

    /**
     * Return the Surname of the User
     * @return the Surname
     */
    String getSurname();

    /**
     * Sets the Surname for the User
     * @param surname the Surname to set
     */
    void setSurname(String surname);

    /**
     * Return the Email od the User
     * @return the Email
     */
    String getEmail();

    /**
     * Sets the Email for the User
     * @param email the Email to set
     */
    void setEmail(String email);
}
package LoyaltyPlatform.Components.User;

/**
 * A Generic User represent the abstract class for the different type of User
 */
public abstract class GenericUser implements User {
    private final int id;
    private String name;
    private String surname;
    private String email;

    public GenericUser(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /**
     * Return the ID of the Generic User
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Return the Name of the Generic User
     *
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name for the Generic User
     *
     * @param name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Surname of the Generic User
     *
     * @return the Surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the Surname for the Generic User
     *
     * @param surname the Surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Return the Email of the Generic User
     *
     * @return the Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the Email for the Generic User
     *
     * @param email the Email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof GenericUser)) return false;
        GenericUser user = (GenericUser) o;
        return this.id == user.id;
    }
}


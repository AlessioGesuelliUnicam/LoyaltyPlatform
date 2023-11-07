package LoyaltyPlatform.Components.User;

import LoyaltyPlatform.Utilities.ObjectWithId;

import java.util.Objects;

/**
 * A Generic User represent the abstract class for the different type of Users
 */
public abstract class GenericUser implements User, ObjectWithId {

    private static int idCounter = 0;
    private final int id;
    private String name;
    private String surname;
    private String email;

    public GenericUser(String name, String surname, String email) {
        this.id = idCounter;
        idCounter++;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /**
     * Returns the id of the genericUser
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the genericUser
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the genericUser
     *
     * @param name the new name
     * @throws NullPointerException if the given name is null
     * @throws IllegalArgumentException if the given name is blank
     */
    public void setName(String name) {
        if(name == null) throw new NullPointerException("Field name can't be null");
        if(name.isEmpty()) throw new IllegalArgumentException("Field name can't be blank");
        this.name = name;
    }

    /**
     * Returns the surname of the genericUser
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the genericUser
     *
     * @param surname the new surname
     * @throws NullPointerException if the given surname is null
     * @throws IllegalArgumentException if the given surname is blank
     */
    public void setSurname(String surname) {
        if(surname == null) throw new NullPointerException("Field surname can't be null");
        if(surname.isEmpty()) throw new IllegalArgumentException("Field surname can't be blank");
        this.surname = surname;
    }

    /**
     * Returns the email of the genericUser
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the genericUser
     *
     * @param email the new email
     * @throws NullPointerException if the given email is null
     * @throws IllegalArgumentException if the given email is blank
     */
    public void setEmail(String email) {
        if(email == null) throw new NullPointerException("Field email can't be null");
        if(email.isEmpty()) throw new IllegalArgumentException("Field email can't be blank");
        this.email = email;
    }



    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof GenericUser user)) return false;
        return this.id == user.id;
    }

    public int hashCode() {
        return Objects.hash(id);
    }
}


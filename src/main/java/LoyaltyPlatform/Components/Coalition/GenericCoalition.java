package LoyaltyPlatform.Components.Coalition;

import LoyaltyPlatform.Components.FidelityProgram.FidelityProgram;
import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Utilities.ObjectWithId;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a generic coalition with a fidelity program
 */
public class GenericCoalition implements Coalition, ObjectWithId {

    private static int idCounter = 0;
    private final int id;
    private String name;
    private List<Shop> members;
    private FidelityProgram fidelityProgram;

    public GenericCoalition() {
        this.id = idCounter;
        idCounter++;
        this.name = "Nuova coalizione";
        this.members = new ArrayList<Shop>();
        this.fidelityProgram = null;
    }

    /**
     * Returns the id of the coalition
     *
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the coalition
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the coalition
     *
     * @param name the name of the coalition
     * @throws NullPointerException     if the given name is null
     * @throws IllegalArgumentException if the given name is blank
     */
    public void setName(String name) {
        if (name == null) throw new NullPointerException("Field name can't be null");
        if (name.isEmpty()) throw new IllegalArgumentException("Field name can't be blank");
        this.name = name;
    }

    /**
     * Returns the list of shops in the coalition
     *
     * @return shopsList
     */
    public List<Shop> getMembers() {
        return members;
    }

    /**
     * Adds a new member to the coalition
     *
     * @param shop the shop who joins
     * @return true if the member has been added, false otherwise
     * @throws NullPointerException if the given shop is null
     */
    public boolean addMember(Shop shop) {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        return members.add(shop);
    }

    /**
     * Removes a member from the coalition
     *
     * @param shop the shop who lefts
     * @return true if the member has been removed, false otherwise
     * @throws NullPointerException if the given shop is null
     */
    public boolean removeMember(Shop shop) {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        return members.remove(shop);
    }

    /**
     * Tells if the given shop is a member of the coalition
     *
     * @param shop the shop
     * @return true if the given shop is a member of the coalition, false otherwise
     * @throws NullPointerException if the given shop is null
     */
    public boolean hasMember(Shop shop) {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        return members.contains(shop);
    }

    /**
     * Tells if the coalition has only one member
     *
     * @return true if it has, false otherwise
     */
    public boolean hasOneMember() {
        return members.size() == 1;
    }

    /**
     * Tells if the coalition has no members
     *
     * @return true if it's empty, false otherwise
     */
    public boolean isEmpty() {
        return members.isEmpty();
    }


    /**
     * Returns the fidelity program of the coalition
     *
     * @return the fidelityProgram
     * @throws NullPointerException if the given fidelityProgram is null
     */
    public FidelityProgram getFidelityProgram() {
        return fidelityProgram;
    }

    /**
     * Set the fidelity program of the coalition
     *
     * @param fidelityProgram the new fidelity program
     * @throws NullPointerException if the given fidelityProgram is null
     */
    public void setFidelityProgram(FidelityProgram fidelityProgram) {
        if (fidelityProgram == null) throw new NullPointerException("Field fidelityProgram can't be null");
        this.fidelityProgram = fidelityProgram;
    }

    /**
     * Tells if the coalition has a fidelity program
     *
     * @return true if it has, false otherwise
     */
    public boolean hasFidelityProgram() {
        return getFidelityProgram() != null;
    }


    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof GenericCoalition coalition)) return false;
        return this.id == coalition.id;
    }

}

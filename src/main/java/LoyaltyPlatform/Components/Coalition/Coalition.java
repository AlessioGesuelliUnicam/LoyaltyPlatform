package LoyaltyPlatform.Components.Coalition;

import LoyaltyPlatform.Components.FidelityProgram.FidelityProgram;
import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Exceptions.ShopNotInQueueException;

import java.util.List;

public interface Coalition {

    /**
     * Returns the ID of the coalition
     *
     * @return Id
     */
    int getId();

    /**
     * Returns the name of the coalition
     *
     * @return name
     */
    String getName();

    /**
     * Set the name of the coalition
     *
     * @param name
     */
    void setName(String name);

    /**
     * Returns the list of shops in the coalition
     *
     * @return shopsList
     */
    List<Shop> getMembers();

    /**
     * Adds a new member to the coalition
     *
     * @param shop the shop who joins
     */
    boolean addMember(Shop shop);

    /**
     * Removes a member from the coalition
     *
     * @param shop the shop to remove
     * @return true if the shop has been removed, false otherwise
     * @throws NullPointerException if the given shop is null
     */
    boolean removeMember(Shop shop);

    /**
     * Tells if the given shop is a member of the coalition
     *
     * @param shop the shop
     * @return true if the given shop is a member of the coalition, false otherwise
     * @throws NullPointerException if the given shop is null
     */
    boolean hasMember(Shop shop);

    /**
     * Tells if the coalition has only one member
     *
     * @return true if it has, false otherwise
     */
    boolean hasOneMember();

    /**
     * Tells if the coalition has no members
     *
     * @return true if it's empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the fidelity program of the coalition
     *
     * @return the fidelityProgram
     */
    FidelityProgram getFidelityProgram();

    /**
     * Set the fidelity program of the coalition
     *
     * @param fidelityProgram the new fidelity program
     * @throws NullPointerException if the given fidelity program is null
     */
    void setFidelityProgram(FidelityProgram fidelityProgram);

    /**
     * Tells if the coalition has a fidelity program
     *
     * @return true if it has, false otherwise
     */
    public boolean hasFidelityProgram();

}

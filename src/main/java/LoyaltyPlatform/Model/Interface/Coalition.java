package LoyaltyPlatform.Model.Interface;

import java.util.List;

public interface Coalition {
    /**
     * Return the ID of the coalition
     * @return Id
     */
    int getId();
    /**
     * Return the name of the coalition
     * @return name
     */
    String getName();
    /**
     * Set the name of the coalition
     * @param name
     */
    void setName(String name);

    /**
     * Return the list of shops in the coalition
     * @return shopsList
     */
    List<Shop> getMembers();

    /**
     * Adds a new member to the coalition
     * @param shop the shop who joins
     */
    void addMember(Shop shop);

    /**
     * Removes a member from the coalition
     * @param shop the shop who lefts
     */
    void removeMember(Shop shop);

    /**
     * Return the fidelity program of the coalition
     * @return fidelityProgram
     */
    FidelityProgram getFidelityProgram();
    /**
     * Set the fidelity program of the coalition
     * @param fidelityProgram
     */
    void setFidelityProgram(FidelityProgram fidelityProgram);

}

package LoyaltyPlatform.Coalition;

import LoyaltyPlatform.FidelityProgram.FidelityProgram;
import LoyaltyPlatform.Shop.Shop;

import java.util.List;

public interface Coalition {
    /**
     * Returns the ID of the coalition
     * @return Id
     */
    int getId();
    /**
     * Returns the name of the coalition
     * @return name
     */
    String getName();
    /**
     * Set the name of the coalition
     * @param name
     */
    void setName(String name);

    /**
     * Returns the list of shops in the coalition
     * @return shopsList
     */
    List<Shop> getMembers();

    /**
     * Adds a new member to the coalition
     * @param shop the shop who joins
     */
    void addMember(Shop shop);

    /**
     * Returns the fidelity program of the coalition
     * @return fidelityProgram
     */
    FidelityProgram getFidelityProgram();
    /**
     * Set the fidelity program of the coalition
     * @param fidelityProgram
     */
    void setFidelityProgram(FidelityProgram fidelityProgram);

}

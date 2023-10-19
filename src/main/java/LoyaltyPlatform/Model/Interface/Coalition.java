package LoyaltyPlatform.Model.Interface;

import java.util.List;

public interface Coalition {
    /**
     * Return the ID of the coalition
     * @return ID
     */
    int getID();
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
    List<Shop> getShopsList();
    /**
     * Set the list of shops in the coalition
     * @param shopsList
     */
    void setShopsList(List<Shop> shopsList);
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

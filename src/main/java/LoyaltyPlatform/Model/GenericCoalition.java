package LoyaltyPlatform.Model;

import LoyaltyPlatform.Model.Interface.Coalition;
import LoyaltyPlatform.Model.Interface.FidelityProgram;
import LoyaltyPlatform.Model.Interface.Shop;

import java.util.List;

/**
 * This class represents a generic coalition
 */
public class GenericCoalition implements Coalition {
    private final int id;
    private String name;
    private List<Shop> shopsList;
    private FidelityProgram fidelityProgram;

    public GenericCoalition(int id, String name, List<Shop> shopsList, FidelityProgram fidelityProgram) {
        this.id = id;
        this.name = name;
        this.shopsList = shopsList;
        this.fidelityProgram = fidelityProgram;
    }

    /**
     * Returns the Id of the coalition
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
     * Set the name of the coalition
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the list of shops in the coalition
     *
     * @return shopsList
     */
    public List<Shop> getShopsList() {
        return shopsList;
    }


    /**
     * Returns the fidelity program of the coalition
     *
     * @return fidelityProgram
     */
    public FidelityProgram getFidelityProgram() {
        return fidelityProgram;
    }

    /**
     * Set the fidelity program of the coalition
     *
     * @param fidelityProgram
     */
    public void setFidelityProgram(FidelityProgram fidelityProgram) {
        this.fidelityProgram = fidelityProgram;
    }
}

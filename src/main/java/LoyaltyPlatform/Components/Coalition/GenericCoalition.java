package LoyaltyPlatform.Components.Coalition;

import LoyaltyPlatform.Components.FidelityProgram.FidelityProgram;
import LoyaltyPlatform.Components.Shop.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a generic coalition
 */
public class GenericCoalition implements Coalition {
    private final int id;
    private String name;
    private List<Shop> members;
    private FidelityProgram fidelityProgram;
    private List<Shop> participationRequests;

    public GenericCoalition(int id, String name) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<Shop>();
        this.fidelityProgram = null;
        this.participationRequests = new ArrayList<Shop>();
    }

    /**
     * Returns the Id of the coalition
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
    public List<Shop> getMembers() {
        return members;
    }

    /**
     * Adds a new member to the coalition
     * @param shop the shop who joins
     */
    public void addMember(Shop shop){}

    /**
     * Removes a member from the coalition
     * @param shop the shop who lefts
     */
    public void removeMember(Shop shop){}

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

    /**
     * Accepts an incoming request to participate
     * @param shop the shop who wants to join the coalition
     */
    public void acceptMember(Shop shop){}

    /**
     * Refuses an incoming request to participate
     * @param shop the shop who wants to join the coalition
     */
    public void refuseMember(Shop shop){}

    /**
     * Adds a shop to the participation requests list
     * @param shop the shop waiting for approval
     */
    public void addShopToParticipationRequests(Shop shop){}

    /**
     * Removes a shop from the participation requests list
     * @param shop the shop waiting for approval
     */
    public void deleteShopFromParticipationRequests(Shop shop){}

}

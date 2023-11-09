package LoyaltyPlatform.Components.Coalition;

import LoyaltyPlatform.Components.FidelityProgram.FidelityProgram;
import LoyaltyPlatform.Components.Shop.GenericShop;
import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Exceptions.ShopNotInQueueException;
import LoyaltyPlatform.Utilities.ObjectWithId;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * This class represents a generic coalition with a fidelity program
 */
public class GenericCoalition implements Coalition, ObjectWithId {

    private static int idCounter = 0;
    private final int id;
    private String name;
    private List<GenericShop> members;
    private FidelityProgram fidelityProgram;
    @JsonProperty
    private Set<GenericShop> participationRequests;

    public GenericCoalition(GenericShop leader) {
        this.id = idCounter;
        idCounter++;
        this.name = "Coalizione di " + leader.getName();
        this.members = new ArrayList<GenericShop>();
        this.members.add(leader);
        this.fidelityProgram = null;
        this.participationRequests = new HashSet<>();
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
    public List<GenericShop> getMembers() {
        return members;
    }

    /**
     * Adds a new member to the coalition
     *
     * @param shop the shop who joins
     * @return true if the member has been added, false otherwise
     * @throws NullPointerException if the given shop is null
     */
    public boolean addMember(GenericShop shop) {
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
    public boolean removeMember(GenericShop shop) {
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
    public boolean hasMember(GenericShop shop) {
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
     * @return true if the fidelityProgram has been changed, false otherwise
     * @throws NullPointerException if the given fidelityProgram is null and the coalition more than one member
     */
    public boolean setFidelityProgram(FidelityProgram fidelityProgram) {
        if ((members.size() > 1) && fidelityProgram == null)
            throw new NullPointerException("Field fidelityProgram can't be null if the coalition has more than one member");
        this.fidelityProgram = fidelityProgram;
        return true;
    }

    /**
     * Tells if the coalition has a fidelity program
     *
     * @return true if it has, false otherwise
     */
    public boolean hasFidelityProgram() {
        return getFidelityProgram() != null;
    }

    /**
     * Adds a shop to the participation requests list
     *
     * @param shop the shop waiting for approval
     * @return true if the shop is added, false otherwise
     */
    public boolean addToParticipationRequests(GenericShop shop) {
        if (shop == null) return false;
        if (hasMember(shop)) return false;
        return participationRequests.add(shop);

    }

    /**
     * Accepts an incoming request to participate
     *
     * @param shop the shop who wants to join the coalition
     * @return true if the member has been added, false otherwise
     */
    public boolean acceptMember(GenericShop shop) throws ShopNotInQueueException {
        if (shop == null) return false;
        if (participationRequests.remove(shop)) return addMember(shop);
        throw new ShopNotInQueueException("The given shop was not found in the incoming requests queue");
    }

    /**
     * Refuses an incoming request to participate
     *
     * @param shop the shop who wants to join the coalition
     * @return true if the member is refused, false otherwise
     */
    public boolean refuseMember(GenericShop shop) {
        if (shop == null) return false;
        if (!participationRequests.contains(shop)) return false;
        return participationRequests.remove(shop);
    }

    /**
     * Tells if the given shop is in the participation requests queue
     *
     * @param shop the shop waiting for approval
     * @return true if the shop is in the queue, false otherwise
     * @throws NullPointerException if the given shop is null
     */
    public boolean isWaiting(GenericShop shop) {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        return participationRequests.contains(shop);
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof GenericCoalition coalition)) return false;
        return this.id == coalition.id;
    }

    public int hashCode() {
        return Objects.hash(id);
    }

}
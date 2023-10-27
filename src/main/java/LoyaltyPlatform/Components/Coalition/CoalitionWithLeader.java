package LoyaltyPlatform.Components.Coalition;

import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Exceptions.ShopNotInQueueException;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a coalition where there's a leader
 * who decides if a shop can enter or not in it
 */
public class CoalitionWithLeader extends GenericCoalition {

    private Set<Shop> participationRequests;

    public CoalitionWithLeader(Shop leader) {
        super();
        super.setName("Coalizione di " + leader.getName());
        super.addMember(leader);
        this.participationRequests = new HashSet<>();
    }

    /**
     * Adds a shop to the participation requests list
     *
     * @param shop the shop waiting for approval
     * @return true if the shop is added, false otherwise
     */
    public boolean addToParticipationRequests(Shop shop) {
        if (shop == null) return false;
        if (participationRequests.contains(shop)) return false;
        if (super.getMembers().contains(shop)) return false;
        return participationRequests.add(shop);

    }

    /**
     * Accepts an incoming request to participate
     *
     * @param shop the shop who wants to join the coalition
     * @return true if the member has been added, false otherwise
     */
    public boolean acceptMember(Shop shop) throws ShopNotInQueueException {
        if (shop == null) return false;
        if(participationRequests.remove(shop)) return super.addMember(shop);
        throw new ShopNotInQueueException("The given shop was not found in the incoming requests queue");
    }

    /**
     * Refuses an incoming request to participate
     *
     * @param shop the shop who wants to join the coalition
     * @return true if the member is refused, false otherwise
     */
    public boolean refuseMember(Shop shop) {
        if (shop == null) return false;
        if (super.getMembers().contains(shop)) return false;
        if (!participationRequests.contains(shop)) return false;
        participationRequests.remove(shop);
        return true;
    }

    /**
     * Tells if the given shop is in the participation requests queue
     * @param shop the shop waiting for approval
     * @return true if the shop is in the queue, false otherwise
     * @throws NullPointerException if the given shop is null
     */
    public boolean isWaiting(Shop shop){
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        return participationRequests.contains(shop);
    }

}

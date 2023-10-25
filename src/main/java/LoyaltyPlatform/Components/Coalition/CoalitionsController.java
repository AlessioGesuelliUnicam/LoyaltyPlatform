package LoyaltyPlatform.Components.Coalition;

import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Db.Db;

import java.util.HashSet;

public class CoalitionsController {

    private final Db db;

    public CoalitionsController(Db db) {
        this.db = db;
    }

    /**
     * Returns the set of existing coalitions
     * @return the set
     */
    public HashSet<Coalition> getCoalitions(){
        return db.getCoalitionsTable().getRecords();
    }

    /**
     * Sends a participation request from a requesting shop to a hosting coalition
     * @param requestingShop the requesting shop
     * @param hostCoalition the hosting coalition
     * @return true if the request has been sent, false otherwise
     */
    public boolean sendParticipationRequest(Shop requestingShop, GenericCoalition hostCoalition) {
        if (requestingShop == null || hostCoalition == null) return false;
        if (requestingShop.getCoalition().getMembers().size() != 1) return false;
        return hostCoalition.addShopToParticipationRequests(requestingShop);
    }

    /**
     * Accepts a participation request from a requesting shop in a hosting coalition
     * @param requestingShop the requesting shop
     * @param hostCoalition the hosting coalition
     * @return true if the request has been accepted, false otherwise
     */
    public boolean acceptParticipationRequest(Shop requestingShop, GenericCoalition hostCoalition){
        if (requestingShop == null || hostCoalition == null) return false;
        if (requestingShop.getCoalition().getMembers().size() != 1) return false;
        return hostCoalition.acceptMember(requestingShop);
    }

    /**
     * Refuses a participation request from a requesting shop in a hosting coalition
     * @param requestingShop the requesting shop
     * @param hostCoalition the hosting coalition
     * @return true if the request has been refused, false otherwise
     */
    public boolean refuseParticipationRequest(Shop requestingShop, GenericCoalition hostCoalition){
        if (requestingShop == null || hostCoalition == null) return false;
        if (requestingShop.getCoalition().getMembers().size() != 1) return false;
        return hostCoalition.refuseMember(requestingShop);
    }

    /**
     * Makes a shop leave the coalition
     * @param leavingShop the leaving Shop
     * @param coalition the coalition to leave
     * @return true if the shop left the coalition, false otherwise
     */
    public boolean leftCoalition(Shop leavingShop, GenericCoalition coalition){
        if (leavingShop == null || coalition == null) return false;
        if (coalition.getMembers().size() == 1) return false;
        return coalition.removeMember(leavingShop);
    }
}

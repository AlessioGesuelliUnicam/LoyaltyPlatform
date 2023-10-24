package LoyaltyPlatform.Components.Coalition;

import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Db.Db;

import java.util.HashSet;

public class CoalitionsController {
    private HashSet<Coalition> coalitions;

    public CoalitionsController(Db db) {
        coalitions = db.getCoalitionsTable().getRecords();
    }

    public boolean sendPartecipationRequest(Shop requestingShop, GenericCoalition hostCoalition) {
        if (requestingShop == null || hostCoalition == null) return false;
        if (requestingShop.getCoalition().getMembers().size() != 1) return false;
        return hostCoalition.addShopToParticipationRequests(requestingShop);
    }
}

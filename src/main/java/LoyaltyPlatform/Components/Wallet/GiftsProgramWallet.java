package LoyaltyPlatform.Components.Wallet;

import LoyaltyPlatform.Components.Coalition.GenericCoalition;
import LoyaltyPlatform.Components.Reward.Gift;
import LoyaltyPlatform.Components.User.Client;

/**
 * This class represents the wallet of a client in a gift program
 */
public class GiftsProgramWallet extends GenericWallet {

    public GiftsProgramWallet(int id, GenericCoalition coalition, Client client, int points) {
        super(id, coalition, client, points);
    }

    /**
     * Subtract points to the wallet
     */
    public void redeemGift(Gift gift) {
        super.setPoints(this.getPoints() - gift.getNecessaryPoints());
    }
}

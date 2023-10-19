package LoyaltyPlatform.Model;

import LoyaltyPlatform.Model.Interface.Coalition;

/**
 * This class represents the wallet of a client in a gift program
 */
public class GiftProgramWallet extends GenericWallet {

    public GiftProgramWallet(int id, GenericCoalition coalition, Client client, int points) {
        super(id, coalition, client, points);
    }

    /**
     * Subtract points to the wallet
     */
    public void redeemGift(Gift gift) {
        super.setPoints(this.getPoints() - gift.getNecessaryPoints());
    }
}

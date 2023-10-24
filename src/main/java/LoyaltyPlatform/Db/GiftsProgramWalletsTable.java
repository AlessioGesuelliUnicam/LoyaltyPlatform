package LoyaltyPlatform.Db;


import LoyaltyPlatform.Wallet.GiftsProgramWallet;

import java.util.HashSet;


public class GiftsProgramWalletsTable implements Table<GiftsProgramWallet> {
    private HashSet<GiftsProgramWallet> giftsProgramWallets;

    public GiftsProgramWalletsTable() {
        giftsProgramWallets = new HashSet<>();
    }

    /**
     * Returns the collection of records
     *
     * @return the collection
     */
    public HashSet<GiftsProgramWallet> getRecords() {
        return giftsProgramWallets;
    }

    /**
     * Adds a record to the collection
     *
     * @param record the record to add
     */
    public void add(GiftsProgramWallet record) {
        giftsProgramWallets.add(record);
    }

    /**
     * Removes a record from the collection
     *
     * @param record the record to remove
     */
    public void remove(GiftsProgramWallet record) {
        giftsProgramWallets.remove(record);
    }
}

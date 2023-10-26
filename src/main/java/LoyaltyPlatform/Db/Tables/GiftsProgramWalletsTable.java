package LoyaltyPlatform.Db.Tables;


import LoyaltyPlatform.Components.Wallet.GiftsProgramWallet;

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
     * @return true if the record is added, false otherwise
     * @throws NullPointerException if the given record is null
     */
    public boolean add(GiftsProgramWallet record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        return giftsProgramWallets.add(record);
    }

    /**
     * Removes a record from the collection
     *
     * @param record the record to remove
     * @return true if the record is deleted, false otherwise
     * @throws NullPointerException if the given record is null
     */
    public boolean remove(GiftsProgramWallet record) {
        if (record == null) throw new NullPointerException("Field record can't be null");
        return giftsProgramWallets.remove(record);
    }

    /**
     * Finds a record by id
     *
     * @param id the id of the record
     * @return the record if found
     */
    public GiftsProgramWallet findById(int id) {
        for (GiftsProgramWallet giftsProgramWallet : giftsProgramWallets) {
            if (giftsProgramWallet.getId() == id) {
                return giftsProgramWallet;
            }
        }
        return null;
    }
}

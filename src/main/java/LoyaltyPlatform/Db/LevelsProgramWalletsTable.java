package LoyaltyPlatform.Db;

import LoyaltyPlatform.Wallet.LevelsProgramWallet;

import java.util.HashSet;


public class LevelsProgramWalletsTable implements Table<LevelsProgramWallet> {
    private HashSet<LevelsProgramWallet> levelsProgramWallets;

    public LevelsProgramWalletsTable() {
        levelsProgramWallets = new HashSet<>();
    }

    /**
     * Returns the collection of records
     *
     * @return the collection
     */

    public HashSet<LevelsProgramWallet> getRecords() {
        return levelsProgramWallets;
    }

    /**
     * Adds a record to the collection
     *
     * @param record the record to add
     */
    public void add(LevelsProgramWallet record) {
        levelsProgramWallets.add(record);
    }

    /**
     * Removes a record from the collection
     *
     * @param record the record to remove
     */
    public void remove(LevelsProgramWallet record) {
        levelsProgramWallets.remove(record);
    }
}

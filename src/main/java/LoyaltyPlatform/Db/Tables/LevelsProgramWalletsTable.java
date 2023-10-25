package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.Wallet.LevelsProgramWallet;

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
     * @return true if the record is added, false otherwise
     */
    public boolean add(LevelsProgramWallet record) {
        if (record == null) return false;
        return levelsProgramWallets.add(record);
    }

    /**
     * Removes a record from the collection
     *
     * @param record the record to remove
     * @return true if the record is deleted, false otherwise
     */
    public boolean remove(LevelsProgramWallet record) {
        if (record == null) return false;
        return levelsProgramWallets.remove(record);
    }

    /**
     * Finds a record by id
     *
     * @param id the id of the record
     * @return the record if found
     */
    public LevelsProgramWallet findById(int id) {
        for (LevelsProgramWallet levelsProgramWallet : levelsProgramWallets) {
            if (levelsProgramWallet.getId() == id) {
                return levelsProgramWallet;
            }
        }
        return null;
    }
}

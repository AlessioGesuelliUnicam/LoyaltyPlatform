package LoyaltyPlatform.Components.Transaction;

import LoyaltyPlatform.Components.Wallet.Wallet;
import LoyaltyPlatform.Components.Shop.Shop;

public interface Transaction {
    /**
     * Returns the Id of the transaction
     * @return Id
     */
    int getId();
    /**
     * Return the amount of the transaction
     * @return amount
     */
    float getAmount();
    /**
     * Set the amount of the transaction
     * @param amount
     */
    void setAmount(float amount);

    /**
     * Returns the wallet to update
     * @return wallet
     */
    Wallet getWallet();
    /**
     * Set the wallet to update
     * @param wallet
     */
    void setWallet(Wallet wallet);
    /**
     * Returns the shop where the transaction was made
     * @return shop
     */
    Shop getShop();
    /**
     * Set the shop where the transaction was made
     * @param shop
     */
    void setShop(Shop shop);




}

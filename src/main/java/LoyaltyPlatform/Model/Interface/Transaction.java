package LoyaltyPlatform.Model.Interface;

public interface Transaction {
    /**
     * Return the ID of the transaction
     * @return ID
     */
    int getID();
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
     * Return the wallet to update
     * @return wallet
     */
    Wallet getWallet();
    /**
     * Set the wallet to update
     * @param wallet
     */
    void setWallet(Wallet wallet);
    /**
     * Return the shop where the transaction was made
     * @return shop
     */
    Shop getShop();
    /**
     * Set the shop where the transaction was made
     * @param shop
     */
    void setShop(Shop shop);




}

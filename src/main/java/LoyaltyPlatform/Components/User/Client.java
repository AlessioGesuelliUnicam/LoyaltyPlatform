package LoyaltyPlatform.Components.User;


import LoyaltyPlatform.Components.Wallet.Wallet;

import java.util.List;

/**
 * A Client represent the Final Consumer of the Loyalty Platform
 */
public class Client extends GenericUser {
    private int telephoneNumber;
    private List<Wallet> wallets;

    public Client(int id, String name, String surname, String email, int telephoneNumber, List<Wallet> wallets) {
        super(id, name, surname, email);
        this.telephoneNumber = telephoneNumber;
        this.wallets = wallets;
    }
    /**
     * Return the Telephone Number of the Client
     * @return the Telephone Number
     */
    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets the Telephone Number for the Client
     * @param telephoneNumber the Telephone Number to set
     */
    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Return the Wallet List of the Client
     * @return the Wallet
     */
    public List<Wallet> getWallets() {
        return wallets;
    }

    /**
     * Adds a Wallet into the Wallets List
     * @param wallet the Wallet to add
     */
    public void addWallet(Wallet wallet) {

    }

    /**
     * Deletes a Wallet into the Wallets List
     * @param wallet the Wallet to delete
     */
    public void deleteWallet(Wallet wallet) {
    }
}
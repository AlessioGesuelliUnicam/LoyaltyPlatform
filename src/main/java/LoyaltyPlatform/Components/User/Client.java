package LoyaltyPlatform.Components.User;


import LoyaltyPlatform.Components.User.GenericUser;
import LoyaltyPlatform.Components.Wallet.Wallet;

import java.util.HashSet;

/**
 * A Client represent the final consumer of the Loyalty Platform
 */
public class Client extends GenericUser {
    private String telephoneNumber;
    private HashSet<Wallet> wallets;

    public Client(String name, String surname, String email, String telephoneNumber) {
        super(name, surname, email);
        this.telephoneNumber = telephoneNumber;
        this.wallets = new HashSet<Wallet>();
    }

    /**
     * Returns the telephoneNumber of the client
     * @return the telephoneNumber
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets the telephoneNumber of the client
     * @param telephoneNumber the new telephoneNumber
     * @throws NullPointerException if the given telephoneNumber is null
     * @throws IllegalArgumentException if the given telephoneNumber is blank
     */
    public void setTelephoneNumber(String telephoneNumber) {
        if(telephoneNumber == null) throw new NullPointerException("Field telephoneNumber can't be null");
        if(telephoneNumber.isEmpty()) throw new IllegalArgumentException("Field telephoneNumber can't be blank");
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Returns the wallets of the client
     * @return a wallets set
     */
    public HashSet<Wallet> getWallets() {
        return wallets;
    }

    /**
     * Adds a wallet into the set of wallets
     * @param wallet the wallet to add
     * @return true if the wallet has been added, false otherwise
     * @throws NullPointerException if the given wallet is null
     */
    public boolean addWallet(Wallet wallet) {
        if(wallet == null) throw new NullPointerException("Field wallet can't be null");
        return wallets.add(wallet);
    }

    /**
     * Removes a wallet from the set of wallets
     * @param wallet the Wallet to delete
     * @return true if the wallet has been removed, false otherwise
     * @throws NullPointerException if the given wallet is null
     */
    public boolean deleteWallet(Wallet wallet) {
        if(wallet == null) throw new NullPointerException("Field wallet can't be null");
        return wallets.remove(wallet);
    }

}
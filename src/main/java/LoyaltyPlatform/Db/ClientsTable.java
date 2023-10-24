package LoyaltyPlatform.Db;

import LoyaltyPlatform.User.Client;
import java.util.HashSet;

public class ClientsTable implements Table<Client> {

    private HashSet<Client> clients;

    public ClientsTable(){
        clients = new HashSet<>();
    }

    /**
     * Returns the collection of Client
     * @return the collection
     */
    public HashSet<Client> getRecords() {
        return clients;
    }

    /**
     * Adds a Client to the collection
     * @param record the Client to add
     */
    public void add(Client record) {
        clients.add(record);
    }

    /**
     * Removes a Client from the collection
     * @param record the Client to remove
     */
    public void remove(Client record) {
        clients.remove(record);
    }
}

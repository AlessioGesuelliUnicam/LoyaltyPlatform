package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.User.Client;
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
     * @return true if the Client is added, false otherwise
     */
    public boolean add(Client record) {
        if(record == null) return false;
        return clients.add(record);
    }

    /**
     * Removes a Client from the collection
     * @param record the Client to remove
     * @return true if the Client is deleted, false otherwise
     */
    public boolean remove(Client record) {
        if(record == null) return false;
        return clients.remove(record);
    }

    /**
     * Finds a Client by id
     * @param id the Client of the record
     * @return the Client if found
     */
    public Client findById(int id) {
        for(Client client : clients){
            if(client.getId() == id) return client;
        }
        return null;
    }
}

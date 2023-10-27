package LoyaltyPlatform.Components.Coalition;

import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Db.Db;
import LoyaltyPlatform.Exceptions.*;

import java.util.HashSet;

public class CoalitionsController {

    private final Db db;

    public CoalitionsController(Db db) {
        this.db = db;
    }

    /**
     * Returns the set of existing coalitions
     * @return the set
     */
    public HashSet<Coalition> getCoalitions(){
        return db.getCoalitionsTable().getRecords();
    }

    /**
     * Returns the coalition tho which the shop belongs
     * @param shop the shop
     * @return the coalition of the shop if it has one
     * @throws NullPointerException if the given shop is null
     */
    public Coalition getCoalitionOf(Shop shop){
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        HashSet<Coalition> coalitions = getCoalitions();
        for(Coalition coalition : coalitions){
            if(coalition.hasMember(shop)) return coalition;
        }
        return null;
    }



    /**
     * Creates a new coalition starting from a shop
     * @param shop the first shop of the coalition
     * @return true if the coalition has been created, false otherwise
     * @throws NullPointerException if the given shop is null
     * @throws HasAlreadyACoalitionException if the given shop already belongs to a coalition
     */
    public boolean createCoalition(Shop shop) throws HasAlreadyACoalitionException {
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(getCoalitionOf(shop) != null) throw new HasAlreadyACoalitionException("Can't create a coalition with a member of another coalition");
        CoalitionWithLeader coalition = new CoalitionWithLeader(shop);
        return db.getCoalitionsTable().add(coalition);
    }

    /**
     * Deletes an empty coalition
     * @param coalition the coalition to delete
     * @return true if the coalition has been deleted, false otherwise
     * @throws NullPointerException if the given coalition is null
     * @throws CoalitionNotEmptyException if the given coalition is not empty
     */
    public boolean deleteCoalition(Coalition coalition) throws CoalitionNotEmptyException {
        if(coalition == null) throw new NullPointerException("Field coalition can't be null");
        if(!coalition.isEmpty()) throw new CoalitionNotEmptyException("Can't delete a coalition with some members");
        return db.getCoalitionsTable().remove(coalition);
    }



    /**
     * Sends a participation request from a requesting shop to a hosting coalition
     * @param coalition the hosting coalition
     * @param shop the requesting shop
     * @return true if the request has been sent, false otherwise
     */
    public boolean sendParticipationRequest(CoalitionWithLeader coalition, Shop shop) {
        if (coalition == null) throw new NullPointerException("Field coalition can't be null");
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        if (!coalition.hasFidelityProgram())  throw new IllegalArgumentException();
        return coalition.addToParticipationRequests(shop);
    }

    /**
     * Accepts a participation request from a requesting shop in a hosting coalition
     * Migrates a shop from a coalition to another
     *
     * @param shop the requesting shop
     * @param coalition the hosting coalition
     * @return true if the request has been accepted and the shop migrated coalition, false otherwise
     * @throws NullPointerException if any of the fields is null
     * @throws ShopNotInQueueException if the shop wasn't found in the participation requests queue of the new coalition
     * @throws FidelityProgramNotProvidedException if the new coalition doesn't provide a fidelity program
     */
    public boolean acceptParticipationRequest(CoalitionWithLeader coalition, Shop shop) throws CoalitionNotEmptyException, ShopNotInQueueException, FidelityProgramNotProvidedException {
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(coalition == null) throw new NullPointerException("Field newCoalition can't be null");
        if (!coalition.hasFidelityProgram()) throw new FidelityProgramNotProvidedException("Can't migrate a shop in a coalition who doesn't provide a fidelity program");
        Coalition oldCoalition = getCoalitionOf(shop);
        if(oldCoalition == null) return coalition.acceptMember(shop);
        if(coalition.acceptMember(shop)){
            oldCoalition.removeMember(shop);
            if(oldCoalition.isEmpty()) deleteCoalition(oldCoalition);
            clearParticipationRequestsFor(shop);
            return true;
        }
        return false;
    }

    /**
     * Refuses a participation request from a requesting shop in a hosting coalition
     * @param coalition the hosting coalition
     * @param shop the requesting shop
     * @return true if the request has been refused, false otherwise
     */
    public boolean refuseParticipationRequest(CoalitionWithLeader coalition, Shop shop){
        if (shop == null || coalition == null) return false;
        return coalition.refuseMember(shop);
    }

    /**
     * Makes a shop leave the coalition
     *
     * @param shop the leaving Shop
     * @return true if the shop left the coalition, false otherwise
     * @throws NullPointerException if any of the field is null
     * @throws LastMemberLeavingException if the given shop is the only member of the coalition
     */
    public boolean leftCoalition(Shop shop) throws LastMemberLeavingException, HasAlreadyACoalitionException {
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        Coalition coalition = getCoalitionOf(shop);
        if (coalition.hasOneMember()) throw new LastMemberLeavingException("A shop can't leave a coalition with only one member");
        coalition.removeMember(shop);
        return createCoalition(shop);
    }



    /**
     * Deletes a shop from all the participation requests queues
     * @param shop the shop
     * @throws NullPointerException if the given shop is null
     */
    private void clearParticipationRequestsFor(Shop shop){
        if (shop == null) throw new NullPointerException("Field shop can't be null");
        HashSet<Coalition> coalitions = getCoalitions();
        for (Coalition coalition : coalitions){
            if(coalition instanceof CoalitionWithLeader coalitionWithLeader){
                if(coalitionWithLeader.isWaiting(shop)) coalitionWithLeader.refuseMember(shop);
            }
        }
    }

}

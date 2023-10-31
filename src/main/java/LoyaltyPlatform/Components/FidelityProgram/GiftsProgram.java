package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Coalition.Coalition;
import LoyaltyPlatform.Components.Reward.Gift;
import LoyaltyPlatform.Components.Shop.Shop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A GiftsProgram represents a GenericFidelityProgram
 * where a list of Gifts is provided
 */
public class GiftsProgram extends GenericFidelityProgram{

    private HashMap<Shop, Set<Gift>> shopsGift;

    public GiftsProgram(Coalition coalition, double multiplier, String description) {
        super(coalition, multiplier, description);
        List<Shop> members = coalition.getMembers();
        for(Shop shop : members) addShop(shop);
    }

    /**
     * Returns all the shops in this program and the associated gifts
     * @return the shops gift
     */
    public HashMap<Shop, Set<Gift>> getShopsGift(){
        return shopsGift;
    }

    /**
     * Adds a shop in the list of shops of the program with an empty set of gifts
     * @param shop the shop to add
     * @return true if the shop is added, false if the shop was already present
     * @throws NullPointerException if the given shop is null
     */
    public boolean addShop(Shop shop){
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(shopsGift.containsKey(shop)) return false;
        shopsGift.put(shop, new HashSet<Gift>());
        return true;
    }

    /**
     * Removes a shop from the list of shops of the program
     * @param shop the shop to remove
     * @return true if the shop has been removed, false if the shop was not found
     * @throws NullPointerException if the given shop is null
     */
    public boolean removeShop(Shop shop){
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(!shopsGift.containsKey(shop)) return false;
        shopsGift.remove(shop);
        return true;
    }

    /**
     * Adds a gift in a given shop
     * @param shop the shop on which to add the gift
     * @param gift the gift to add
     * @return true if the gift of the shop has been added, false otherwise
     * @throws NullPointerException if any of the fields is null
     * @throws IllegalArgumentException if the shop doesn't belong to this program
     */
    public boolean addShopGift(Shop shop, Gift gift){
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(gift == null) throw new NullPointerException("Field discount can't be null");
        if(!shopsGift.containsKey(shop)) throw new IllegalArgumentException(("Field shop is invalid"));
        return shopsGift.get(shop).add(gift);
    }

    /**
     * Removes a gift in a given shop
     * @param shop the shop on which to remove the gift
     * @param gift the gift to remove
     * @return true if the gift of the shop has been removed, false otherwise
     * @throws NullPointerException if any of the fields is null
     * @throws IllegalArgumentException if the shop doesn't belong to this level
     */
    public boolean removeShopGift(Shop shop, Gift gift){
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        if(gift == null) throw new NullPointerException("Field gift can't be null");
        if(!shopsGift.containsKey(shop)) throw new IllegalArgumentException(("Field shop is invalid"));
        return shopsGift.get(shop).remove(gift);
    }

}

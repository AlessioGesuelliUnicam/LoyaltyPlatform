package LoyaltyPlatform.Model;

import LoyaltyPlatform.Model.Interface.Shop;

import java.util.HashMap;
import java.util.List;

/**
 * A GiftsProgram represents a GenericFidelityProgram
 * where a list of Gifts is provided
 */
public class GiftsProgram extends GenericFidelityProgram{

    private HashMap<Shop, List<Gift>> giftsMap;

    public GiftsProgram(int id, double multiplier, String description) {
        super(id, multiplier, description);
    }
}

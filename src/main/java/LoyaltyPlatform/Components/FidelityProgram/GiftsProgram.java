package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Reward.Gift;
import LoyaltyPlatform.Components.Shop.Shop;

import java.util.HashMap;
import java.util.List;

/**
 * A GiftsProgram represents a GenericFidelityProgram
 * where a list of Gifts is provided
 */
public class GiftsProgram extends GenericFidelityProgram{

    private HashMap<Shop, List<Gift>> giftsMap;

    public GiftsProgram(double multiplier, String description) {
        super(multiplier, description);
    }
}

package LoyaltyPlatform.Model;

import LoyaltyPlatform.Model.Interface.Shop;

import java.util.HashMap;
import java.util.List;

public class GiftsProgram extends GenericFidelityProgram{

    private HashMap<Shop, List<Gift>> giftsMap;

    public GiftsProgram(int id, double multiplier, String description) {
        super(id, multiplier, description);
    }
}

package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Coalition.Coalition;
import LoyaltyPlatform.Components.Reward.Gift;
import LoyaltyPlatform.Components.Reward.GiftsController;
import LoyaltyPlatform.Components.Shop.GenericShop;
import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Db.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/levelsPrograms")
public class GiftsProgramsController {

    private final Db db;

    @Autowired
    public GiftsProgramsController(Db db) {
        this.db = db;
    }

    /**
     * Returns all the GiftsPrograms
     * @return the GiftsPrograms
     */
    @GetMapping("/getGiftsPrograms")
    public Set<GiftsProgram> getGiftsPrograms(){
        return db.getGiftsProgramsTable().getRecords();
    }

    /**
     * Creates a new giftsProgram
     *
     * @param multiplier  the multiplier used to convert euros into points
     * @param description the description of the program
     * @return the new GiftsProgram
     */
    @PostMapping("/createGiftsProgram")
    public GiftsProgram createGiftsProgram(@RequestParam double multiplier, @RequestParam String description) {
        GiftsProgram giftsProgram = new GiftsProgram(multiplier, description);
        if(!db.getGiftsProgramsTable().add(giftsProgram)) return null;
        return giftsProgram;
    }

    /**
     * Deletes a giftsProgram
     *
     * @param giftsProgramId the id of the giftProgram to delete
     * @return true if the program has been deleted, false otherwise
     */
    @DeleteMapping("/deleteGiftsProgram")
    private boolean deleteGiftsProgram(@RequestParam int giftsProgramId) {
        GiftsProgram giftsProgram = db.getGiftsProgramsTable().getRecordById(giftsProgramId);
        if (giftsProgram == null) return false;
        GiftsController giftsController = new GiftsController(db);
        HashMap<Shop, Set<Gift>> shopsGift = giftsProgram.getShopsGift();
        for (Map.Entry<Shop, Set<Gift>> entry : shopsGift.entrySet()){
            Set<Gift> gifts = entry.getValue();
            for (Gift gift : gifts)
                if(!giftsController.deleteGift(gift.getId())) return false;
        }
        return db.getGiftsProgramsTable().delete(giftsProgram);
    }

    /**
     * Adds a shop in a giftsProgram
     * @param giftsProgramId the id of the giftsProgram where to add the shop
     * @param shopId the id of the shop to add
     * @return true if the shop has been added, false otherwise
     */
    @PostMapping("/addShopToGiftsProgram")
    private boolean addShopToGiftsProgram(@RequestParam int giftsProgramId, @RequestParam int shopId){
        GiftsProgram giftsProgram = db.getGiftsProgramsTable().getRecordById(giftsProgramId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if(giftsProgram == null || shop == null) return false;
        return giftsProgram.addShop(shop);
    }

    /**
     * Removes a shop from a giftsProgram
     * @param giftsProgramId the id of the giftsProgram from which to remove the shop
     * @param shopId the id of the shop to remove
     * @return true if the shop has been removed, false otherwise
     * @throws NullPointerException if any of the fields is null
     */
    @DeleteMapping("/removeShopFromGiftsProgram")
    private boolean removeShopFromGiftsProgram(@RequestParam int giftsProgramId, @RequestParam int shopId){
        GiftsProgram giftsProgram = db.getGiftsProgramsTable().getRecordById(giftsProgramId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if(giftsProgram == null || shop == null) return false;
        GiftsController giftsController = new GiftsController(db);
        HashMap<Shop, Set<Gift>> shopsGift = giftsProgram.getShopsGift();
        Set<Gift> gifts = shopsGift.get(shop);
        for (Gift gift : gifts)
            if(!giftsController.deleteGift(gift.getId())) return false;
        return giftsProgram.removeShop(shop);
    }

}

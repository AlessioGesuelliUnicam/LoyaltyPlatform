package LoyaltyPlatform.Components.FidelityProgram;

import LoyaltyPlatform.Components.Coalition.Coalition;
import LoyaltyPlatform.Components.Reward.GenericReward;
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
@RequestMapping("/giftsPrograms")
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
    public GiftsProgram createGiftsProgram(double multiplier, String description) {
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
    public boolean deleteGiftsProgram(int giftsProgramId) {
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
    public boolean addShopToGiftsProgram(int giftsProgramId, int shopId){
        GiftsProgram giftsProgram = db.getGiftsProgramsTable().getRecordById(giftsProgramId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if(giftsProgram == null) return false;
        return giftsProgram.addShop(shop);
    }

    /**
     * Removes a shop from a giftsProgram
     * @param giftsProgramId the id of the giftsProgram from which to remove the shop
     * @param shopId the id of the shop to remove
     * @return true if the shop has been removed, false otherwise
     * @throws NullPointerException if any of the fields is null
     */
    public boolean removeShopFromGiftsProgram(int giftsProgramId, int shopId){
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

    /**
     * Adds a gift for a shop in a gift program
     * @param giftProgramId the id of the giftProgram
     * @param shopId the id of the shop
     * @param label the label of the gift
     * @param necessaryPoints the necessaryPoints of the gift
     * @param addition the addition of the gift
     * @return true if the gift has been added, false otherwise
     */
    @PostMapping("/addShopGiftInGiftProgram")
    public boolean addShopGiftInGiftProgram(@RequestParam int giftProgramId, @RequestParam int shopId, @RequestParam String label, @RequestParam int necessaryPoints, @RequestParam double addition){
        GiftsProgram giftsProgram = db.getGiftsProgramsTable().getRecordById(giftProgramId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if(giftsProgram == null) return false;
        GiftsController giftsController = new GiftsController(db);
        Gift gift = giftsController.createGift(label, necessaryPoints, addition);
        return giftsProgram.addShopGift(shop, gift);

    }

    /**
     * Removes a gift of a shop in a giftProgram
     * @param giftProgramId the id of the giftProgram
     * @param shopId the id of the shop
     * @param giftId the id of the gift
     * @return true if the gift has been removed, false otherwise
     */
    @PostMapping("/removeShopGiftFromGiftProgram")
    public boolean removeShopGiftFromGiftProgram(@RequestParam int giftProgramId, @RequestParam int shopId, @RequestParam int giftId){
        GiftsProgram giftsProgram = db.getGiftsProgramsTable().getRecordById(giftProgramId);
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        Gift gift = db.getGiftsTable().getRecordById(giftId);
        if(giftsProgram == null) return false;
        GiftsController giftsController = new GiftsController(db);
        if(!giftsProgram.removeShopGift(shop, gift)) return false;
        return giftsController.deleteGift(gift.getId());
    }
}

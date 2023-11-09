package LoyaltyPlatform.Components.Reward;

import LoyaltyPlatform.Db.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/gifts")
public class GiftsController {

    private final Db db;

    @Autowired
    public GiftsController(Db db) {
        this.db = db;
    }

    /**
     * Returns all the gifts
     *
     * @return all the gifts
     */
    @GetMapping("/getGifts")
    public HashSet<Gift> getGifts() {
        return db.getGiftsTable().getRecords();
    }

    /**
     * Creates a new Gift
     *
     * @param label           the label of the Gift
     * @param necessaryPoints the necessaryPoints to redeem the Gift
     * @param addition        the addition to redeem the Gift
     * @return the new gift
     */
    public Gift createGift(String label, int necessaryPoints, double addition) {
        Gift gift = new Gift(label, necessaryPoints, addition);
        if (!db.getGiftsTable().add(gift)) return null;
        return gift;
    }

    /**
     * Deletes a gift
     *
     * @param giftId the id of the gift to delete
     * @return true if the gift has been deleted, false otherwise
     */
    public boolean deleteGift(int giftId) {
        Gift gift = db.getGiftsTable().getRecordById(giftId);
        if (gift == null) return false;
        return db.getGiftsTable().delete(gift);
    }

    /**
     * Sets the label of a gift
     *
     * @param giftId the id of the gift where to change the label
     * @param label  the new label
     */
    @PostMapping("/setGiftLabel")
    public void setGiftLabel(int giftId, String label) {
        Gift gift = db.getGiftsTable().getRecordById(giftId);
        if (gift != null) gift.setLabel(label);
    }


    /**
     * Sets the necessaryPoints for a gift
     *
     * @param giftId          the id of the gift where to change the necessaryPoints
     * @param necessaryPoints the new amount of necessaryPoints
     */
    @PostMapping("/setGiftNecessaryPoints")
    public void setGiftNecessaryPoints(@RequestParam int giftId, @RequestParam int necessaryPoints) {
        Gift gift = db.getGiftsTable().getRecordById(giftId);
        if (gift != null) gift.setNecessaryPoints(necessaryPoints);
    }

    /**
     * Sets the addition for a gift
     *
     * @param giftId   the id of the gift where to change the addition
     * @param addition the new addition
     */
    @PostMapping("/setGiftAddition")
    public void setGiftAddition(@RequestParam int giftId, @RequestParam double addition) {
        Gift gift = db.getGiftsTable().getRecordById(giftId);
        if (gift != null) gift.setAddition(addition);
    }
}

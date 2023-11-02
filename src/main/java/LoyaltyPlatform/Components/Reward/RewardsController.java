package LoyaltyPlatform.Components.Reward;

import LoyaltyPlatform.Db.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    private final Db db;

    @Autowired
    public RewardsController(Db db) {
        this.db = db;
    }

    /**
     * Sets the label of a reward
     *
     * @param reward the reward where to change the label
     * @param label  the new label
     * @throws NullPointerException     if any of the fields is null
     * @throws IllegalArgumentException if the given label is blank
     */

    @PostMapping("/setRewardLabel")
    public void setRewardLabel(@RequestBody Reward reward, @RequestParam String label) {
        if (reward == null) throw new NullPointerException("Field reward can't be ull");
        if (label == null) throw new NullPointerException("Field label can't be null");
        if (label.isEmpty()) throw new IllegalArgumentException("Field label can't be blank");
        reward.setLabel(label);
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
     * @return true if the Gift has been created, false otherwise
     */
    @PostMapping("/createGift")
    public boolean createGift(@RequestParam String label, @RequestParam int necessaryPoints, @RequestParam double addition) {
        if (label == null) return false;
        if (necessaryPoints < 0) return false;
        if (addition < 0) return false;
        Gift gift = new Gift(label, necessaryPoints, addition);
        return db.getGiftsTable().add(gift);
    }

    /**
     * Deletes a gift
     *
     * @param gift the gift to delete
     * @return true if the gift has been deleted, false otherwise
     */
    @DeleteMapping("/deleteGift")
    public boolean deleteGift(@RequestBody Gift gift) {
        if (gift == null) throw new NullPointerException("Field gift can't be null");
        return db.getGiftsTable().delete(gift);
    }

    /**
     * Sets the necessaryPoints for a gift
     *
     * @param gift            the gift where to change the necessaryPoints
     * @param necessaryPoints the new amount of necessaryPoints
     * @throws NullPointerException     if the given gift is null
     * @throws IllegalArgumentException if the given necessaryPoints are below 1
     */
    @PostMapping("/setGiftNecessaryPoints")
    public void setGiftNecessaryPoints(@RequestBody Gift gift, @RequestParam int necessaryPoints) {
        if (gift == null) throw new NullPointerException("Field gift can't be null");
        if (necessaryPoints < 1) throw new IllegalArgumentException("Field necessaryPoints can't be less than 1");
        gift.setNecessaryPoints(necessaryPoints);
    }

    /**
     * Sets the addition for a gift
     *
     * @param gift     the gift where to change the addition
     * @param addition the new addition
     * @throws NullPointerException     if the given gift is null
     * @throws IllegalArgumentException if the given addition is below 0
     */
    @PostMapping("/setGiftAddition")
    public void setGiftAddition(@RequestBody Gift gift, @RequestParam double addition) {
        if (gift == null) throw new NullPointerException("Field gift can't be null");
        if (addition < 0) throw new IllegalArgumentException("Field addition must be positive");
        gift.setAddition(addition);
    }

    /**
     * Returns all the discounts
     *
     * @return all the discounts
     */
    @GetMapping("/getDiscounts")
    public HashSet<Discount> getDiscounts() {
        return db.getDiscountsTable().getRecords();
    }

    /**
     * Creates a new Discount
     *
     * @param label              the label of the Discount
     * @param percentageDiscount the percentageDiscount
     * @return true if the Discount has been created, false otherwise
     */
    @PostMapping("/createDiscount")
    public boolean createDiscount(@RequestParam String label, @RequestParam int percentageDiscount) {
        if (label == null) return false;
        if (percentageDiscount < 0 || percentageDiscount > 100) return false;
        Discount discount = new Discount(label, percentageDiscount);
        return db.getDiscountsTable().add(discount);
    }

    /**
     * Deletes a discount
     *
     * @param discount the discount to delete
     * @return true if the discount has been deleted, false otherwise
     */
    @DeleteMapping("/deleteDiscount")
    public boolean deleteDiscount(@RequestBody Discount discount) {
        if (discount == null) throw new NullPointerException("Field discount can't be null");
        return db.getDiscountsTable().delete(discount);
    }

    /**
     * Sets the percentageDiscount for a discount
     *
     * @param discount           the discount where to change the percentageDiscount
     * @param percentageDiscount the new percentageDiscount
     * @throws NullPointerException     if the given discount is null
     * @throws IllegalArgumentException if the given percentageDiscount is out of range 1-100
     */
    @PostMapping("/setDiscountPercentageDiscount")
    public void setDiscountPercentageDiscount(@RequestBody Discount discount, @RequestParam int percentageDiscount) {
        if (discount == null) throw new NullPointerException("Field discount can't be null");
        if (percentageDiscount < 0 || percentageDiscount > 100)
            throw new IllegalArgumentException("Field percentageDiscount out of range 1-100");
        discount.setPercentageDiscount(percentageDiscount);
    }

}

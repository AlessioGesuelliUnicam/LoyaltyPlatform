package LoyaltyPlatform.Components.Reward;

import LoyaltyPlatform.Db.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/discounts")
public class DiscountsController {

    private final Db db;

    @Autowired
    public DiscountsController(Db db) {
        this.db = db;
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
     * @return the new discount
     */
    @PostMapping("/createDiscount")
    public Discount createDiscount(@RequestParam String label, @RequestParam int percentageDiscount) {
        Discount discount = new Discount(label, percentageDiscount);
        if (!db.getDiscountsTable().add(discount)) return null;
        return discount;
    }

    /**
     * Deletes a discount
     *
     * @param discountId the id of the discount to delete
     * @return true if the discount has been deleted, false otherwise
     */
    @DeleteMapping("/deleteDiscount")
    public boolean deleteDiscount(@RequestParam int discountId) {
        Discount discount = db.getDiscountsTable().getRecordById(discountId);
        if (discount == null) return false;
        return db.getDiscountsTable().delete(discount);
    }

    /**
     * Sets the label of a discount
     *
     * @param discountId the id of the discount where to change the label
     * @param label      the new label
     */
    public void setDiscountLabel(int discountId, String label) {
        Discount discount = db.getDiscountsTable().getRecordById(discountId);
        if (discount != null) discount.setLabel(label);
    }


    /**
     * Sets the percentageDiscount for a discount
     *
     * @param discountId         the id of the discount where to change the percentageDiscount
     * @param percentageDiscount the new percentageDiscount
     */
    @PostMapping("/setDiscountPercentageDiscount")
    public void setDiscountPercentageDiscount(@RequestParam int discountId, @RequestParam int percentageDiscount) {
        Discount discount = db.getDiscountsTable().getRecordById(discountId);
        if (discount != null) discount.setPercentageDiscount(percentageDiscount);
    }
}

package LoyaltyPlatform.Model;

import LoyaltyPlatform.Model.Interface.Shop;
import LoyaltyPlatform.Model.Interface.User;

/**
 * An Owner represent the head of a single shop.
 */
public class Owner extends Employee {

    public Owner(int id, String name, String surname, String email, Shop shop) {
        super(id, name, surname, email, shop);
    }

}
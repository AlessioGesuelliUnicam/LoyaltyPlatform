package LoyaltyPlatform.User;

import LoyaltyPlatform.Shop.Shop;

/**
 * An Owner represent the head of a single shop.
 */
public class Owner extends Employee {

    public Owner(int id, String name, String surname, String email, Shop shop) {
        super(id, name, surname, email, shop);
    }

}
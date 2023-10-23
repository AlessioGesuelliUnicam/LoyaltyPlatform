package LoyaltyPlatform.User;

import LoyaltyPlatform.Shop.Shop;

/**
 * An Employee represent the worker of one of the shops.
 */
public class Employee extends GenericUser {
    private final Shop shop;

    public Employee(int id, String name, String surname, String email, Shop shop) {
        super(id, name, surname, email);
        this.shop = shop;
    }

    /**
     * Return the Shop of the Employee
     * @return the Shop
     */
    public Shop getShop() {
        return shop;
    }

}

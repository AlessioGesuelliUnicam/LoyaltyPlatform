package LoyaltyPlatform.Components.User;

import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Components.User.GenericUser;

/**
 * An Employee represent a worker of a shops.
 */
public class Employee extends GenericUser {

    private Shop shop;

    public Employee(String name, String surname, String email) {
        super(name, surname, email);
        this.shop = null;
    }

    /**
     * Return the Shop of the Employee
     * @return the Shop
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * Sets the Shop of the Employee
     * @throws NullPointerException if the given shop is null
     */
    public void setShop(Shop shop) {
        if(shop == null) throw new NullPointerException("Field shop can't be null");
        this.shop = shop;
    }

}

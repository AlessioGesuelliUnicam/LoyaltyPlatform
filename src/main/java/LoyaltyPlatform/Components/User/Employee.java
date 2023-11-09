package LoyaltyPlatform.Components.User;

import LoyaltyPlatform.Components.Shop.Shop;
import LoyaltyPlatform.Components.User.GenericUser;

/**
 * An Employee represent a worker of a shops.
 */
public class Employee extends GenericUser {

    public Employee(String name, String surname, String email) {
        super(name, surname, email);
    }
}

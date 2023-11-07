package LoyaltyPlatform.Components.Shop;

import LoyaltyPlatform.Components.User.Employee;
import LoyaltyPlatform.Components.User.Owner;

import java.util.HashSet;

public interface Shop {
    /**
     *  Return the ID of the User
     *  @return the ID
     */
    int getId();

    /**
     * Return the Partita Iva of the Shop
     * @return the Partita Iva
     */
    String getPartitaIva();

    /**
     * Return the Name of the Shop
     * @return the Name
     */
    String getName();

    /**
     * Sets the Name for the Shop
     * @param name the Name to set
     */
    void setName(String name);

    /**
     * Return the Owner of the Shop
     * @return the Owner
     */
    Owner getOwner();

    /**
     * Returns the set of employees
     * @return the employees
     */
    public HashSet<Employee> getEmployees();

    /**
     * Adds an employee to the set of employees
     * @param employee the employee to add
     * @return true if the employee has been added, false otherwise
     */
    public boolean addEmployee(Employee employee);

    /**
     * Removes an employee from the set of employees
     * @param employee the employee to remove
     * @return true if the employee has been removed, false otherwise
     */
    public boolean removeEmployee(Employee employee);

}
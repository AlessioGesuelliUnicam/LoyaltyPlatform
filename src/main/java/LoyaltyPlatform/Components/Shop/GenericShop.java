package LoyaltyPlatform.Components.Shop;

import LoyaltyPlatform.Components.User.Employee;
import LoyaltyPlatform.Components.User.Owner;
import LoyaltyPlatform.Utilities.ObjectWithId;

import java.util.HashSet;
import java.util.Objects;

/**
 * A Generic Shop represent the individual shop of the Loyalty Platform
 */
public class GenericShop implements Shop, ObjectWithId {

    private static int idCounter = 0;
    private final int id;
    private String partitaIva;
    private String name;
    private Owner owner;
    private HashSet<Employee> employees;

    public GenericShop(String partitaIva, String name, Owner owner) {
        if(partitaIva == null) throw new NullPointerException("Field partitaIva can't be null");
        if(partitaIva.isEmpty()) throw new IllegalArgumentException("Field partitaIva can't be blank");
        if(name == null) throw new NullPointerException("Field name can't be null");
        if(name.isEmpty()) throw new IllegalArgumentException("Field name can't be blank");
        if(owner == null) throw new NullPointerException("Field owner can't be null");
        this.id = idCounter;
        idCounter++;
        this.partitaIva = partitaIva;
        this.name = name;
        this.owner = owner;
        this.employees = new HashSet<Employee>();
    }


    /**
     * Return the ID of the Generic User
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Return the Partita Iva of the Shop
     *
     * @return the Partita Iva
     */
    public String getPartitaIva() {
        return partitaIva;
    }

    /**
     * Return the Name of the Shop
     *
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name for the Shop
     *
     * @param name the Name to set
     * @throws NullPointerException if the given name is null
     * @throws IllegalArgumentException if the given name is blank
     */
    public void setName(String name) {
        if(name == null) throw new NullPointerException("Field name can't be null");
        if(name.isEmpty()) throw new IllegalArgumentException("Field name can't be blank");
        this.name = name;
    }

    /**
     * Return the Owner of the Shop
     *
     * @return the Owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Returns the set of employees
     * @return the employees
     */
    public HashSet<Employee> getEmployees(){
        return employees;
    }

    /**
     * Adds an employee to the set of employees
     * @param employee the employee to add
     * @return true if the employee has been added, false otherwise
     */
    public boolean addEmployee(Employee employee){
        if(employee == null) throw new NullPointerException("Field employee can't be null");
        return employees.add(employee);
    }

    /**
     * Removes an employee from the set of employees
     * @param employee the employee to remove
     * @return true if the employee has been removed, false otherwise
     */
    public boolean removeEmployee(Employee employee){
        if(employee == null) throw new NullPointerException("Field employee can't be null");
        return employees.remove(employee);
    }



    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof GenericShop shop)) return false;
        return this.id == shop.id;
    }

    public int hashCode() {
        return Objects.hash(id);
    }

}
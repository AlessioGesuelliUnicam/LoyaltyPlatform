package LoyaltyPlatform.Components.Shop;

import LoyaltyPlatform.Components.Coalition.CoalitionsController;
import LoyaltyPlatform.Components.Coalition.GenericCoalition;
import LoyaltyPlatform.Components.User.Employee;
import LoyaltyPlatform.Components.User.EmployeesController;
import LoyaltyPlatform.Components.User.Owner;
import LoyaltyPlatform.Db.Db;
import LoyaltyPlatform.Exceptions.CoalitionNotEmptyException;
import LoyaltyPlatform.Exceptions.HasAlreadyACoalitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/shops")
public class ShopsController {

    private final Db db;

    @Autowired
    public ShopsController(Db db) {
        this.db = db;
    }

    /**
     * Return all the shops
     *
     * @return the shops
     */
    @GetMapping("/getShops")
    public HashSet<GenericShop> getShops() {
        return db.getShopsTable().getRecords();
    }

    /**
     * Returns the shop of a given owner
     *
     * @param ownerId the id of the owner
     * @return the shop of the owner
     */
    @GetMapping("/getShopOf")
    public GenericShop getShopOf(@RequestParam int ownerId) {
        Owner owner = db.getOwnersTable().getRecordById(ownerId);
        if (owner == null) return null;
        HashSet<GenericShop> shops = getShops();
        for (GenericShop shop : shops)
            if (shop.getOwner().getId() == ownerId) return shop;
        return null;
    }

    /**
     * Creates a new shop and puts it in a new coalition
     *
     * @param partitaIva the partitaIva of the shop
     * @param ownerId    the id of the owner of the shop
     * @return the new shop
     */
    public GenericShop createShop(String partitaIva, int ownerId) throws HasAlreadyACoalitionException {
        Owner owner = db.getOwnersTable().getRecordById(ownerId);
        GenericShop shop = new GenericShop(partitaIva, owner);
        if (!db.getShopsTable().add(shop)) return null;
        CoalitionsController coalitionsController = new CoalitionsController(db);
        if (coalitionsController.createCoalition(shop.getId()) != null) return shop;
        return null;
    }

    /**
     * Deletes a shop and removes it from its coalition
     *
     * @param shopId the id of the shop to remove
     * @return true if the shop has been deleted, false otherwise
     */
    public boolean deleteShop(int shopId) throws CoalitionNotEmptyException {
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (shop == null) return false;
        CoalitionsController coalitionsController = new CoalitionsController(db);
        GenericCoalition coalition = coalitionsController.getCoalitionOf(shop.getId());
        if (!coalitionsController.removeShopFromCoalition(coalition.getId(), shop.getId())) return false;
        deleteShopEmployees(shop.getId());
        return db.getShopsTable().delete(shop);
    }

    /**
     * Returns the employees of a given shop
     *
     * @param shopId the id of the shop
     * @return the set of employees
     */
    @GetMapping("/getShopEmployees")
    public HashSet<Employee> getShopEmployees(@RequestParam int shopId) {
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        return shop.getEmployees();
    }

    /**
     * Adds an employee in the given shop
     *
     * @param shopId  the id of the shop
     * @param name    the name of employee
     * @param surname the surname of employee
     * @param email   the email of employee
     * @return true if the employee has been created, false otherwise
     */
    @PostMapping("/addShopEmployee")
    public boolean addShopEmployee(@RequestParam int shopId, @RequestParam String name, @RequestParam String surname, @RequestParam String email) {
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        EmployeesController employeesController = new EmployeesController(db);
        Employee employee = employeesController.createEmployee(name, surname, email);
        return shop.addEmployee(employee);
    }

    /**
     * Removes an employee from the given shop
     *
     * @param shopId     the id of the shop
     * @param employeeId the id of the employee to remove
     * @return true if the employee has been deleted, false otherwise
     */
    @PostMapping("/removeShopEmployee")
    public boolean removeShopEmployee(@RequestParam int shopId, @RequestParam int employeeId) {
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        Employee employee = db.getEmployeesTable().getRecordById(employeeId);
        EmployeesController employeesController = new EmployeesController(db);
        if (!shop.removeEmployee(employee)) return false;
        return employeesController.deleteEmployee(employeeId);
    }

    /**
     * Sets the name of a shop
     *
     * @param shopId the id of the shop
     * @param name   the new name
     */
    @PostMapping("/setShopName")
    public void setShopName(@RequestParam int shopId, @RequestParam String name) {
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        shop.setName(name);
    }


    /**
     * Deletes all the employees from a shop
     *
     * @param shopId the id of the shop where to delete the employees
     */
    private void deleteShopEmployees(int shopId) {
        GenericShop shop = db.getShopsTable().getRecordById(shopId);
        if (shop == null) return;
        EmployeesController employeesController = new EmployeesController(db);
        HashSet<Employee> employees = shop.getEmployees();
        for (Employee employee : employees) {
            int employeeId = employee.getId();
            employeesController.deleteEmployee(employeeId);
        }
    }

}

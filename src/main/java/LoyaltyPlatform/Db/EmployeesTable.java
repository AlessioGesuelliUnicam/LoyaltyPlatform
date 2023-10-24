package LoyaltyPlatform.Db;

import LoyaltyPlatform.User.Employee;
import java.util.HashSet;

public class EmployeesTable implements Table<Employee> {

    private HashSet<Employee> employees;

    public EmployeesTable() {
        employees = new HashSet<>();
    }

    /**
     * Returns the collection of records
     * @return the collection
     */
    public HashSet<Employee> getRecords() {
        return employees;
    }

    /**
     * Adds an Employee to the collection
     * @param record the Employee to add
     */
    public void add(Employee record) {
        employees.add(record);
    }

    /**
     * Removes an Employee from the collection
     * @param record the Employee to remove
     */
    public void remove(Employee record) {
        employees.remove(record);
    }
}

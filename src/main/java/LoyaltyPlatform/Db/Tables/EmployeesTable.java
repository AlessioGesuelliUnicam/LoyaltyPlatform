package LoyaltyPlatform.Db.Tables;

import LoyaltyPlatform.Components.User.Employee;

import java.util.HashSet;

public class EmployeesTable implements Table<Employee> {

    private HashSet<Employee> employees;

    public EmployeesTable() {
        employees = new HashSet<>();
    }

    /**
     * Returns the collection of records
     *
     * @return the collection
     */
    public HashSet<Employee> getRecords() {
        return employees;
    }

    /**
     * Adds an Employee to the collection
     *
     * @param record the Employee to add
     * @return true if the Employee has been added, false otherwise
     */
    public boolean add(Employee record) {
        if (record == null) return false;
        return employees.add(record);
    }

    /**
     * Removes an Employee from the collection
     *
     * @param record the Employee to remove
     * @return true if the Employee has been removed, false otherwise
     */
    public boolean remove(Employee record) {
        if (record == null) return false;
        return employees.remove(record);
    }

    /**
     * Finds an Employee by id
     *
     * @param id the Employee of the record
     * @return the Employee if found
     */
    public Employee findById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) return employee;
        }
        return null;
    }
}

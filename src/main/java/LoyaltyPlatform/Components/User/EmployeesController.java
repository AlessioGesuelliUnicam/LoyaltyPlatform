package LoyaltyPlatform.Components.User;

import LoyaltyPlatform.Db.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/employees")
public class EmployeesController{

    private final Db db;

    @Autowired
    public EmployeesController(Db db) {
        this.db = db;
    }

    /**
     * Returns all the employees
     * @return the set of employees
     */
    @GetMapping("/getEmployees")
    public HashSet<Employee> getEmployees(){
        return db.getEmployeesTable().getRecords();
    }

    /**
     * Creates a new employee
     * @param name the name of the employee
     * @param surname the surname of the employee
     * @param email the email of the employee
     * @return the new employee
     */
    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestParam String name, @RequestParam String surname, @RequestParam String email){
        Employee employee = new Employee(name, surname, email);
        if(!db.getEmployeesTable().add(employee)) return null;
        return employee;
    }

    /**
     * Deletes an employee
     * @param employeeId the id of the employee to delete
     * @return true if the employee has been deleted, false otherwise
     */
    @DeleteMapping("/deleteEmployee")
    public boolean deleteEmployee(@RequestParam int employeeId){
        Employee employee = db.getEmployeesTable().getRecordById(employeeId);
        if (employee == null) return false;
        return db.getEmployeesTable().delete(employee);
    }



    /**
     * Sets the name for the given employee
     * @param employeeId the id of the employee
     * @param name the new name
     */
    @PostMapping("/setEmployeeName")
    void setEmployeeName(@RequestParam int employeeId, @RequestParam String name){
        Employee employee = db.getEmployeesTable().getRecordById(employeeId);
        employee.setName(name);
    }

    /**
     * Sets the surname for the given employee
     * @param employeeId the id of the employee
     * @param surname the new surname
     */
    @PostMapping("/setEmployeeSurname")
    void setEmployeeSurname(@RequestParam int employeeId, @RequestParam String surname){
        Employee employee = db.getEmployeesTable().getRecordById(employeeId);
        employee.setSurname(surname);
    }

    /**
     * Sets the email for the given employee
     * @param employeeId the id of the employee
     * @param email the new email
     */
    @PostMapping("/setEmployeeEmail")
    void setEmployeeEmail(@RequestParam int employeeId, @RequestParam String email){
        Employee employee = db.getEmployeesTable().getRecordById(employeeId);
        employee.setEmail(email);
    }

}

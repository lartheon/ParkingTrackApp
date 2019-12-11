package com.example.springsecuritymysql.controller;

import java.util.List;
import java.util.Set;

import com.example.springsecuritymysql.clientModel.ClientEmployee;
import com.example.springsecuritymysql.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springsecuritymysql.exception.EmployeeNotFoundException;
import com.example.springsecuritymysql.model.Employee;
import com.example.springsecuritymysql.repository.EmployeeRepository;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ModelConverter converter;

    /*
    private final EmployeeRepository repository;


    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }
*/
    // Aggregate root

    @GetMapping("/api/employees")
    List<ClientEmployee> all() {
        List<Employee> all = repository.findAll();
        return converter.convert(all);
    }

    // CREATE  - employee
    @PostMapping("/api/employees")
    ClientEmployee newEmployee(@Valid @RequestBody Employee newEmployee) {
        return converter.convert (repository.save(newEmployee));
    }

    // READ - Single employee
    @GetMapping("/api/employees/{id}")
    ClientEmployee one(@PathVariable @Min(1) Long id) {

        return converter.convert (repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)));
    }

    // Other ways to find employees
    @GetMapping("/api/employees/searchByPermit")
    List<ClientEmployee> permitNumber(@RequestParam int number) {
        return converter.convert (repository.findByPermitNumber(number));
    }

    @GetMapping("/api/employees/searchByReg")
    List<ClientEmployee> regNumber(@RequestParam String reg) {
        return converter.convert (repository.findByRegNumber(reg));
    }


    @GetMapping("/api/employees/searchByName")
    List<ClientEmployee> nameSearch(@RequestParam String name) {
        List<Employee> employees = repository.findByName(name);
        return converter.convert(employees);
    }

    // Replace an employee
    // Can't alter the employeeID as its auto generated -
    @PutMapping("/api/employees/{id}")
    Employee replaceEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setSkypeId(newEmployee.getSkypeId());
                    employee.setEmail(newEmployee.getEmail());
                    employee.setDept(newEmployee.getDept());
                    employee.setPermitNumber(newEmployee.getPermitNumber());
                    // This is where we need to update the vehicle record to the new vehicle with new id if adding new row?
                    // This is a set:
                    Set<Vehicle> vehicles = newEmployee.getVehicles();

                    // Loop through the vehicles in newEmployee. How to do a for-each in a set. A Java construct.


                    // Check if it has a vehicle id


                    // If it doesn't have a vehicle id then it's a new one, create a new Vehicle object, set the fields and add it to employee vehicles
                    // If it does have a vehicle ID find the matching record in employee and update it
                    // If employee has a vehicle that isn't in newEmployee then that vehicle has been deleted, remove it from employee
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    return repository.save(newEmployee);
                });
    }

    // UPDATE - a employee record
    @DeleteMapping("/api/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
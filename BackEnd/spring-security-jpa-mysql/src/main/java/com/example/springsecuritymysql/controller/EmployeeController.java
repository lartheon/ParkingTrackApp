package com.example.springsecuritymysql.controller;

import java.util.List;

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

    // Aggregate root

    @GetMapping("/api/employees")
    List<Employee> all() {
        return repository.findAll();
    }

    // CREATE  - employee
    @PostMapping("/api/employees")
    Employee newEmployee(@Valid @RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // READ - Single employee
    @GetMapping("/api/employees/{id}")
    Employee one(@PathVariable @Min(1) Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // Other ways to find employees
    @GetMapping("/api/employees/searchByPermit")
    List<Employee> permitNumber(@RequestParam int number) {
        return repository.findByPermitNumber(number);
    }

    @GetMapping("/api/employees/searchByReg")
    List<Employee> regNumber(@RequestParam String reg) {
        return repository.findByRegNumber(reg);
    }

    @GetMapping("/api/employees/searchByName")
    List<Employee> nameSearch(@RequestParam String name) {
        return repository.findByName(name);
    }

    // Replace an employee's details - UPDATE
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
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    return repository.save(newEmployee);
                });
    }

    // DELETE - a employee record
    @DeleteMapping("/api/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
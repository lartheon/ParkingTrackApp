package com.example.springsecuritymysql.controller;

import com.example.springsecuritymysql.clientModel.ClientEmployee;
import com.example.springsecuritymysql.exception.EmployeeNotFoundException;
import com.example.springsecuritymysql.model.Employee;
import com.example.springsecuritymysql.model.Vehicle;
import com.example.springsecuritymysql.repository.EmployeeRepository;
import com.example.springsecuritymysql.repository.VehicleRepository;
import com.example.springsecuritymysql.security.Salt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

import static com.example.springsecuritymysql.security.SecurityConstants.*;
import static java.util.Collections.emptyList;

//@CrossOrigin(maxAge = 3600)
//@RequestMapping("/api/employees")
//@CrossOrigin(origins = {"http://localhost:3000"})

@SessionAttributes({"currentUser"})
@Transactional
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public EmployeeController(EmployeeRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.repository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelConverter converter;

    // Aggregate root
//    @CrossOrigin(origins = {"*"})
    @GetMapping(APP_API+EMPLOYEES_URL)
    List<ClientEmployee> all() {
        List<Employee> all = repository.findAll();
        return converter.convert(all);
    }

    //Login employee
    @Transactional(readOnly = true)
//    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<Employee> optionalEmployee = repository.findByEmail(email);

        if (optionalEmployee != null) {
            if (!optionalEmployee.isEmpty()) {
                return new User(optionalEmployee.get(0).getEmail(), optionalEmployee.get(0).getPassword(), emptyList());
            } else throw new UsernameNotFoundException("User not found. Email doesn't exist");
        } else throw new UsernameNotFoundException("User not found. Email doesn't exist");
    }

    List<ClientEmployee> login(String email){
        List<Employee> all = repository.findByEmail(email);
        return converter.convert(all);
    }
    @GetMapping(APP_API+LOGIN_URL)
    @ResponseBody
    List<ClientEmployee> login(@Valid @RequestBody LoginForm loginForm){
        System.out.println("server side employee login :" + loginForm.getEmail() + " " + loginForm.getPassword());

            List<Employee> all = repository.findByEmail(loginForm.getEmail());
            return converter.convert(all);
}

    // CREATE  - employee
//    @CrossOrigin(origins = {"*"})
    @PostMapping(APP_API+EMPLOYEES_URL+"/")
    @ResponseBody
    ClientEmployee newEmployee(@Valid @RequestBody Employee newEmployee) {
        System.out.println("New employeeId found: " + newEmployee.getFirstName());
        String hashed = BCrypt.hashpw(newEmployee.getPassword(), Salt.getSalt());
        newEmployee.setPassword(hashed);
        return converter.convert(repository.save(newEmployee));
    }

    // READ - Single employee
    @GetMapping(APP_API+EMPLOYEES_URL+"/{id}")
    @ResponseBody
    ClientEmployee one(@PathVariable @Min(1) Long id) {
        System.out.println("ClientEmployee one() id: " + id);
        return converter.convert(repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)));
    }

    // Other ways to find employees
    @GetMapping(APP_API+EMPLOYEES_URL+"/searchByPermit")
    List<ClientEmployee> permitNumber(@RequestParam String number) {
        return converter.convert(repository.findByPermitNumber(number));
    }

    @GetMapping(APP_API+EMPLOYEES_URL+"/searchByReg")
    List<ClientEmployee> regNumber(@RequestParam String reg) {
        return converter.convert(repository.findByRegNumber(reg));
    }


    @GetMapping(APP_API+EMPLOYEES_URL+"/searchByName")
    List<Employee> nameSearch(@RequestParam String name) {
        List<Employee> employees = repository.findByName(name);
//        return JSONArray.toJSONString(employees);
        //converter.convert(employees);
        return employees;
    }

    // Replace an employee
    // Can't alter the employeeID as its auto generated -
//    @CrossOrigin(origins = {"*"})
    @Transactional
    @Modifying
    @PutMapping(APP_API+EMPLOYEES_URL+"/{id}")
    @ResponseBody
    Employee replaceEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable Long id) {
        System.out.println("replaceEmployee id: " + id.toString());
        VehicleController vc = new VehicleController();
        Employee dBEmployee = repository.findByIdInTable(id);
        Set<Vehicle> dBEmployeeVehicles = dBEmployee.getVehicles();
        Set<Employee.VehicleForDeletion> vehiclesForDeletion = newEmployee.getForDeletion();

        return repository.findById(id).map(employee -> {
            System.out.println("Employee: " + employee);
            employee.setFirstName(newEmployee.getFirstName());
            employee.setLastName(newEmployee.getLastName());
            employee.setSkypeId(newEmployee.getSkypeId());
            employee.setEmail(newEmployee.getEmail());
            employee.setDept(newEmployee.getDept());
            employee.setPermitNumber(newEmployee.getPermitNumber());
            employee.setForDeletion(null);
            if(newEmployee.getPassword() != null){
                if(!newEmployee.getPassword().isEmpty()){
                    String hashed = BCrypt.hashpw(newEmployee.getPassword(), Salt.getSalt());
//                String hashed = passwordEncoder().encode(newEmployee.getPassword());
                    employee.setPassword(hashed);
                    newEmployee.setPassword(hashed);
                }
            }
            // This is where we need to update the vehicle record to the new vehicle with new id if adding new row?
            // This is a set:
            Set<Vehicle> newEmployeeVehicles = newEmployee.getVehicles();
            Set<Vehicle> temp = newEmployeeVehicles;

            // Loop through the vehicles in newEmployee. How to do a for-each in a set. A Java construct.
            // Check if it has a vehicle id
            // If it doesn't have a vehicle id then it's a new one, create a new Vehicle object, set the fields and add it to employee vehicles
            // If it does have a vehicle ID find the matching record in employee and update it
            // If employee has a vehicle that isn't in newEmployee then that vehicle has been deleted, remove it from employee

            for (Vehicle v : temp) {
                System.out.println("Vehicle " + v.getRegNumber() + " id: " + v.getVehicleId());
                if (v.getVehicleId() == null) {
                    System.out.println("VehicleId IS null: A new vehicle is to be added reg: " + v.getRegNumber());
                } else {
                    System.out.println("VehicleId NOT null: Updating: " + v.getVehicleId());

                    for (Vehicle dbV : dBEmployeeVehicles) {
                        if (v.getVehicleId() == dbV.getVehicleId()) {
                            System.out.println("dbEmployee table contains this vehicleId : " + v.getVehicleId());
                            vc.replaceVehicle(v, v.getVehicleId());
                            System.out.println("Updated vehicle in dbEmployee table : " + v.getVehicleId());
                        }
                    }
                }
            }
            if(vehiclesForDeletion != null){
                System.out.println("vehiclesForDeletion' set size :" + vehiclesForDeletion.size());
                if(vehiclesForDeletion.size() > 0){
                    for(Employee.VehicleForDeletion vId : vehiclesForDeletion){
                        System.out.println("Identified vehicle for deletion Id: " + vId);
                        vehicleRepository.deleteVehicleInTable(vId.getVehicleId());
                        System.out.println("Successfully deleted vehicle Id " + vId.getVehicleId());
                    }
                }}
            employee.setVehicles(newEmployeeVehicles);
            return repository.save(employee);
        })
                .orElseGet(() -> {
                    System.out.println("orElseGet method called on employee save");
                    return repository.save(newEmployee);
                });
    }


    // UPDATE - a employee record
//    @CrossOrigin(origins = {"*"})
    @DeleteMapping(APP_API+EMPLOYEES_URL+"/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Modifying
    @DeleteMapping(APP_API+VEHICLES_URL+"/{id}")
    void deleteVehicle(@PathVariable Long id) {
        System.out.println("VehicleController deleteVehicle : " + id);
        vehicleRepository.deleteById(id);
    }

    static public class LoginForm {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
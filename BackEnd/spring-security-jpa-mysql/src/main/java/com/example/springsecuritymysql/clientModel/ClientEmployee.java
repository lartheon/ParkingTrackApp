package com.example.springsecuritymysql.clientModel;

import com.example.springsecuritymysql.model.Employee;

import java.util.HashSet;
import java.util.Set;


public class ClientEmployee {

    //  @GeneratedValue(strategy= GenerationType.AUTO)

    private Long employee_id;
    private String firstName;
    private String lastName;
    private String skypeId;
    private String email;
    private String password;
    private String dept;
    private String permitNumber;
    private Set<Employee.VehicleForDeletion> forDeletion = new HashSet<>();
    private Set<ClientVehicle> vehicles = new HashSet<>();


    public String toString() {
        return String.format(
                "Employee[employee_id=%d, firstName='%s', lastName='%s', skypeId='%s', email='%s', dept='%s', permitNumber='%d']",
                employee_id, firstName, lastName, skypeId, email, dept, permitNumber);
    }

    public Long getEmployeeId() {
        return employee_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSkypeId() {
        return skypeId;
    }

    public String getEmail() {
        return email;
    }

    public String getDept() {
        return dept;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public Set<ClientVehicle> getVehicles() {
        return vehicles;
    }

    public void setEmployeeId(Long employee_id) {
        this.employee_id = employee_id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public void setVehicles(Set<ClientVehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<Employee.VehicleForDeletion> getForDeletion() {
        return forDeletion;
    }

    public void setForDeletion(Set<Employee.VehicleForDeletion> forDeletion) {
        this.forDeletion = forDeletion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
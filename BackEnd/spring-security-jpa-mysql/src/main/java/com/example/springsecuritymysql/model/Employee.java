package com.example.springsecuritymysql.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
  //  @GeneratedValue(strategy= GenerationType.AUTO)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long employee_id;

    @NotEmpty(message = "Please provide a First name")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Please provide a Last name")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "skype_id")
    private String skypeId;

    private String email;
    private String dept;

    @NotNull(message = "Please provide a Permit Number")
    @Positive
    @Max(value = 99999, message = "Must be no more than 5 digits" )
    @Column(name = "permit_number")
    private int permitNumber;

    @ManyToMany
    @JoinTable(
            name = "employees_vehicles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    Set<Vehicle> vehicles;

    protected Employee () {}

    public Employee (String firstName, String lastName, String skypeId, String email, String dept, int permitNo) {
        this.dept = dept;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.permitNumber = permitNo;
        this.skypeId = skypeId;
    }

    @Override
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
    @Email(message = "Email should be valid")
    public String getEmail() {
        return email;
    }

    public String getDept() {
        return dept;
    }

    public Integer getPermitNumber() {
        return permitNumber;
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

    public void setPermitNumber(int permitNumber) {
        this.permitNumber = permitNumber;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
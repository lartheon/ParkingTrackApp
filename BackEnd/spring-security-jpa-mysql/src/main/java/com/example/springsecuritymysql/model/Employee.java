package com.example.springsecuritymysql.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "employees")
public class Employee {
    @JsonProperty("password")
    private String password;

    @Transient
    @JsonProperty("forDeletion")
    private Set<VehicleForDeletion> idForDeletion;

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    @Size(min = 1, max = 5)
    @Column(name = "permit_number")
    private String permitNumber; //LKK change, permitNumber should be String in case there are permits starting in 0.

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "employees_vehicles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Set<Vehicle> vehicles;

//    @JsonProperty("role")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity= Role.class)
    @JoinTable(
            name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "employee_id" ),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Collection<Role> roles;

    protected Employee(){}

    protected Employee(Employee employee) {
        this.dept = employee.getDept();
        this.email = employee.getEmail();
        this.password = employee.getPassword();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.permitNumber = employee.getPermitNumber();
        this.skypeId = employee.getSkypeId();
        this.idForDeletion = employee.getForDeletion();
        this.roles = employee.getRoles();
    }

    public Employee (String firstName,
                     String lastName,
                     String skypeId,
                     String email,
                     String password,
                     String dept,
                     String permitNo,
                     Set<VehicleForDeletion> forDeletion,
                     Set<Role> roles)
    {
        this.dept = dept;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.permitNumber = permitNo;
        this.skypeId = skypeId;
        this.idForDeletion = forDeletion;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return String.format(
                "Employee[employee_id=%d, firstName='%s', lastName='%s', skypeId='%s', email='%s', dept='%s', permitNumber='%s']",
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

    public String getPermitNumber() {
        return permitNumber;
    }

    public Set<Vehicle> getVehicles () {
        return vehicles;
    }

    @JsonProperty("forDeletion")
    public Set<VehicleForDeletion> getForDeletion() { return idForDeletion; }

    @JsonProperty("forDeletion")
    public void setForDeletion(Set<VehicleForDeletion> forDeletion) { this.idForDeletion = forDeletion; }

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

    public Collection<Role> getRoles() { return roles; }

    public void setRoles(Collection<Role> roles) { this.roles = roles; }

    @JsonProperty("password")
    public String getPassword() { return password; }
    @JsonProperty("password")
    public void setPassword(String password) { this.password = password; }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    static public class VehicleForDeletion {

        @JsonProperty("vehicleId")
        Long vehicleId;

        VehicleForDeletion(){}

        VehicleForDeletion(@JsonProperty("vehicleId") Long vehicleId){
            this.vehicleId = vehicleId;
        }

        @Override
        public String toString() {
            return String.format(
                    "VehicleForDeletion[vehicleId=%d]", vehicleId);
        }

        public Long getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(Long vehicleId) {
            this.vehicleId = vehicleId;
        }
    }
}



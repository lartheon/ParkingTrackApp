package com.example.springsecuritymysql.model;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "vehicles")
public class Vehicle {

//    @GeneratedValue(strategy=  GenerationType.AUTO)
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY) @Column(nullable = false, name="vehicleId")
    private Long vehicleId;
    @NotEmpty(message = "Please provide a Reg number")
    @Size( max = 10, message = "Must be less than 10 characters")
    private String regNumber;
    private String make;
    private String model;
    private String colour;

    @ManyToMany(mappedBy = "vehicles")
    Set<Employee> employees;

    protected Vehicle () {}

    public Vehicle (String regNumber, String make, String model, String colour) {
        this.colour = colour;
        this.regNumber = regNumber;
        this.make = make;
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format(
        "Vehicle[vehicle_id=%d, reg_number='%s', colour='%s', make='%s', model='%s']",
        vehicleId, regNumber, colour, make, model);
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}

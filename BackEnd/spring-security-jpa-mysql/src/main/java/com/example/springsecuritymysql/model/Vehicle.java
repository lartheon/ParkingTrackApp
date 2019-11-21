package com.example.springsecuritymysql.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
}

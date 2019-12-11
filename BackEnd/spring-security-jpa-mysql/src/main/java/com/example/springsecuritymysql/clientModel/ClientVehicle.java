package com.example.springsecuritymysql.clientModel;

import java.util.Set;



public class ClientVehicle {


    private Long vehicleId;
    private String regNumber;
    private String make;
    private String model;
    private String colour;

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

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
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

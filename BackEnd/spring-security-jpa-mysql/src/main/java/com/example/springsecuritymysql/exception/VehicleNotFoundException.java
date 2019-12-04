package com.example.springsecuritymysql.exception;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(Long id) {
        super("Could not find vehicle " + id);
    }

    public VehicleNotFoundException(int permitNumber) {
        super("Could not find vehicle with permit number " + permitNumber);
    }
}
package com.example.springsecuritymysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springsecuritymysql.exception.VehicleNotFoundException;
import com.example.springsecuritymysql.model.Vehicle;
import com.example.springsecuritymysql.repository.VehicleRepository;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class VehicleController {

    @Autowired
    private VehicleRepository repository;
    /*
    private final VehicleRepository repository;


    VehicleController(VehicleRepository repository) {
        this.repository = repository;
    }
*/
    // Aggregate root

    @GetMapping("/api/vehicles")
    List<Vehicle> all() {
        return repository.findAll();
    }

    @PostMapping("/api/vehicles")
    Vehicle newVehicle(@Valid @RequestBody Vehicle newVehicle) {
        return repository.save(newVehicle);
    }

    // Single item

    @GetMapping("/api/vehicles/{id}")
    Vehicle one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
    }
/*
    @GetMapping("/api/vehicles/searchByPermit")
    List<Vehicle> permitNumber(@RequestParam int number) {
        return repository.findByPermitNumber(number);
    }

    @GetMapping("/api/vehicles/searchByReg")
    List<Vehicle> regNumber(@RequestParam String reg) {
        return repository.findByRegNumber(reg);
    }

    @GetMapping("/api/vehicles/searchByName")
    List<Vehicle> nameSearch(@RequestParam String name) {
        return repository.findByName(name);
    }
*/
  /*
  @PutMapping("/api/vehicles/{id}")
  Vehicle replaceVehicle(@RequestBody Vehicle newVehicle, @PathVariable Long id) {
    return repository.findById(id)
        .map(vehicle -> {
          vehicle.setName(newVehicle.getName());
          vehicle.setRole(newVehicle.getRole());
          return repository.save(vehicle);
        })
        .orElseGet(() -> {
          newVehicle.setId(id);
          return repository.save(newVehicle);
        });
  }
  */

    @DeleteMapping("/api/vehicles/{id}")
    void deleteVehicle(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

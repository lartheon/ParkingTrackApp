package com.example.springsecuritymysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.example.springsecuritymysql.exception.VehicleNotFoundException;
import com.example.springsecuritymysql.model.Vehicle;
import com.example.springsecuritymysql.repository.VehicleRepository;

import javax.validation.Valid;

//@CrossOrigin(maxAge = 3600)//@RequestMapping("/api/vehicles")
//@CrossOrigin(origins = {"http://localhost:3000"})

@SessionAttributes({"currentUser"})
@Transactional
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
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/api/vehicles")
    List<Vehicle> all() {
        return repository.findAll();
    }

    //    @CrossOrigin(origins = {"http://localhost:3000"})

    @PostMapping("/api/vehicles")
    Vehicle newVehicle(@Valid @RequestBody Vehicle newVehicle) {
        return repository.save(newVehicle);
    }

    // Single item
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
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
    @Transactional
    @Modifying
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/api/vehicles/{id}")
    Vehicle replaceVehicle(@RequestBody Vehicle newVehicle, @PathVariable Long id) {
        return repository.findById(id)
                .map(vehicle -> {
                    vehicle.setVehicleId(id);
                    vehicle.setColour(newVehicle.getColour());
                    vehicle.setMake(newVehicle.getMake());
                    vehicle.setModel(newVehicle.getModel());
                    vehicle.setRegNumber(newVehicle.getRegNumber());
                    return repository.save(vehicle);
                })
                .orElseGet(() -> {
//          newVehicle.setId(id);
                    return repository.save(newVehicle);
                });
    }

    /*
    @Transactional
    @Modifying
    @CrossOrigin(origins = {"http://localhost:3000"})
    @DeleteMapping("/api/vehicles/{id}")
    void deleteVehicle(@PathVariable Long id) {
        System.out.println("VehicleController deleteVehicle : " + id);
        repository.deleteById(id);
    }*/
}

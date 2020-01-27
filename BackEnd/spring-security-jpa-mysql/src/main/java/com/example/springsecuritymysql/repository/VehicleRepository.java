package com.example.springsecuritymysql.repository;

import com.example.springsecuritymysql.model.Employee;
import com.example.springsecuritymysql.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM vehicles v WHERE v.vehicle_id = ?1", nativeQuery = true)
    void deleteVehicleInTable(@Param("id") Long id);

/*
    List<Vehicle> findByReg(int regNumber);
    @Query(
            value = "SELECT * FROM employees e WHERE concat(e.first_name, e.last_name) LIKE concat('%',:name,'%')",
            nativeQuery = true
    )
    List<Vehicle> findByName(@Param("name") String name);
    List<Vehicle> findByName(@Param("name") String name);
    @Query(
            value = "SELECT * FROM vehicles e LEFT JOIN vehicles_vehicles ev ON e.vehicle_id = ev.vehicle_id LEFT JOIN vehicles v ON ev.vehicle_id = v.vehicle_id WHERE v.reg_number LIKE CONCAT('%', :reg, '%')",
            nativeQuery = true
    )
    List<Vehicle> findByRegNumber(@Param("reg") String reg);
     */
}

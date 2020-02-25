package com.example.springsecuritymysql.repository;

import com.example.springsecuritymysql.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(
            value = "SELECT * FROM employees e WHERE e.permit_number = ?1",
            nativeQuery = true
    )
    List<Employee> findByPermitNumber(@Param("permit_number") String permitNumber);

    @Query(
            value = "SELECT * FROM employees e WHERE concat(e.first_name, e.last_name) LIKE concat('%',:name,'%')",
            nativeQuery = true
    )
    List<Employee> findByName(@Param("name") String name);

    @Query(
            value = "SELECT * FROM employees e LEFT JOIN employees_vehicles ev ON e.employee_id = ev.employee_id LEFT JOIN vehicles v ON ev.vehicle_id = v.vehicle_id WHERE v.reg_number LIKE CONCAT('%', :reg, '%')",
            nativeQuery = true
    )
    List<Employee> findByRegNumber(@Param("reg") String reg);

    @Query(value = "SELECT * FROM employees e WHERE e.deleted = false",
            nativeQuery = true)
    Optional<List<Employee>> findAllInTable();

    @Query(value = "SELECT * FROM employees e WHERE e.employee_id = ?1",
            nativeQuery = true)
    Optional<Employee> findByIdInTable(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM employees_vehicles ev WHERE ev.vehicle_id = :id", nativeQuery = true)
    void deleteEmployeeVehicleInTable(@Param("id") Long id);

    @Query(value = "SELECT * FROM employees e WHERE e.email = ?1", nativeQuery = true)
    List<Employee> findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM employees e WHERE e.email = ?1", nativeQuery = true)
    Optional<List<Employee>> findEmployeeByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM employees e WHERE e.email = ?1 AND e.password = ?2", nativeQuery = true)
    Optional<Employee> findByEmailAndHash(@Param("email") String email, @Param("password") String password);

}
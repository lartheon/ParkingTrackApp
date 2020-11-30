package com.example.springsecuritymysql.repository;

import com.example.springsecuritymysql.model.Employee;
import com.example.springsecuritymysql.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {


    @Query(value = "SELECT * FROM roles r WHERE r.role_id = ?1",
            nativeQuery = true)
    Role findByRoleId(@Param("id") int roleId);

    @Query(
            value = "SELECT * FROM roles r LEFT JOIN employee_roles er ON r.role_id = er.role_id LEFT JOIN employees e ON e.employee_id = er.employee_id WHERE e.email = ?1",
            nativeQuery = true
    )
    Employee findRoleByEmail(@Param("email") String email);

}

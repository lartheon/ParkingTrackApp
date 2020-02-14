package com.example.springsecuritymysql;

import com.example.springsecuritymysql.repository.EmployeeRepository;
import com.example.springsecuritymysql.repository.RoleRepository;
import com.example.springsecuritymysql.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityJpaMysqlApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJpaMysqlApplication.class, args);
	}

}

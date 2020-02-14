package com.example.springsecuritymysql;

import com.example.springsecuritymysql.repository.EmployeeRepository;
import com.example.springsecuritymysql.security.CustomUserDetails;
import com.example.springsecuritymysql.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

public class EmployeeServiceImplIntegrationTest {
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public CustomUserDetailsService employeeService() {
            return new CustomUserDetailsService();
        }
    }

    @Autowired
    private CustomUserDetailsService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    // write test cases here
}

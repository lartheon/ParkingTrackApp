package com.example.springsecuritymysql.service;

import com.example.springsecuritymysql.model.Employee;
import com.example.springsecuritymysql.repository.EmployeeRepository;
import com.example.springsecuritymysql.repository.VehicleRepository;
import com.example.springsecuritymysql.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository eRepository;

    @Autowired
    private VehicleRepository vRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {

        Employee employee = this.eRepository.findEmployeeByEmail(emailAddress).orElseThrow(
                () -> new UsernameNotFoundException("Unable to find a user with these credentials: " + emailAddress)).get(0);
        if (employee == null) {
            throw new UsernameNotFoundException("Unable to find a user with these credentials: " + emailAddress);
        } else {
            return CustomUserDetails.create(employee);
        }
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Employee employee = eRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(("Unable to find a user with this id: " + id))
        );
        return CustomUserDetails.create(employee);
    }


}
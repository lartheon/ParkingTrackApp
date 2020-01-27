package com.example.springsecuritymysql.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails extends Employee implements UserDetails {
    Employee employee;
    public CustomUserDetails(final Employee employee){
        super(employee);
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return employee.getRoles().stream().map(authority -> {
            System.out.println("Authority: " + authority.getRole());
           return new SimpleGrantedAuthority(authority.getRole().toString());
        }).collect(Collectors.toList());

        /*return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());*/
    }

    @Override
    public String getPassword(){
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public Employee getUserDetails() {
        return employee;
    }

}

package com.example.springsecuritymysql.security;


import com.example.springsecuritymysql.model.Employee;
import com.example.springsecuritymysql.model.Role;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String name;
    private String username;

    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;
    private GrantedAuthority gAuthorities;
    private String role;

    public CustomUserDetails(Long employeeID, String firstName, String lastName, String skypeId, String email, String password, String dept, String permitNo, Collection<? extends GrantedAuthority> roles){
        this.id = employeeID;
        this.email = email;
        this.username = email;
        this.password = password;
        this.authorities = roles;
    }
    public CustomUserDetails(Long employeeID, String firstName, String lastName, String skypeId, String email, String password, String dept, String permitNo,  GrantedAuthority roles){
        this.id = employeeID;
        this.email = email;
        this.username = email;
        this.password = password;
        this.gAuthorities = roles;
    }
    public CustomUserDetails(Long employeeID, String firstName, String lastName, String skypeId, String email, String password, String dept, String permitNo,  String role){
        this.id = employeeID;
        this.email = email;
        this.username = email;
        this.password = password;
        this.role = role;
    }
    public CustomUserDetails(Employee employee){
        this.id = employee.getEmployeeId();
        this.email = employee.getEmail();
        this.username = employee.getEmail();
        this.password = employee.getPassword();
        this.authorities = employee.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role.getRole().name())
        ).collect(Collectors.toList());
    }

    public static CustomUserDetails create(Employee employee){

        List<GrantedAuthority> authorities = employee.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role.getRole().name())
        ).collect(Collectors.toList());

        if(authorities.isEmpty() && employee.getEmployeeId() != 1){ //check employee id isn't the admin account
            authorities = Collections.singletonList((GrantedAuthority) new SimpleGrantedAuthority(AuthorityType.ROLE_USER.name()));
        }

        return new CustomUserDetails(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSkypeId(),
                employee.getEmail(),
                employee.getPassword(),
                employee.getDept(),
                employee.getPermitNumber(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public GrantedAuthority getgAuthorities() {
        return gAuthorities;
    }

    public String getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUserDetails that = (CustomUserDetails) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}

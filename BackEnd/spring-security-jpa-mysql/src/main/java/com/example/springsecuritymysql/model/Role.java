package com.example.springsecuritymysql.model;

import com.example.springsecuritymysql.security.AuthorityType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long roleId;


    @Enumerated(EnumType.STRING)
    @JsonProperty("role")
    @Column(name = "role_type", nullable = false, columnDefinition = "TEXT default ROLE_USER")
     private AuthorityType role;

    @ManyToMany(mappedBy = "roles")
    Collection<Employee> employees;

    public Role(){}

    public Role(Long roleId, AuthorityType role){
        this.roleId = roleId;
        this.role = role;
    }
    public Role create(Long roleId){
        AuthorityType authorityType;
        if(roleId == 1){
            authorityType = AuthorityType.ROLE_ADMIN;
        }else{
            authorityType = AuthorityType.ROLE_USER;
        }
        return new Role(roleId, authorityType);
    }
    public Role(AuthorityType role){ this.role = role;}

    public Long getRoleId() { return roleId; }

    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public AuthorityType getRole() {
        return role;
    }

    public void setRole(AuthorityType role) {
        this.role = role;
    }

}

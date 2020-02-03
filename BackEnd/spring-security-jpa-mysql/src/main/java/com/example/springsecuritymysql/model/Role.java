package com.example.springsecuritymysql.model;

import com.example.springsecuritymysql.security.AuthorityType;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
     private AuthorityType role;

    public Role(){}

    public Role(AuthorityType role){ this.role = role;}

    public int getRoleId() { return roleId; }

    public void setRoleId(int roleId) { this.roleId = roleId; }

    public AuthorityType getRole() {
        return role;
    }

    public void setRole(AuthorityType role) {
        this.role = role;
    }

}

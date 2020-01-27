package com.example.springsecuritymysql.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_type")
//    private String role;
    @Enumerated(EnumType.STRING)
    private AuthorityType role;

    public Role(){}

    public Role(AuthorityType name){
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public AuthorityType getRole() {
        return role;
    }

    public void setRole(AuthorityType role) {
        this.role = role;
    }
//    public int getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(int roleId) {
//        this.roleId = roleId;
//    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
}

package com.example.springsecuritymysql.controller;

import com.example.springsecuritymysql.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"currentUser"})
@Transactional
@RestController
public class RoleController {

    @Autowired
    private RoleRepository repository;

    public RoleController(RoleRepository roleRepository){
        this.repository = roleRepository;
    }

}

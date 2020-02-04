package com.example.springsecuritymysql.controller;

import com.example.springsecuritymysql.clientModel.ClientEmployee;
import com.example.springsecuritymysql.clientModel.ClientVehicle;
import com.example.springsecuritymysql.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelConverter {
    public ClientEmployee convert (Employee employee) {
        ClientEmployee clientEmployee= new ClientEmployee();
        clientEmployee.setEmployeeId(employee.getEmployeeId());
        clientEmployee.setFirstName(employee.getFirstName());
        clientEmployee.setLastName(employee.getLastName());
        clientEmployee.setPermitNumber(employee.getPermitNumber());
        clientEmployee.setEmail(employee.getEmail());
        clientEmployee.setSkypeId(employee.getSkypeId());
        clientEmployee.setDept(employee.getDept());
        clientEmployee.setForDeletion(employee.getForDeletion());
        clientEmployee.setRoles(employee.getRoles());

        // map all vehicles to the client employee. Create a loop.
        employee.getVehicles().forEach((vehicle)->{
            ClientVehicle clientVehicle = new ClientVehicle();
            clientVehicle.setVehicleId(vehicle.getVehicleId());
            clientVehicle.setRegNumber(vehicle.getRegNumber());
            clientVehicle.setMake(vehicle.getMake());
            clientVehicle.setModel(vehicle.getModel());
            clientVehicle.setColour(vehicle.getColour());
            clientEmployee.getVehicles().add(clientVehicle);
        });

        return clientEmployee;
    }

    public List<ClientEmployee> convert (List<Employee> employees) {
        List<ClientEmployee> clientEmployees = new ArrayList<>();
        employees.forEach((employee -> {
            clientEmployees.add(convert(employee));
        }));
        return clientEmployees;
    }

}

// get it to return client model

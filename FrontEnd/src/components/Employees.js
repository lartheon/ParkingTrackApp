import React from 'react';
import Card from 'react-bootstrap/Card';
import Table from 'react-bootstrap/Table';
import {BrowserRouter as Router, Switch, Route, Link} from "react-router-dom";
// We are passing the props through this function named: Employee.
function Employees(props) {
  console.log('Employees searched: '+JSON.stringify(props.searched));
  if (props.searched) { // Employee card as an array list of values.
        let cards = [];
        console.log(JSON.stringify(props.employees));
        if (props.employees.length > 0) { // using the map method to create a new array by calling a provided function on every element in the calling array named: cards.
            cards = props.employees.map((employee) => { // This variable is named: employeeCars and the value is set to whats on the right side.
                let employeeCars;
                if (employee.vehicles) {

                    employeeCars = employee.vehicles.map((vehicle,idx) => { // Will return the below values from the vehicle table.
                    return (
                        <tr 
                        key={idx}
                        // key={employee.vehicles[0].vehicleId}
                        >
                            <td>{
                                vehicle.regNumber
                            }</td>
                            <td>{
                                vehicle.make
                            }</td>
                            <td>{
                                vehicle.model
                            }</td>
                            <td>{
                                vehicle.colour
                            }</td>
                        </tr>
                    )
                });
            } else {
                employeeCars = ()=> {
                    return <div>No vehicles found</div>
                }
            }
                // console.log('carId: '+ employee.vehicles[0].vehicleId ?? '');
                // Returning values from the employees table.
                return (
                    <Card 
                    key={
                            employee.employeeId
                        }
                        className="card-spacer">
                        <Card.Body> {/* These are refering to the Employee.java > @Id > private String */}
                            <Card.Title>
                                <b>Name: </b>
                                {employee.firstName} {employee.lastName}
                            </Card.Title>
                            <Card.Title>
                                <b>Email:  </b>
                                {employee.email}
                            </Card.Title>
                                 <Card.Title>
                                 <b>Skype: </b>
                                {employee.skypeId}
                            </Card.Title>
                            <Card.Title>
                                <b>Department: </b>
                                {employee.dept}
                            </Card.Title>
                            <br/>
                            <Card.Text>
                                <b>Permit Number: </b>
                                {employee.permitNumber}
                            </Card.Text>
                            <Table responsive="sm">
                                <thead>
                                    <tr>
                                        <th>Reg</th>
                                        <th>Make</th>
                                        <th>Model</th>
                                        <th>Colour</th>
                                    </tr>
                                </thead>
                                <tbody>{employeeCars}</tbody>
                            </Table>


                            {/*  Edit button link to edit Employees details/card: */}
                            
                            <Link to={"/EmployeesForm/" + employee.employeeId} className="btn btn-primary" role="button">Edit My Card</Link>

                        </Card.Body>
                    </Card>
                )
            });
        }
        if (cards.length > 0) {
            return (
                <div className={
                    props.className
                }>
                    {cards}</div>
            );
        }
        let className = "alert alert-warning " + props.className;
        return (
            <div className={className}
                role="alert">
                {/* Unable to find any employees matching that search criteria. */}
                <p>Alrt! Invalid Input. Please enter a valid search term.</p>
            </div>
        );
    }
    return (
        <div>nothing to return</div>
    );
}

export default Employees;

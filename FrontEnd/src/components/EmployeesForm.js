import React, { useState } from 'react';
import App from '../App';
import { withRouter } from 'react-router';
import { BrowserRouter as Router, Switch, Route, Link, useParams } from "react-router-dom";
// By defeault we don't have any errors.

const initialState = {
    success: false,
    fail: false,
    error401: false,
    error401POST: false,
    error500: false,
    employeeId: "",
    firstName: "",
    lastName: "",
    permitNumber: "",
    email: "",
    skypeId: "",
    dept: "",
    password: "",
    confirmPassword: "",
    role: "",

    vehicles: [{
        vehicleId: "",
        regNumber: "",
        make: "",
        model: "",
        colour: "",
    }],

    forDeletion: [{
        vehicleId: "",
    }],

    firstNameError: "",
    lastNameError: "",
    permitNumberError: "",
    emailAddressError: "",
    skypeIdError: "",
    departmentError: "",

    regNumberError: "",
    makeError: "",
    modelError: "",
    colourError: "",

    agreed: false,
    agreedError: "",

    isValid: false
};
// Regular Expressions or RegEx: 1. set it here but call it further down:
const validEmailRegex = RegExp(/^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i);
const api_endpoint = "http://localhost:3000/api/employees";

class EmployeesForm extends React.Component {
    // PT2: Renamed state
    // We don't need all the let statements anymore.
    state = initialState;

    constructor(props) {
        super(props);
        const employeeId = this.props.match.params.employeeId;
        console.log(employeeId);

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

        if (employeeId) {
            this.callAPI(employeeId);
        }
    }

    // Calling the endpoints(API) in the server side (grabing the endpoints from the backend) to get a employee Id.
    callAPI(employeeId) {
        console.log(api_endpoint + "/" + employeeId);
        fetch(api_endpoint + "/" + employeeId, {
            method: 'GET',
            headers:
            {
                'Authorization': localStorage.getItem('Authorization'),
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        })
            .then(res => {
                console.log('auth status ' + res.statusText);
                // res.text();
                if (res.status === 200) {
                    res.text().then(
                        res => {
                            const employee = JSON.parse(res);
                            this.setState(employee);
                            console.log('PRINTING EMPLOYEE DETAILS: ' + JSON.stringify(employee))
                        });

                }
            }

            )
    };
    // vehicles field with an Array:




    // POST request to server endpoint. data = JSON. Passing data from frontend to server.
    callPostAPI(endpoint, data) {
        console.log('POST api: ' + api_endpoint + "/");
        // console.log('data res: ' + JSON.stringify(data));
        fetch(api_endpoint + "/", {
            method: 'POST',
            headers:
            {
                // 'Authorization': localStorage.getItem('Authorization'),
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)

        })
            .then(res => {

                if (res.status === 200) {
                    res.text().then(res => {
                        localStorage.setItem('parking-employee-jwt', res);
                        this.setState({ employees: JSON.parse(res) });

                        this.setState({ success: true, fail: false, error401POST: false, error401: false, error500: false });
                        setTimeout(function () {
                            this.props.history.push("/EmployeesForm/" + this.state.employees.employeeId);
                            this.setState({ success: false, fail: false, error401POST: false, error401: false, error500: false });
                        }.bind(this), 1000);

                    });
                }
                if (res.status === 401) {
                    //permission denied
                    console.error('permission denied: ' + res.status);
                    this.setState({ error401POST: true, error401: false, error500: false });
                }
                if (res.status === 500) {
                    //server error
                    console.error('server error: ' + res.status);
                    this.setState({ error401POST: false, error401: false, error500: true });
                }
                else {
                    console.log(res.status + " " + res.statusText);
                    this.setState({ success: false, fail: true });
                }
            });
    }

    callPutAPI(endpoint, data) {
        console.log('callPutAPI: ' + api_endpoint + "/" + endpoint + data.employeeId);
        // console.log('callPutAPI res: ' + JSON.stringify(data));
        fetch(api_endpoint + "/" + endpoint + data.employeeId, {
            method: 'PUT',
            headers: {
                // 'Access-Control-Allow-Origin': 'http://localhost:3000',
                // "Access-Control-Allow-Credentials" : true,
                // 'Access-Control-Allow-Methods': 'PUT',
                'Authorization': localStorage.getItem('Authorization'),
                'Accept': 'application/json',
                'Content-Type': 'application/json',

            },
            body: JSON.stringify(data)

        })
            .then(res => {
                if (res.status === 200) {
                    res.text().then(res => {
                        console.log('PUT res: ' + res);
                        localStorage.setItem('parking-employee-jwt', res)
                        this.setState({ employees: JSON.parse(res) })
                    });
                    this.setState({ success: true, fail: false });
                    // this.props.history.push("/EmployeesForm/" + this.state.employeeId);
                    setTimeout(function () {
                        this.props.history.push("/EmployeesForm/" + this.state.employeeId);
                        this.setState({ success: false, fail: false, error401POST: false, error401: false, error500: false });
                    }.bind(this), 1000);
                }
                if (res.status === 401) {
                    //permission denied
                    console.error('permission denied: ' + res.status);
                    this.setState({ error401: true, error500: false });
                }
                if (res.status === 500) {
                    //server error
                    console.error('server error: ' + res.status);
                    this.setState({ error401: false, error500: true });
                } else {
                    console.error(res.status + " " + res.statusText)
                }
            });
    }



    //  Start of Basic Contact Details:
    // Is this the event handler?
    handleChange = (event) => {
        console.log("handleChange method called")
        const isCheckbox = event.target.type === "checkbox";

        // PT - this will pick up field names that are not defined in the
        // state
        if (typeof this.state[event.target.name] == 'undefined' ||
            typeof this.state.vehicles[event.target.name] == 'undefined') {
            console.log(`Unknown field name ${event.target.name} - check your field names`)
        }
        // PT: Changed from stateState
        // PT: I cannot see where this is defined in the
        // state variable
        console.log("dataset name " + [event.target.name])
        if (["regNumber", "make", "model", "colour"].includes(event.target.name)) {
            let vehicles = [...this.state.vehicles]
            vehicles[Number(event.target.dataset.uid)][event.target.name] = event.target.value
            this.setState({ vehicles }, () => console.log("handling change " + this.state.vehicles))
        } else {
            this.setState({
                [event.target.name]: isCheckbox ? event.target.checked : event.target.value,
                firstNameError: "",
                lastNameError: "",
                permitNumberError: "",
                emailAddressError: "",
                skypeIdError: "",
                departmentError: "",
                passwordError: ""
            });
        }
    };

    // Function named validate, when the user clicks Submit we want to validate and show any errors that exist.
    // Checking for any errors.
    validate = () => {
        let isValid = true;

        if (!this.state.firstName) {
            //PT: setState to set the firstName into the state so the dom will update and
            //    set the error div below
            console.error('first name cannot be blank');
            this.setState({ firstNameError: 'first name cannot be blank' });
            isValid = false;
        }

        if (!this.state.lastName) {
            //PT: SetState to set the lastname into the state so the dom will update and
            //    set the error div below
            console.error('last name cannot be blank');
            this.setState({ lastNameError: 'last name cannot be blank' });
            isValid = false;
        }

        if (!this.state.permitNumber) {
            console.error('permit number cannot be blank');
            this.setState({ permitNumberError: 'permit number cannot be blank' });
            isValid = false;
        }

        // Regular Expressions: 2. calling it here:
        if (!validEmailRegex.test(this.state.email)) {
            console.error('invalid email entered');
            this.setState({ emailAddressError: 'invalid email entered' });
            isValid = false;
        }

        if (!this.state.skypeId.includes('live.')) {
            console.error('invalid skype id entered');
            this.setState({ skypeIdError: 'invalid skype id entered' });
            isValid = false;
        }

        if (!this.state.dept) {
            console.error('invalid department entered');
            this.setState({ departmentError: 'invalid department entered' });
            isValid = false;

        }

        if (!this.state.employeeId) {
            const { password, confirmPassword } = this.state;
            // perform all neccassary validations
            if (password !== confirmPassword) {
                // alert("Passwords don't match");
                this.setState({ passwordError: 'password does not match!' });
                isValid = false;
            } else {
                // make API call
                isValid = true;
                this.setState({ "password": confirmPassword })
            }
        }

        let v;

        if (this.state.vehicles) {

            v = this.state.vehicles.map((vehicle, idx) => {
                console.log('VALIDATING VEHICLE DETAILS ::: ' + JSON.stringify(vehicle))
                if (!vehicle.regNumber) {
                    console.error('invalid vehicle registration number entered : ' + this.state.vehicles[idx].regNumber);
                    this.setState({ regNumberError: 'invalid registration number entered' });
                    isValid = false;
                }

                if (!vehicle.make) {
                    console.error('invalid make entered : ' + vehicle.make);
                    this.setState({ makeError: 'invalid make entered' });
                    isValid = false;

                }
                if (!vehicle.model) {
                    console.error('invalid model entered : ' + vehicle.model);
                    this.setState({ modelError: 'invalid model entered' });
                    isValid = false;
                }

                if (!vehicle.colour) {
                    console.error('invalid colour entered : ' + vehicle.colour);
                    this.setState({ colourError: 'invalid colour entered' });
                    isValid = false;
                }

                return v;
            });
        }

        if (!this.state.agreed) {
            console.error('You must agree before submitting');
            this.setState({ agreedError: 'You must agree before submitting' });
            isValid = false;
        }

        return isValid;
    };

    addVehicle = (e) => {
        console.log("addVehicle")
        // create a new element (row/ vehicle).
        this.state.vehicles.push({
        });
        // sets the vehicle and show on page.
        this.setState(this.state);

    }


    deleteVehicle = (event) => {
        this.state.forDeletion = this.state.forDeletion || [];

        let index = this.state.vehicles.findIndex((vehicle) => {
            // This should give us the vehicle id that we'll delete.
            console.log('deleting vehicle with id: ' + vehicle.vehicleId)
            this.state.forDeletion.push({ vehicleId: vehicle.vehicleId })

            return event === vehicle.vehicleId;
        })

        this.state.vehicles.splice(index, 1);
        this.setState(this.state);
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const isValid = this.validate();

        console.log('handleSubmit this state :::\n' + JSON.stringify(this.state));
        // If the form validates, we want to store the data or values into our Database but How do I do this?
        // at the moment it clears the user input on submit.


        // Posting data to the server. Empty string.
        // make the API Call here to pass user input to the server side?
        //  This function creates the user ID.
        if (isValid) {
            console.info('Successfully validated the form');
            // clear form
            this.setState(this.state);

            if (this.state.employeeId) {
                console.log('found employeeId: ' + this.state.employeeId + ' sending PUT request')
                //  This function allows employee user to UPDATER edit their card.
                // if already has an ID then its Put.
                console.log('data: ' + JSON.stringify(this.state))
                this.callPutAPI('', this.state);

            } else {
                // If we don't have ID then its Post.
                this.setState({role: "ROLE_USER"});
                console.log('no employeeId: ' + this.state.employeeId + ' sending POST request')
                this.callPostAPI('', this.state);

            }
        } else {
            console.error('The state is not valid');
        }

    };



    render() {
        let employeeCars;
        let error401POST = this.state.error401POST;
        let error401 = this.state.error401;
        let error500 = this.state.error500;

        if (this.state.vehicles) {

            employeeCars = this.state.vehicles.map((vehicle, idx) => { // Will return the below values from the vehicle table.
                // console.log('vehicle: ' + vehicle.regNumber + " id: " + vehicle.vehicleId + " idx: " + idx);
                return (
                    <div key={idx} >
                        <div className="form-row">
                            <div className="form-group col-4">
                                <label>* Vehicle Registration Number: </label>

                                <input type="text" name="regNumber" id={"regNumber" + idx.toString()} className="form-control"
                                    data-uid={idx}
                                    placeholder="Enter your vehicle registration number"
                                    defaultValue={vehicle.regNumber}
                                    onChange={this.handleChange}
                                />
                                <div style={{ fontSize: 18, color: "red" }}>{vehicle.regNumberError}</div>
                            </div>

                            <div className="col-md-3 mb-3">
                                <label>* Make: </label>
                                <input type="text" name="make" id={"make" + idx.toString()} className="form-control"
                                    data-uid={idx}
                                    placeholder="Enter the make your vehicle"
                                    defaultValue={vehicle.make}
                                    onChange={this.handleChange}
                                />
                                <div style={{ fontSize: 14, color: "red" }}>{vehicle.makeError}</div>
                            </div>

                            <div className="col-md-3 mb-3">
                                <label>* Model: </label>
                                <input type="text" name="model" id={"model" + idx.toString()} className="form-control"
                                    data-uid={idx}
                                    placeholder="Enter the model your vehicle" defaultValue={vehicle.model}
                                    onChange={this.handleChange}
                                />
                                <div style={{ fontSize: 14, color: "red" }}>{vehicle.modelError}</div>
                            </div>
                            <div className="col-md-3 mb-3">
                                <label>* Colour: </label>
                                <input type="text" name="colour" id={"colour" + idx.toString()} className="form-control"
                                    data-uid={idx}
                                    placeholder="Enter the colour your vehicle" defaultValue={vehicle.colour}
                                    onChange={this.handleChange}
                                />
                                <div style={{ fontSize: 14, color: "red" }}>{vehicle.colourError}</div>
                            </div>
                            <button className="btn btn-primary" type="button" data-uid={idx} id={"button" + idx.toString()} onClick={() => this.deleteVehicle(vehicle.vehicleId)}>Delete Vehicle <br />(ID: {vehicle.vehicleId})</button>
                        </div>
                        <br />
                    </div>
                )
            });
        } else {
            employeeCars = () => {
                return (<div>No vehicles found</div>)
            }
        }
        const empId = this.state.employeeId;
        function disableField(){
        if(empId){
                      return true;          
        }}
        function passwordMSG(){
            if(empId){
                return 'Change Password?'
            }else{
                return '* Password:'
            }
        }
        return (
            <div className="container pt-4">
                <form onSubmit={this.handleSubmit} >
                    {/*  <form className="needs-validation" novalidate> */}
                    <h3>Your Basic Contact Details:</h3>

                    <div className="form-row mb-3">
                        <div className="form-group col-2">
                            <label>* First Name: </label>
                            {/* the value element will update from the state. */}
                            {/* What is the value of state? React won't know about users typing in the form elements.
                          Developers need to implement an event handler to capture changes with a onchange element. */}
                            {/* Capture changes of a form element using: onChange() as they happen. Update the internal
                          state in event handler. New values are saved in state and then the view is updated by a new render() */}
                            <input type="text" name="firstName" id="firstName" className="form-control" 
                                placeholder="Enter your first name" value={this.state.firstName} disabled = {disableField()}
                                onChange={this.handleChange}
                            />
                            {/* Update any errors that may occure. */}
                            <div style={{ fontSize: 14, color: "red" }}>{this.state.firstNameError}</div>
                        </div>

                        <div className="form-group col-2">
                            <label>* Last Name: </label>
                            <input type="text" name="lastName" id="lastName" className="form-control"
                                placeholder="Enter your last name" value={this.state.lastName} disabled = {disableField()}
                                onChange={this.handleChange}
                            />
                            <div style={{ fontSize: 14, color: "red" }}>{this.state.lastNameError}</div>

                        </div>

                        <div className="form-group col-4">
                            <label>* Email Address: </label>
                            <input type="email" name="email" id="email" className="form-control"
                                placeholder="Enter your Email address" value={this.state.email}
                                onChange={this.handleChange}
                            />
                            <div style={{ fontSize: 18, color: "red" }}>{this.state.emailAddressError}</div>
                        </div>
                    </div>
                    <div className="form-row mb-3">
                        <div className="form-group col-4">
                            <label>* Skype ID: </label>
                            <input type="text" name="skypeId" id="skypeId" className="form-control"
                                placeholder="Enter your Skype id" value={this.state.skypeId}
                                onChange={this.handleChange}
                            />
                            <div style={{ fontSize: 18, color: "red" }}>{this.state.skypeIdError}</div>
                        </div>

                        <div className="form-group col-4">
                            <label>* Department: </label>
                            <input type="text" name="dept" id="dept" className="form-control"
                                placeholder="Enter your department" value={this.state.dept}
                                onChange={this.handleChange}
                            />
                            <div style={{ fontSize: 18, color: "red" }}>{this.state.departmentError}</div>
                        </div>
                    </div>
                    <div className="form-row mb-3">
                        <div className="form-group col-4">
                            <label>* Permit Number: </label>
                            <input type="number" name="permitNumber" id="permitNumber" className="form-control"
                                placeholder="Enter your permit number" value={this.state.permitNumber}
                                onChange={this.handleChange}
                            />
                            <div style={{ fontSize: 18, color: "red" }}>{this.state.permitNumberError}</div>
                        </div>
                    </div>
                    <div className="form-row mb-3">
                        <div className="form-group col-4">
                            <label>{passwordMSG()} </label>
                            <input type="password" name="password" id="password" className="form-control"
                                placeholder="Password" value={this.state.password || ''}
                                onChange={this.handleChange}
                            />
                            <div style={{ fontSize: 18, color: "red" }}>{this.state.passwordError}</div>
                        </div>

                        <div className="form-group col-4">
                            <label>* Confirm Password: </label>
                            <input type="password" name="confirmPassword" id="confirmPassword" className="form-control"
                                placeholder="Confirm Password" value={this.state.confirmPassword}
                                onChange={this.handleChange}
                            />
                            <div style={{ fontSize: 18, color: "red" }}>{this.state.passwordError}</div>
                        </div>
                    </div>
                    <br />

                    {/*  End of Basic Contact Details. */}

                    {/* Start of Vehicle Details: */}
                    <p><button className="btn btn-primary" type="button" onClick={this.addVehicle}>Add Vehicle</button></p>
                    <h3>About Your Vehicle: </h3>
                    {employeeCars}
                    {console.log('RENDER Called :: current state is \n' + JSON.stringify(this.state))}

                    {/* End of Vehicle Details */}

                    <div className="form-group">
                        <div className="form-check">
                            <input className="form-check-input" name="agreed" type="checkbox"
                                value={this.state.agreed}
                                onChange={this.handleChange}
                            />
                            <label className="form-check-label">
                                Agree to terms and conditions
                            </label>
                            <div style={{ fontSize: 14, color: "red" }}>{this.state.agreedError}</div>
                        </div><br />

                    </div>
                    {error401POST && (
                        <div className="alert alert-danger"><span aria-label='permission denied' role="img">🚫</span> Permission Denied! A user with this email already exists!</div>
                    )}
                    {error401 && (
                        <div className="alert alert-danger"><span aria-label='permission denied' role="img">🚫</span> Permission Denied! You need to login to perform this action!</div>
                    )}
                    {error500 && (
                        <div className="alert alert-danger"><span aria-label='shrug' role="img">🤷</span> Oops! Something went wrong on our end!</div>
                    )}
                    <div>
                        {this.state.success && (
                            <div
                                className="alert alert-success"
                            > Form submitted!</div>
                        )}
                    </div>
                    <button className="btn btn-primary" type="submit">Submit form</button>
                    <Link to="/" className="btn btn-primary" role="button">Home</Link>

                </form>

            </div>
        );
    }
}
export default withRouter(EmployeesForm);

// PT: We should not need this as we are already default exporting in the classdef at the top
// export default EmployeesForm;


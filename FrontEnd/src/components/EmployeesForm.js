import React from 'react';
import App from '../App';
import { withRouter } from 'react-router';
import {BrowserRouter as Router, Switch, Route, Link, useParams} from "react-router-dom";

// By defeault we don't have any errors.
const initialState = {
    firstName: "",
    lastName: "",
    permitNumber: "",
    emailAddress: "",
    skypeId: "",
    department: "",
    
    regNumber: "",
    make: "",
    model: "",
    colour: "",
    
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
    agreedError: ""
};
// Regular Expressions or RegEx: 1. set it here but call it further down:
const validEmailRegex = RegExp(/^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i);
const api_endpoint = "http://localhost:8080/api/employees";

class EmployeesForm extends React.Component {
    // PT2: Renamed state
    // We don't need all the let statements anymore.
    state = initialState;



    constructor(props) {
        super(props);
        const employeeId = this.props.match.params.employeeId;
        console.log( employeeId );

        if (employeeId) {
            this.callAPI(employeeId);
        }
    }

    // Calling the endpoints(API) in the server side (grabing the endpoints from the backend) to get a employee Id.
    callAPI(employeeId) {
        console.log(api_endpoint + "/" + employeeId);
        fetch(api_endpoint + "/" + employeeId).then(res => res.text()).then(res => {
            // employee object being passed into the form.
            const employee = JSON.parse(res);
            this.setState(employee);

            
            /*let vehicle = {};
            if (employee.vehicles && employee.vehicles.length > 0) {
                vehicle.regNumber = employee.vehicles[0].regNumber;
                vehicle.make = employee.vehicles[0].make;
                vehicle.model = employee.vehicles[0].model;
                vehicle.colour = employee.vehicles[0].colour;
            }
            const formEmployee = {
                // as we did before but the other way round.
                firstName: employee.firstName,
                lastName: employee.lastName,
                permitNumber: employee.permitNumber,
                emailAddress: employee.email,
                skypeId: employee.skypeId,
                department: employee.dept,
                regNumber: vehicle.regNumber,
                make: vehicle.make,
                model: vehicle.model,
                colour: vehicle.colour
            };
            this.setState(formEmployee);*/
        });
// vehicles field with an Array:



    }


    

    // Post request to server endpoint. data = JSON. Passing data from frontend to server.
    callPostAPI(endpoint, data) {
      console.log(api_endpoint + "/" + endpoint);
      fetch(api_endpoint + "/" + endpoint, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
  
      })
        .then(res => res.text())
        .then(res => this.setState({ employees: JSON.parse(res) }));
    }



    //  Start of Basic Contact Details:
    // Is this the event handler?
    handleChange = event => {
        const isCheckbox = event.target.type === "checkbox";

        // PT - this will pick up field names that are not defined in the
        // state
        if (typeof this.state[event.target.name] == 'undefined') {
            console.error(`Unknown field name ${event.target.name} - check your field names`)
        }
        // PT: Changed from stateState
        // PT: I cannot see where this is defined in the
        // state variable
        this.setState({
            [event.target.name]: isCheckbox ? event.target.checked : event.target.value
        });
    };

    // Function named validate, when the user clicks Submit we want to validate and show any errors that exist.
    // Checking for any errors.
    validate = () => {
        let  isValid = true;

        if (!this.state.firstName) {
            //PT: setState to set the firstName into the state so the dom will update and
            //    set the error div below
            console.error('first name cannot be blank');
            this.setState({firstNameError: 'first name cannot be blank'});
            isValid = false;
        }

        if (!this.state.lastName) {
            //PT: SetState to set the lastname into the state so the dom will update and
            //    set the error div below
            console.error('last name cannot be blank');
            this.setState({lastNameError: 'last name cannot be blank'});
            isValid = false;
        }

        if (!this.state.permitNumber) {
            console.error('permit number cannot be blank');
            this.setState({permitNumberError: 'permit number cannot be blank'});
            isValid = false;
        }

        // Regular Expressions: 2. calling it here:
        if (!validEmailRegex.test(this.state.emailAddress)) {
            console.error('invalid email entered');
            this.setState({emailAddressError: 'invalid email entered'});
            isValid = false;
        }

        if (!this.state.skypeId.includes('live.')) {
            console.error('invalid skype id entered');
            this.setState({skypeIdError: 'invalid skype id entered'});
            isValid = false;
        }

        if (!this.state.department) {
            console.error('invalid department entered');
            this.setState({departmentError: 'invalid department entered'});
            isValid = false;

        }

        if (!this.state.regNumber) {
            console.error('invalid vehicle registration number entered');
            this.setState({regNumberError: 'invalid registration number entered'});
            isValid = false;
        }

        if (!this.state.make) {
            console.error('invalid make entered');
            this.setState({makeError: 'invalid make entered'});
            isValid = false;

        }
        if (!this.state.model) {
            console.error('invalid model entered');
            this.setState({modelError: 'invalid model entered'});
            isValid = false;
        }

        if (!this.state.colour) {
            console.error('invalid colour entered');
            this.setState({colourError: 'invalid colour entered'});
            isValid = false;
        }

        if (!this.state.agreed) {
            console.error('You must agree before submitting');
            this.setState({agreedError: 'You must agree before submitting'});
            isValid = false;
        }

        return isValid;
    };

    addVehicle() {
        // create a new element (row/ vehicle).
        this.vehicles.push({});
        // sets the vehicle and show on page.
        this.setState(this.state);
    }

    handleSubmit = event => {
        event.preventDefault();
        const isValid = this.validate();

        console.log(this.state);
        // If the form validates, we want to store the data or values into our Database but How do I do this?
        // at the moment it clears the user input on submit.
        
        // Posting data to server.
        const data = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            permitNumber: this.state.permitNumber,
            email: this.state.emailAddress,
            skype: this.state.skypeId,
            dept: this.state.department,
            vehicles: [
               /*  {
                    reg_number: this.state.regNumber,
                    make: this.state.make,
                    model: this.state.model,
                    colour: this.state.colour
                } */
            ]
        };
        // Posting data to the server. Empty string.
        // make the API Call here to pass user input to the server side?
        //  This function creates the user ID.
        if(this.state.employeeId) {
        //  This function allows employee user to UPDATER edit their card.
        // if already has an ID then its Put.
        this.callPutAPI('', this.state);
        } else {
            // If don't have ID then its Post.
        this.callPostAPI('', this.state);
        }

        if (isValid) {
            console.info('Successfully validated the form');
            // clear form
            this.setState(initialState);

            

        } else {
            console.error('The state is not valid');
        }
    };

    render() {
        let employeeCars;
        if (this.state.vehicles) {
        employeeCars = this.state.vehicles.map((vehicle) => { // Will return the below values from the vehicle table.
            return (
                <div>
                      <h3>About Your Vehicle: </h3>
                      <div className="form-row">
                          <div className="form-group col-4">
                              <label>* Vehicle Registration Number: </label>
                              <input type="text" name="regNumber" className="form-control" 
                              placeholder="Enter your vehicle registration number" value={vehicle.regNumber}
                              onChange={this.handleChange} />
                              <div style={{ fontSize: 18, color: "red" }}>{vehicle.regNumberError}</div>
                          </div>
                      </div>
                      <br />

                      <div className="form-row">
                          <div className="col-md-3 mb-3">
                              <label>* Make: </label>
                              <input type="text" name="make" className="form-control" 
                              placeholder="Enter the make your vehicle" value={this.state.make} 
                              onChange={this.handleChange} />
                              <div style={{ fontSize: 14, color: "red" }}>{this.state.makeError}</div>
                          </div>

                          <div className="col-md-3 mb-3">
                              <label>* Model: </label>
                              <input type="text" name="model" className="form-control" 
                              placeholder="Enter the model your vehicle" value={this.state.model} 
                              onChange={this.handleChange} />
                                <div style={{ fontSize: 14, color: "red" }}>{this.state.modelError}</div>
                          </div>
                          <div className="col-md-3 mb-3">
                              <label>* Colour: </label>
                              <input type="text" name="colour" className="form-control"  
                              placeholder="Enter the colour your vehicle" value={this.state.colour} 
                              onChange={this.handleChange} />
                                <div style={{ fontSize: 14, color: "red" }}>{this.state.colourError}</div>
                              </div>
                      </div>
                      <br />
                      {/* End of Vehicle Details */}

                      <div className="form-group">
                          <div className="form-check">
                              <input className="form-check-input" name="agreed" type="checkbox" 
                              value={this.state.agreed} onChange={this.handleChange} />
                              <label className="form-check-label">
                                  Agree to terms and conditions
                              </label>
                              <div style={{ fontSize: 14, color: "red" }}>{this.state.agreedError}</div>
                          </div><br />

                      </div>
                  </div>
            )
        });
    } else {
        employeeCars = ()=> {
            return <div>No vehicles found</div>
        }
    }

      return (
          <div className="container pt-4">
              <form onSubmit={this.handleSubmit}>
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
                          <input type="text" name="firstName" className="form-control" 
                          placeholder="Enter your first name" value={this.state.firstName} 
                          onChange={this.handleChange} />
                          {/* Update any errors that may occure. */}
                          <div style={{ fontSize: 14, color: "red" }}>{this.state.firstNameError}</div>
                      </div>

                      <div className="form-group col-2">
                          <label>* Last Name: </label>
                          <input type="text" name="lastName" className="form-control" 
                          placeholder="Enter your last name" value={this.state.lastName} 
                          onChange={this.handleChange} />
                              <div style={{ fontSize: 14, color: "red" }}>{this.state.lastNameError}</div>

                      </div>

                      <div className="form-group col-4">
                          <label>* Email Address: </label>
                          <input type="email" name="emailAddress" className="form-control" 
                          placeholder="Enter your Email address" value={this.state.emailAddress} 
                          onChange={this.handleChange} />
                          <div style={{ fontSize: 18, color: "red" }}>{this.state.emailAddressError}</div>
                      </div>
                  </div>
                  <div className="form-row mb-3">
                      <div className="form-group col-4">
                          <label>* Skype ID: </label>
                          <input type="text" name="skypeId" className="form-control" 
                          placeholder="Enter your Skype id" value={this.state.skypeId} 
                          onChange={this.handleChange} />
                          <div style={{ fontSize: 18, color: "red" }}>{this.state.skypeIdError}</div>
                      </div>

                      <div className="form-group col-4">
                          <label>* Department: </label>
                          <input type="text" name="department" className="form-control" 
                          placeholder="Enter your department" value={this.state.department} 
                          onChange={this.handleChange} />
                          <div style={{ fontSize: 18, color: "red" }}>{this.state.departmentError}</div>
                      </div>
                  </div>
                  <div className="form-row mb-3">
                      <div className="form-group col-4">
                          <label>* Permit Number: </label>
                          <input type="number" name="permitNumber" className="form-control"
                          placeholder="Enter your permit number" value={this.state.permitNumber}
                          onChange={this.handleChange} />
                          <div style={{ fontSize: 18, color: "red" }}>{this.state.permitNumberError}</div>
                      </div>
                  </div>
                  <br />

              {/*  End of Basic Contact Details. */ }

              {/* Start of Vehicle Details: */ }
              <button className="btn btn-primary" type="button" onclick="addVehicle()">Add Vehicle</button>
                  {employeeCars}
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


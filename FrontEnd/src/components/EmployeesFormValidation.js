import React, { Component, useState } from 'react';
import Form from 'react-bootstrap/Form';
import { Row, Col, Grid } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import { ButtonInput } from 'react-bootstrap'

// function EmployeesForm(props) {
//   const [validated, setValidated] = useState(false);

//   const handleSubmit = event => {
//     const form = event.currentTarget;
//     if (form.checkValidity() === false) {
//       event.preventDefault();
//       event.stopPropagation();
//     }
//     setValidated(true);
//   };

// By defeault we don't have any errors.
const initialtState = {
  firstName: "",
  lastName: "",
  permitNumber: "",
  emailAddress: "",
  skypeId: "",
  department: "",
  regNumberError: "",
  make: "",
  model: "",
  colour: "",
  firstNameError: "",
  lastNameError: "",
  permitNumberError: "",
  emailAddressError: "",
  skypeIdError: "",
  departmentError: "",
  regNumber: "",
  makeError: "",
  modelError: "",
  colurError: ""
}

export default class EmployeesForm extends React.Component {
  state = initialtState;


  //  Start of Basic Contact Details:
  handleChange = event => {
    const isCheckbox = event.target.type === "checkbox";

    // PT: Changed from stateState
    // PT: I cannot see where this is defined in the
    // state variable
    this.setState({
      [event.target.name]: isCheckbox
        ? event.target.checked
        : event.target.value
    });
  };

  // Function named validate, when the user clicks Submit we want to validate and show any errors that exist.
  // Checking for any errors.
  // Empty strings.
  validate = () => {
    // PT: once the setState's are in place below, all these let's can be removed
    //     as they will be unused.
    let firstNameError = "";
    let lastNameError = "";
    let permitNumberError = "";
    let emailAddressError = "";
    let skypeIdError = "";
    let departmentError = "";
    let regNumberError = "";
    let makeError = "";
    let modelError = "";
    let colurError = "";

    if (!this.state.firstName) {
      //PT: setState to set the firstName into the state so the dom will update and
      //    set the error div below
      //firstNameError = 'first name cannot be blank';
      this.setState({firstNameError: 'first name cannot be blank'});
      return false;
    }

    if (!this.state.lastName) {
      //PT: SetState to set the lastname into the state so the dom will update and
      //    set the error div below
      //lastNameError = 'last name cannot be blank';
      this.setState({lastNameError: 'last name cannot be blank'});
      return false
    }

    if (!this.state.permitNumber) {
      // PT: repeat as above what was done for lastNameError
      this.setState({permitNumberError: 'permit number cannot be blank'});
      return false
      // permitNumberError = 'permit number cannot be blank';
    }

    // PT: I would use a regex here to validate the email address
    // PT: but anyway this is the wrong way around
    if (!this.state.emailAddress.includes('@', '.')) {
      // repeat as above what was done for lastNameError
      this.setState({emailAddressError: 'invalid email entered'});
      return false
      // emailAddressError = 'invalid email entered'
    }

    if (!this.state.skypeId.includes('live.')) {
      // PT: repeat as above what was done for lastNameError
      this.setState({skypeIdError: 'invalid skype id entered'});
      return false
      // skypeIdError = 'invalid skype id entered'
    }

    if (!this.state.department) {
      // PT: repeat as above what was done for lastNameError
      this.setState({departmentError: 'invalid department entered'});
      return false
      // departmentError = 'invalid department entered'
    }

    if (!this.state.regNumber) {
      // PT: repeat as above what was done for lastNameError
      this.setState({regNumberError: 'invalid registration number entered'});
      return false
      // regNumberError = 'invalid vehicle registration number entered'
    }

    if (!this.state.make) {
      // PT: repeat as above what was done for lastNameError
      this.setState({makeError: 'invalid make entered'});
      return false
      // makeError = 'invalid make entered'
    }
    if (!this.state.model) {
      // PT: repeat as above what was done for lastNameError
      this.setState({modelError: 'invalid model entered'});
      return false
      // modelError = 'invalid model entered'
    };

    // PT: The commented code will not be required.
    // if there is an email or first name error, we're going to set state.
    // if (emailAddressError || firstNameError) {
    //   this.setState({ emailAddressError, firstNameError });
    //   return false;
    // }
    return true;
  };

  // }

  handleSubmit = event => {
    event.preventDefault();
    const isValid = this.validate();

    // PT: What do you plan to do if the form
    // validates - in this case you are just
    // clearing the user input.
    if (isValid) {
      console.log(this.state);
      // clear form
      // PT: Changed from stateState
      this.setState(initialtState);
    }
  };

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          {/*  <form className="needs-validation" novalidate> */}
          <h3>Your Basic Contact Details:</h3>

          <div className="form-row">
            <div className="col-md-4 mb-3">
              <label for="validationCustom01">* First Name: </label>
              <input type="text" name="firstName" className="form-control" id="validationCustom01" placeholder="Enter your first name" value={this.state.firstName} onChange={this.handleChange} required />
              {/* Update any errors that may occure. */}
              <div style={{ fontSize: 10, color: "red" }}>{this.state.firstNameError}</div>
            </div>

            <div className="col-md-4 mb-3">
              <label for="validationCustom02">* Last Name: </label>
              <input type="text" name="lastName" className="form-control" id="validationCustom02" placeholder="Enter your last name" value={this.state.lastName} onChange={this.handleChange} required />
              <div className="valid-feedback">
                <div style={{ fontSize: 10, color: "red" }}>{this.state.lastNameError}</div>
              </div>
            </div>

            <div className="form-group">
              <label for="validationCustom02">* Email Address: </label>
              <input type="email" name="emailAddress" className="form-control" id="validationCustom03" placeholder="Enter your Email address" value="" required />
              <div style={{ fontSize: 10, color: "red" }}>{this.state.emailAddressError}</div>
            </div>

            <div className="form-row">
              <div className="col-md-4 mb-3">
                <label for="validationCustom01">* Skype ID: </label>
                <input type="text" name="skypeId" className="form-control" id="validationCustom01" placeholder="Enter your Skype id" value="" required />
                <div style={{ fontSize: 10, color: "red" }}>{this.state.skypeIdError}</div>
              </div>

              <div className="col-md-4 mb-3">
                <label for="validationCustom02">* Department: </label>
                <input type="text" name="department" className="form-control" id="validationCustom02" placeholder="Enter your department" value="" required />
                <div style={{ fontSize: 10, color: "red" }}>{this.state.departmentError}</div>
              </div>
            </div>

            <div className="form-group">
              <label for="validationCustom02">* Permit Number: </label>
              <input type="number" name="permitNumber" className="form-control" id="validationCustom01" placeholder="Enter your permit number" value="" required />
              <div style={{ fontSize: 10, color: "red" }}>{this.state.permitNumberError}</div>
            </div>
          </div>
          <br />
  </form>

    {/*  End of Basic Contact Details. */ }

    {/* Start of Vehicle Details: */ }

    <form onSubmit={this.handleSubmit}>
      <div>
        <h3>About Your Vehicle: </h3>
        <div className="form-row">
          <div className="">
            <label for="validationCustom01">* Vehicle Registration Number: </label>
            <input type="text" name="regName" className="form-control" id="validationCustom01" placeholder="Enter your vehicle registration number" value="" onChange={this.handleChange} required />
            <div style={{ fontSize: 10, color: "red" }}>{this.state.regNumberError}</div>
          </div>
        </div>
        <br />

        <div className="form-row">
          <div className="col-md-3 mb-3">
            <label for="validationCustom02">* Make: </label>
            <input type="text" name="make" className="form-control" id="validationCustom02" placeholder="Enter the make your vehicle" onChange={this.handleChange} required />
            <div style={{ fontSize: 10, color: "red" }}>{this.state.makeError}</div>
          </div>

          <div className="col-md-3 mb-3">
            <label for="validationCustom03">* Model: </label>
            <input type="text" name="model" className="form-control" id="validationCustom03" placeholder="Enter the model your vehicle" onChange={this.handleChange} required />
            <div className="invalid-feedback">
              <div style={{ fontSize: 10, color: "red" }}>{this.state.modelError}</div>
            </div>
          </div>
          <div className="col-md-3 mb-3">
            <label for="validationCustom04">* Colour: </label>
            <input type="text" name="colour" className="form-control" id="validationCustom04" placeholder="Enter the colour your vehicle" onChange={this.handleChange} required />
            <div style={{ fontSize: 10, color: "red" }}>{this.state.colurError}</div>
          </div>
        </div>
        <br />
          {/* End of Vehicle Details */}

          <div className="form-group">
            <div className="form-check">
              <input className="form-check-input" type="checkbox" value="" id="invalidCheck" onChange={this.handleChange} required />
              <label className="form-check-label" for="invalidCheck">
                Agree to terms and conditions
              </label>
              <div className="invalid-feedback">
                You must agree before submitting.
              </div>

            </div>
            <button className="btn btn-primary" type="submit">Submit form</button>
          </div>
      </div>
    </form>
      </div>
  );
  }
  }

  // PT: We should not need this as we are already default exporting in the classdef at the top
  // export default EmployeesForm;


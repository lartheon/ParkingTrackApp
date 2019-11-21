import React, { Component, useState } from 'react';
import Form from 'react-bootstrap/Form';
import { Row, Col, Grid} from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import { ButtonInput } from 'react-bootstrap'
// import {FormControl, FormGroup, ControlLabel, Button} from 'react-bootstrap';

function EmployeesForm(props) {
  const [validated, setValidated] = useState(false);

  const handleSubmit = event => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }

    setValidated(true);
  };

  

  
 {/* Start of Basic Contact Details: */}

return (  
  <React.Fragment>
    <form className="needs-validation" novalidate>
    <h3>Your Basic Contact Details:</h3>
      <div className="form-row">
        <div className="col-md-4 mb-3">
          <label for="validationCustom01">* First Name: </label>
          <input type="text" class="form-control" id="validationCustom01" placeholder="Enter your first name" value="" required />
          <div className="valid-feedback">
            Looks good!
          </div>
      </div>

        <div className="col-md-4 mb-3">
            <label for="validationCustom02">* Last Name: </label>
            <input type="text" className="form-control" id="validationCustom02" placeholder="Enter your last name" value="" required />
              <div className="valid-feedback">
                Looks good!
              </div>
        </div>
      </div>

      <div className="form-group">
          <label for="validationCustom02">* Email Address: </label>
          <input type="email" className="form-control" id="validationCustom03" placeholder="Enter your Email address" value="" required />
            <div className="valid-feedback">
              Looks good!
            </div>
      </div>

      <div className="form-row">
        <div className="col-md-4 mb-3">
          <label for="validationCustom01">* Skype ID: </label>
          <input type="text" class="form-control" id="validationCustom01" placeholder="Enter your Skype id" value="" required />
            <div className="valid-feedback">
              Looks good!
            </div>
        </div>

        <div className="col-md-4 mb-3">
            <label for="validationCustom02">* Department: </label>
            <input type="text" className="form-control" id="validationCustom02" placeholder="Enter your department" value="" required />
              <div className="valid-feedback">
                Looks good!
              </div>
        </div>
      </div>

        <div className="form-group">
          <label for="validationCustom02">* Permit Number: </label>
          <input type="number" className="form-control" id="validationCustom01" placeholder="Enter your permit number" value="" required />
            <div className="valid-feedback">
              Looks good!
            </div>
        </div>
      <br/>
    </form>
{/* End of Basic Contact Details. */}

{/*  Start of Vehicle Details:  */}
<form className="needs-validation" novalidate>
  <h3>About Your Vehicle: </h3>
  <div className="form-row">
    <div className="">
      <label for="validationCustom01">* Vehicle Registration Number: </label>
      <input type="text" class="form-control" id="validationCustom01" placeholder="Enter your vehicle registration number" value="" required />
        <div className="valid-feedback">
          Looks good!
        </div>
    </div>
  </div>
  <br/>

  <div className="form-row">
    <div className="col-md-3 mb-3">
      <label for="validationCustom02">* Make: </label>
      <input type="text" className="form-control" id="validationCustom02" placeholder="Enter the make your vehicle" required />
      <div className="invalid-feedback">
        Please provide a valid vehicle make.
      </div>
    </div>

    <div className="col-md-3 mb-3">
      <label for="validationCustom03">* Model: </label>
      <input type="text" className="form-control" id="validationCustom03" placeholder="Enter the model your vehicle" required />
        <div className="invalid-feedback">
          Please provide a valid vehicle model.
        </div>
    </div>

    <div className="col-md-3 mb-3">
      <label for="validationCustom04">* Colour: </label>
      <input type="text" className="form-control" id="validationCustom04" placeholder="Enter the colour your vehicle" required />
        <div className="invalid-feedback">
          Please provide a valid vehicle colour.
        </div>
      </div>
    </div>
  <br/>

  {/* End of Vehicle Details */}
      
  <div className="form-group">
    <div className="form-check">
        <input className="form-check-input" type="checkbox" value="" id="invalidCheck" required />
          <label className="form-check-label" for="invalidCheck">
            Agree to terms and conditions
          </label>
            <div class="invalid-feedback">
              You must agree before submitting.
            </div>

            </div>
            <button class="btn btn-primary" type="submit">Submit form</button>
            </div>
  </form>


</React.Fragment>

// Example starter JavaScript for disabling form submissions if there are invalid fields

// Example starter JavaScript for disabling form submissions if there are invalid fields


  )
  
}

export default EmployeesForm;
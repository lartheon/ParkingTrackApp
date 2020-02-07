// import React, { Component } from "react";
import React, { useState } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { withRouter } from "react-router-dom";
import Employees from './Employees';
import { createHashHistory } from 'history'
export const history = createHashHistory();

const initialLoginState = {
    email: "",
    password: "",
    checkbox: true,
};
const root_ = "http://localhost:3000"
const api_ = "http://localhost:3000/api"
const api_endpoint = "http://localhost:3000/api/employeesLogin";

class EmployeeLogin extends React.Component {


    constructor(props) {
        super(props);
        // Below is the state of the App (parent) component.
        this.state = {
            error401: false,
            error500: false,
            success: false,
            fail: false,
            email: "",
            password: "",
            checkbox: true,
            employees: [],
            initialLoginState: initialLoginState,
            searched: false,
            isValid: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange = (event) => {
        const isCheckbox = event.target.type === "checkbox";
        if (typeof this.state[event.target.type] == 'undefined') {
            console.error(`Unknown field name ${event.target.name} - check your field names`)
        }
        this.setState({
            [event.target.type]: isCheckbox ? event.target.checked : event.target.value
        });

    }
    validate = () => {
        console.log("validate login: " + this.state.email + " password: " + this.state.password)
        return this.state.email.length > 0 && this.state.password.length > 0;
    }

    callPostAPI(endpoint, data) {

        fetch(api_endpoint + "/" + endpoint, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': ' Bearer '
            },
            body: JSON.stringify(data)
        })
            .then(res => {
                if (res.status === 200) {
                    console.log('auth status ' + res.statusText);
                    res.headers.get("Authorization");
                    // console.log('jwt auth?? ' + JSON.stringify(res.headers.get("Authorization")));
                    localStorage.setItem('Authorization', res.headers.get("Authorization"));


                    res.json()
                        .then(jsonData => {
                            console.log('PUT respose: ' + JSON.stringify(jsonData));

                            this.setState({ success: true, fail: false });
                            this.props.history.push("/Search");

                        });

                } 
                if(res.status === 401){
                    //permission denied
                    console.error('permission denied: ' + res.status);
                    this.setState({ error401: true, error500: false });
                }
                if(res.status === 500){
                    //server error
                    console.error('server error: ' + res.status);
                    this.setState({ error401: false, error500: true });
                }
                
                else {
                    console.log(res.status + " " + res.body)
                    this.setState({ success: false, fail: true });
                }
            });
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const isValid = this.validate();
        // console.log('handleSubmit this state :::\n' + JSON.stringify(this.state));

        if (isValid) {
            this.callPostAPI('', this.state);
        }
    }

    render() {
        let employees = this.state.employees;
        let isValid = this.state.isValid;
        let searched = this.state.searched;
        let error401 = this.state.error401;
        let error500 = this.state.error500;

        if (!searched || !isValid) {
            return (
                <form onSubmit={this.handleSubmit}>
                    <h3>Sign In</h3>

                    <div className="form-group">
                        <label>Email address</label>
                        <input autoFocus
                            type="email"
                            defaultValue={this.state.email}
                            onChange={this.handleChange}
                            className="form-control" placeholder="Enter email" />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input type="password"
                            defaultValue={this.state.password}
                            onChange={this.handleChange}
                            className="form-control" placeholder="Enter password" />
                    </div>

                    <div className="form-group">
                        <div className="custom-control custom-checkbox">
                            <input type="checkbox" onChange={this.handleChange} className="custom-control-input" id="customCheck1" />
                            <label className="custom-control-label" htmlFor="customCheck1">Remember me</label>
                        </div>
                    </div>

                    <button onSubmit={this.handleSubmit}
                        type="submit"
                        disabled={!this.validate()}
                        className="btn btn-primary btn-block">Submit</button>
                    <p className="forgot-password text-right">
                        Forgot <a href="#">password?</a>
                    </p>
                    <div>{this.state.success && (
                        <div
                            className="alert alert-success"
                        >Form submitted!
                    </div>)}
                       
                        {error401 && (
                            <div className="alert alert-danger"><span aria-label='permission denied' role="img">🚫</span> Unable to login, either the username or password is incorrect!</div>
                                 )}
                        {error500 && (
                            <div className="alert alert-danger"><span aria-label='shrug' role="img">🤷</span> Oops! Something went wrong on our server</div>
                                 )}
                    </div>
                </form>

            );
        } else {
            return (<Employees className="col-10" employees={employees} searched={searched} />)
        }
    }
}
export default withRouter(EmployeeLogin)
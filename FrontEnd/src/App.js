import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import Employees from './components/Employees';
import EmployeesForm from './components/EmployeesForm';
import SearchBar from './components/SearchBar';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from "./components/EmployeesLogin";
import './app.css';
import { Form } from 'react-bootstrap';
import { createHashHistory } from 'history'
export const history = createHashHistory();
// Is this an Object or a variable or both?
const api_endpoint = "http://localhost:3000/api/employees";


// This is a function named: makeLike that takes a parameter of search.
function makeLike(search) {
    return search.replace(/ /g, '%25');
}
// Refers to the dropdown on the search bar to determine which endpoint query is used.
const actions = {
    permitNumber: {
        displayName: "Permit Number",
        endpoint: value => "searchByPermit?number=" + makeLike(value)
    },
    regNumber: {
        displayName: "Reg Number",
        endpoint: value => "searchByReg?reg=" + makeLike(value)
    },
    name: {
        displayName: "Name",
        endpoint: value => "searchByName?name=" + makeLike(value)
    }

};


// The constructor takes in props. The Super takes in props but in a different way.
class App extends React.Component {
    constructor(props) {
        super(props);
        // Below is the state of the App (parent) component.
        this.state = {
            error401: false,
            error403: false,
            error500: false,
            employees: [],
            searchAction: actions.regNumber,
            searched: false,
            searchForm: ""
        };
        this.handleSearchSubmit = this.handleSearchSubmit.bind(this);
        this.handleLogOut = this.handleLogOut.bind(this);
    }

    // Calling the endpoints(API) in the server side (grabing the endpoints from the backend).
    callAPI(endpoint) {
        console.log(api_endpoint + "/" + endpoint);
        fetch(api_endpoint + "/" + endpoint, {
            method: 'GET',
            headers:
            {
                'Authorization': localStorage.getItem('Authorization'),
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        })
        //if res == 401 user hasn't logged in
        .then(
            res => {
                // res.text()
                if(res.status === 200){
                    res.text().then(
                        res => this.setState({employees: JSON.parse(res)})
                    ); 
                }
                if(res.status === 401){
                    //permission denied
                    console.error('permission denied: ' + res.status);
                    this.setState({ error401: true, error403: false, error500: false });
                }
                if(res.status === 403){
                    //permission denied
                    console.error('permission denied: ' + res.status);
                    this.setState({ error403: true, error401: false, error500: false });
                }
                if(res.status === 500){
                    //server error
                    console.error('server error: ' + res.status);
                    this.setState({ error401: false, error403: false, error500: true });
                }
            }
        );
    }

    handleLogOut = (event) => {
        if (event.type === 'click') {
            if (localStorage.getItem('Authorization') !== '' || localStorage.getItem('Authorization') != null
                || localStorage.getItem('Authorization') !== undefined) {
                localStorage.clear();
                history.push("/employeesLogin");
            }
        }
    }
    // The dropdown menu.
    // This is the function that says what to do once search term has been inputed. Example, just set the dropdown menu. What kind of action it is?
    handleSearchActionClick(action) { // Sets search bar to empty once the user's has inputed.
        this.setState({ searched: false });
        document.getElementById('form-control-input').value = '';
        this.setState({ searchAction: action });
    }

    // This is the function that says what to do once search term has been inputted.
    handleSearchSubmit(search) {
        let action = this.state.searchAction;
        console.log('handleSearchSubmit event: ' + action);

        this.callAPI(action.endpoint(search));
    }


    // This is the function that says what to do once an event happens (Enter key has been pressed).
    handleKeyDownEvent(event) { // 'keypress' event misbehaves on mobile so we track 'Enter' key via 'keydown' event
        // event.preventDefault();
        console.log('handleKeyDownEvent event: ' + event.target.value);

        this.changeHandler(event);
        if (event.key === 'Enter' || event.type === 'click') {
            console.log('event type >' + event.type)
            this.setState({ searched: true });
            event.preventDefault();
            event.stopPropagation();
            console.log('handleSearchSubmit: ' + event.target.value)

            this.handleSearchSubmit(event.target.value);
        }
    }
    changeHandler(event) {
        // console.log('changeHandler event: ' + event.text);
        if (event.target.value === '') {
            console.log('value was empty now handleSearchSubmit: ' + event.target.value)
            event.target.value = document.getElementById('form-control-input').value
        }
        console.log('CHANGEHANDLER event: ' + event.target.name + ":" + event.target.value);
        event.target.value = document.getElementById('form-control-input').value
        this.setState({
            [event.target.name]: event.target.value
            // permitNumber: this.state.permitNumber,
            // name: this.state.name,
            // regNumber: this.state.regNumber,
            // actions: this.state.actions,
            // searchForm: this.state.searchForm,
            // employees: this.state.employees,
            // searchAction: this.state.searchAction,
        });

        console.log(this.state);
        // event.preventDefault();
        // event.stopPropagation();
        // this.handleSearchSubmit(event.target.value);
    }

    submissionHandler(event) {
        // console.log('submissionHandler event target value: ' + this.state.regNumber);
        event.preventDefault();

        this.setState({
            name: this.state.name,
            permitNumber: this.state.permitNumber,
            regNumber: this.state.regNumber,
            actions: this.state.actions,
            searchForm: this.state.regNumber,
            employees: this.state.employees,
            searchAction: this.state.searchAction,
            searched: true
        });

        event.stopPropagation();
        this.handleSearchSubmit(event.target.value);
    }

    // These are variables (using the let varible type) which are also Objects too?
    render() {
        let employees = this.state.employees;
        let searchAction = this.state.searchAction;
        let searched = this.state.searched;
        let error401 = this.state.error401;
        let error500 = this.state.error500;
        let error403 = this.state.error403;

        // generating the HTML:
        return (
            <div className="container ">
                <div className="row d-flex justify-content-center text-center">
                    <div className="col"></div>
                    <div className="col-10">
                        <h1><a href="/">Employee Vehicle Database</a></h1>
                        <p className="introduction">Enter your search term below to track down the employee you need</p>
                    </div>
                    <div className="col"></div>
                </div>
                <div className="row">
                    <div className="col">
                        {/* The: SearchBar component shows whats been inputed into the search bar */}
                        {/* This is the SearchBar component */} </div>
                </div>

                <div className="row d-flex justify-content-center">

                    {/*  Router component */}
                    <Router>
                        <div>
                            <ul className="nav row d-flex justify-content-center text-center">
                                <li className="nav-item">
                                    <a className="nav-link"
                                        href={"/employeesLogin/"}>Login</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="/employeesForm/">Register An Employee</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="/Search/">Search</a>
                                </li>
                                <li>
                                    <a className="nav-link" 
                                    onClick={this.handleLogOut.bind(this)}
                                        href={"/signOut/"}>Sign Out</a>
                                </li>

                            </ul>

                            <hr /> {/*
                                    A <Switch> looks through all its children <Route>
                                    elements and renders the first one whose path
                                    matches the current URL. Use a <Switch> any time
                                    you have multiple routes, but you want only one
                                    of them to render at a time
                                    */}
                            <Switch>
                                <Route exact path="/Search">
                                    <Form>
                                        <SearchBar className="col-10"
                                            // ref="searchForm"
                                            form="searchForm"
                                            name="searchForm"
                                            id='form-control-input'
                                            searchAction={searchAction}
                                            actions={actions}
                                            // value={this.state.searchForm}
                                            onChange={e => this.changeHandler(e)}
                                            // There has been a click event on the search and we want to bind that click event to the search bar?
                                            // We are binding the: SearchActionClick to the: handleSearchActionClick as a prop.
                                            handleSearchActionClick={
                                                this.handleSearchActionClick.bind(this)
                                            }
                                            // We are binding the: handleKeyDownEvent (right) as a prop and then calling it: handleKeyDownEvent (left).
                                            // This lets us use the: handleKeyDownEvent as a function in our SearchBar component.
                                            handleKeyDownEvent={
                                                this.handleKeyDownEvent.bind(this)
                                            }

                                        /> 
                            {(error401 || error403) && (
                            <div className="alert alert-danger"><span aria-label='permission denied' role="img">🚫</span> Permission Denied! You need to login to perform this action!</div>
                                 )}
                            {error500 && (
                            <div className="alert alert-danger"><span aria-label='shrug' role="img">🤷</span> Oops! Something went wrong </div>
                                 )}
                                        <button form='searchForm' type="submit"
                                            className="btn btn-outline-primary mb-3"
                                            onClick={e => { this.submissionHandler(e) }}
                                        >Show All</button>
                                    </Form>
                                    <Employees className="col-10"
                                        employees={employees}
                                        searched={searched} />
                                </Route>
                               
                                <Route path="/EmployeesForm/:employeeId?">
                                    {/* This displays the EmployeeForm Component */}
                                    <EmployeesForm />
                                </Route>
                                <Route path='/'>
                                    <Login />
                                </Route>

                            </Switch>
                        </div>
                    </Router>

                </div>

            </div>
        );
    }
}

export default App;

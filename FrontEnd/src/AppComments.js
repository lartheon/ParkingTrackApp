import React from 'react';
import Employees from './components/Employees';
import SearchBar from './components/SearchBar';
import 'bootstrap/dist/css/bootstrap.min.css';
import './app.css';

// Is this an Object or a variable or both?
// This endpoint (uri) is pointing to the server(backend) and the port for web services (http) is generally 8080 (alternative to port 80):
// This uri is the endpoint:
const api_endpoint = "http://localhost:8080/api/employees";

// This is a function named: makeLike that takes a parameter of search.
function makeLike (search) {
  // (User input with space) substitute spaces with %'s for the like.
  // // character encoding for % on the uri: https://www.w3schools.com/tags/ref_urlencode.asp
  return search.replace(/ /g, '%25');
}
// Refers to the dropdown on the search bar to determine which endpoint query is used.
// This block of code is an object.
const actions = { 
  permitNumber: { // attribue & value which is an object
    displayName: "Permit Number", // attribue & value which is an object
    // query starts after the question mark.
    // Why 10? This sets the Permit number search: parsing base 10.
    endpoint: value => "searchByPermit?number=" + parseInt(value, 10), // A method
  },
  regNumber: {
    displayName: "Reg Number",
    endpoint: value => "searchByReg?reg=" + makeLike(value),
  },
  name: {
    displayName: "Name",
    endpoint: value => "searchByName?name=" + makeLike(value),
  }
};

// The constructor takes in props. The Super takes in props but in a different way.
// In JavaScript, super refers to the parent class constructor, (in our example, it points to the React.Component implementation).
class App extends React.Component {
  constructor(props) {
    super(props);
    // Below is the state of the App (parent) component.
    this.state = {
      // An array of values from the employees table.
      employees: [],
      // What is this?
      searchAction: actions.regNumber,
      searched: false,
    };
  }
  // res stands for Response.
  // Creating full uri (uniform Resource Identifier) path.
  // Calling the endpoints(API) in the server side (grabing the endpoints from the backend).
  // The endpoints (above) will come after the forward-slash (below).
  callAPI(endpoint) {
    console.log(api_endpoint + "/" + endpoint);
    fetch(api_endpoint + "/" + endpoint)
    // returns a promise. Method call / method chaining.
    // Parsing: taking a document (text) and turning from a string to an object so that it can be passed to the server side.
      .then(res => res.text())
      .then(res => this.setState({ employees: JSON.parse(res) }));
  }
  // The dropdown menu.
  // This is the function that says what to do once search term has been inputed. Example, just set the dropdown menu. What kind of action it is?
  handleSearchActionClick(action) {
    // Sets search bar to empty once the user's has inputed.
    document.getElementById('form-control-input').value = '';
    // Set the state action to current action so the App knows what action has been selected.
    this.setState({ searchAction: action });
  }

  // This is the function that says what to do once search term has been inputted.
  handleSearchSubmit(search) {
    // Sets action to equal to the selected search option in the dropdown menu.
    let action = this.state.searchAction;
    this.callAPI(action.endpoint(search));
  }

  // This is the function that says what to do once an event happens (Enter key has been pressed).
  handleKeyDownEvent(event) {
    // 'keypress' event misbehaves on mobile so we track 'Enter' key via 'keydown' event
    if (event.key === 'Enter') {
      // when user press the Enter-key then: setState will be set to: true.
      this.setState({ searched: true });
      // A method that stops the default action of an element form happening. In this case is to stop the page refreshing.
      event.preventDefault();
      // Prevents the event from bubbling up the DOM as when an event is called on an element, that event bubles up the DOM and gets called on all of the elements parents.
      event.stopPropagation();
      // When user has input their search term then: handleSearchSubmit will be triggered ?
      // event: clicking | target: the search bar calls this due to an action | value: is whatever the value the user inputs.
      this.handleSearchSubmit(event.target.value); 
    }
  }
// These are variables (using the let varible type) which are also Objects too?
  // Creating the Objects: employees, searchAction and searched with states?
  render() {
    // We are setting the 
    let employees = this.state.employees;
    let searchAction = this.state.searchAction;
    let searched = this.state.searched;
     // generating the HTML:
    return (
      <div className="container">
        <div className="row">
          <div className="col"></div>
          <div className="col-10">
            <h1>Employee Vehicle Database</h1>
            <p className="introduction">Enter your search term below to track down the employee you need</p>
          </div>
          <div className="col"></div>
        </div>
        <div className="row">
          <div className="col">
            {/* The: SearchBar component shows whats been inputed into the search bar */}
            {/* This is the SearchBar component */}
          <SearchBar
              className="col-10"
              searchAction={searchAction}
              actions={actions}
              // There has been a click event on the search and we want to bind that click event to the search bar?
              // We are binding the: SearchActionClick to the: handleSearchActionClick as a prop.
              handleSearchActionClick={this.handleSearchActionClick.bind(this)}
              // We are binding the: handleKeyDownEvent (right) as a prop and then calling it: handleKeyDownEvent (left).
              // This lets us use the: handleKeyDownEvent as a function in our SearchBar component. 
              handleKeyDownEvent={this.handleKeyDownEvent.bind(this)}/>
          </div>        
        </div>
        <div className="row">
          <div className="col"></div>
          {/* The: Employees component returns the values from that table */}
            <Employees
              className="col-10"
              employees={employees}
              searched={searched}/>
          <div className="col"></div>
        </div>
      </div>
    );
  }
}

export default App;

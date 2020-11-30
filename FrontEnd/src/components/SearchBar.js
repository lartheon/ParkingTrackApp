import React from 'react';
import InputGroup from 'react-bootstrap/InputGroup';
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import FormControl from 'react-bootstrap/FormControl';
import { Form, Button } from 'react-bootstrap';


class SearchBar extends React.Component{
  constructor(props){
    super(props);
    this.state = {
      actions: {},
      searchAction: "",
    }
  }

  // handleSubmit(event){
  //   event.preventDefault();
  //   this.setState({
  //   actions:this.props.actions,
  //   searchAction:this.props.searchAction,})
  //   event.stopPropagation();
  // }
  // handleChange(event) {
  //   this.setState({
  //     [event.target.name]: event.target.value
  //   });
  //   console.log("changeHandler: "+this.state);
  // }

  render(){

    return (
      <div className={this.className}>
      <InputGroup className="mb-3">
        <DropdownButton
        // class="dropdown-toggle" 
        data-toggle="dropdown"
        onChange={(e)=> this.props.handleChange(e)}
          onSelect={function (evt) { console.log(evt); }}
          as={InputGroup.Prepend}
          variant="outline-primary"
          title={this.props.searchAction.displayName}
          id="input-group-dropdown-1">
          <Dropdown.Item
            className="dropdown-item"
            eventKey={this.props.actions.regNumber.displayName}
            onClick={() => this.props.handleSearchActionClick(this.props.actions.regNumber)}>
            {this.props.actions.regNumber.displayName}</Dropdown.Item>
          <Dropdown.Item
            className="dropdown-item"
            eventKey={this.props.actions.permitNumber.displayName}
            onClick={() => this.props.handleSearchActionClick(this.props.actions.permitNumber)}>
            {this.props.actions.permitNumber.displayName}</Dropdown.Item>
          <Dropdown.Item
            className="dropdown-item"
            eventKey={this.props.actions.name.displayName}
            onClick={() => this.props.handleSearchActionClick(this.props.actions.name)}>
            {this.props.actions.name.displayName}</Dropdown.Item>
        </DropdownButton>
        
          
        <input
          className="ml-2"
          onChange={this.props.changeHandler}
          onSubmit={e => this.props.submissionHandler(e)}
          id="form-control-input"
          aria-describedby="basic-addon1"
          placeholder="Enter Search"
          onKeyDown={e => this.props.handleKeyDownEvent(e)} 
          />
           <button type="submit" 
          //  name="sbSubmitBtn"
           className="btn btn-primary ml-2"
          form='form-control-input'
             onClick={e => { this.props.handleKeyDownEvent(e) }}
            >Submit</button>
      </InputGroup>
    </div>
    );
  }
}



/*
// function handleSubmit(event){
//   event.preventDefault();
//   actions:state.actions,
//   searchAction:state.searchAction,
// }
function handleChange(event){
  this.setState({
      [event.target.name]: event.target.value
  });
}

function SearchBar(props) {
  let actions = props.actions;
  let searchAction = props.searchAction;
  // let searchTerm = props.searchTerm;

  return (
    <div className={props.className}>
      <InputGroup className="mb-3">
        <DropdownButton
        onChange={(e)=> handleChange(e)}
          onSelect={function (evt) { console.log(evt); }}
          as={InputGroup.Prepend}
          variant="outline-secondary"
          title={searchAction.displayName}
          id="input-group-dropdown-1">
          <Dropdown.Item
            // href="#"
            eventKey={actions.regNumber.displayName}
            onClick={() => props.handleSearchActionClick(actions.regNumber)}>
            {actions.regNumber.displayName}</Dropdown.Item>
          <Dropdown.Item
            // href="#"
            eventKey={actions.permitNumber.displayName}
            onClick={() => props.handleSearchActionClick(actions.permitNumber)}>
            {actions.permitNumber.displayName}</Dropdown.Item>
          <Dropdown.Item
            //  href="#"
            eventKey={actions.name.displayName}
            onClick={() => props.handleSearchActionClick(actions.name)}>
            {actions.name.displayName}</Dropdown.Item>
        </DropdownButton>
        
          
        <FormControl
          onChange={(e)=> handleChange(e)}
          onSubmit={e => props.handleKeyDownEvent(e)}
          id="form-control-input"
          aria-describedby="basic-addon1"
          placeholder="Enter Search"
          onKeyDown={e => props.handleKeyDownEvent(e)} 
          />
           <button type="submit" 
          //  class="btn btn-primary"
          form='form-control-input'
             onClick={e => { props.handleKeyDownEvent(e) }}
            >Submit</button>
      </InputGroup>
    </div>


  );
}*/

export default SearchBar;
import React from 'react';
import InputGroup from 'react-bootstrap/InputGroup';
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import FormControl from 'react-bootstrap/FormControl';

function SearchBar(props) {
  let actions = props.actions;
  let searchAction = props.searchAction;
  return (
    <div className={props.className}>
      <InputGroup className="mb-3">
        <DropdownButton
          as={InputGroup.Prepend}
          variant="outline-secondary"
          title={searchAction.displayName}
          id="input-group-dropdown-1">
          <Dropdown.Item
            href="#"
            onClick={() => props.handleSearchActionClick(actions.regNumber)}>
            {actions.regNumber.displayName}</Dropdown.Item>
          <Dropdown.Item href="#"
            onClick={() => props.handleSearchActionClick(actions.permitNumber)}>
            {actions.permitNumber.displayName}</Dropdown.Item>
          <Dropdown.Item href="#"
            onClick={() => props.handleSearchActionClick(actions.name)}>
            {actions.name.displayName}</Dropdown.Item>
        </DropdownButton>
        <FormControl
          id="form-control-input"
          aria-describedby="basic-addon1"
          placeholder="Enter Search"
          onKeyDown={e => props.handleKeyDownEvent(e)} />
          {/* <button type="submit" class="btn btn-primary mb-2">Submit</button> */}
          {/*  <input type="text" value={this.state.value} onChange={this.handleChange} /> */}
           <input type="submit" class="btn btn-primary mb-2" value="Submit" />
          
      </InputGroup>
    </div>
  );
}

export default SearchBar;
import React from 'react';
import InputGroup from 'react-bootstrap/InputGroup';
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import FormControl from 'react-bootstrap/FormControl';
import { Form, Button } from 'react-bootstrap';

// function handleSubmit(event){
//   event.preventDefault();
//   actions:state.actions,
//   searchAction:state.searchAction,
// }

function SearchBar(props) {
  let actions = props.actions;
  let searchAction = props.searchAction;
  // let searchTerm = props.searchTerm;

  return (
//     <div className={props.className}>
//     <Form >
//       <Form.Group controlId="formDropDown">
//         <DropdownButton
//         onSelect={function (evt) { console.log(evt) }}
//           as={InputGroup.Prepend}
//           variant="outline-secondary"
//           title={searchAction.displayName}
//           id="input-group-dropdown-1">
//           <Dropdown.Item
//             // href="#"
//             eventKey={actions.regNumber.displayName}
//             onClick={() => props.handleSearchActionClick(actions.regNumber)}>
//             {actions.regNumber.displayName}</Dropdown.Item>
//           <Dropdown.Item
//             // href="#"
//             eventKey={actions.permitNumber.displayName}
//             onClick={() => props.handleSearchActionClick(actions.permitNumber)}>
//             {actions.permitNumber.displayName}</Dropdown.Item>
//           <Dropdown.Item
//             //  href="#"
//             eventKey={actions.name.displayName}
//             onClick={() => props.handleSearchActionClick(actions.name)}>
//             {actions.name.displayName}</Dropdown.Item>
//         </DropdownButton>
//       </Form.Group>
//       <Form.Group >
//         <Form.Control type="text"
//         onSubmit={e => props.handleKeyDownEvent(e)}
//         onKeyDown={e => props.handleKeyDownEvent(e)} 
//           id="form-control-input"
//           aria-describedby="basic-addon1"
//           placeholder="Enter Search" />
//       </Form.Group>
//       <Button variant="primary" type="submit">Submit</Button>
//     </Form>
// </div>
/////////////////////
    <div className={props.className}>
      <InputGroup className="mb-3">
        <DropdownButton
          onSelect={function (evt) { console.log(evt);  }}
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
          onSubmit={e => props.handleKeyDownEvent(e)}
          id="form-control-input"
          aria-describedby="basic-addon1"
          placeholder="Enter Search"
          onKeyDown={e => props.handleKeyDownEvent(e)} 
          />
          {/* <button form='form-control-input' type="submit" 
          onClick={e => props.handleKeyDownEvent(e)}
          >SubmitFromSB</button> */}
        {/* <input type="submit" className="btn btn-primary mb-2" value="Submit" /> */}
        {/* <button type="submit" class="btn btn-primary mb-2">Submit</button> */}
        {/*  <input type="text" value={this.state.value} onChange={this.handleChange} /> */}
        {/* <button className="btn btn-primary mb-2"
        form="form-control-input"
          onClick={e => props.handleKeyDownEvent(e)}
        >Submit</button> */}
       {/* <form
        onSubmit={this.props.handleSubmit}
        > */}
        {/* <input className="btn btn-primary mb-2"
        key = "Enter"
          onClick={e => {
            e.key = 'Enter'
            props.handleKeyDownEvent(e.target.value)}}
          type="submit" value="Submisst" /> */}
          {/* </form> */}
      </InputGroup>
    </div>


  );
}

export default SearchBar;
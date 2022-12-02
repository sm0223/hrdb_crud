// @flow
import React, {useState} from "react";
import Select from 'react-select';
import employeeService from "../services/employee"

import {ConstraintViolation} from "./constraintViolation";
import Example from "./modal";;
const Empform = (props)=> {

  const [selectedOption, setSelectedOption] = useState(1);
  const [dataUri, setDataUri] = useState('')
  const [spinner, setSpinner] = useState(false);

  const [message, setMessage] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [happy, setHappy] = useState("text-danger");

  const fileToDataUri = (file) => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.onload = (event) => {
      resolve(event.target.result)
    };
    reader.readAsDataURL(file);
  })

  const onChangeImage = (file) => {
    if(!file) {
      setDataUri('');
      return;
    }
    fileToDataUri(file)
        .then(dataUri => {
          setDataUri(dataUri);
        })

  }
  //modal handling
  const handleClose = () => {
    console.log("ads")
    setShowModal(false);
  }

  const departmentList = props.departments;
  if(departmentList == null || departmentList.data ==null) return (
      <div className="container" style={{padding:100}}>
        <div className="text-center">
          <div className="spinner-border text-primary text-center" style={{height:200, width:200}}> </div>
        </div>
      </div>
  )   //Returning null ig department List is null


  //Creating Department List for easy
  let options = [];
  departmentList.data.map(dept => {
    options.push({label:dept.dname, value :dept.dept_id});
  });


  const saveEmployee= (event) => {
    event.preventDefault()
    setSpinner(true);

    const dept = departmentList.data.filter(dept => dept.dept_id === selectedOption.value);

    const newEmployee = {
      "firstname": event.target[0].value,
      "lastname": event.target[1].value,
      "email": event.target[3].value,
      "title": event.target[4].value,
      "photographPath": event.target[3].value+".jpg",
      "empDepartment": dept[0],
      "imageData" : dataUri
    }
    console.log("dataURI", dataUri);
    employeeService.addEmployee(newEmployee).then(res =>{
      setSpinner(false);
      if (res.status == 200) {
        setMessage('Successful! : ' +"Employee Added!");
        setHappy("text-success")
        setShowModal(true)
        event.target[0].value = ""
        event.target[1].value = ""
        event.target[2].value = ""
        event.target[3].value = ""
        event.target[4].value = ""
      } else if (res.status == 208) {
        if (res.data.resCode == 208) {
          console.log("department Full");
          setMessage('Unsuccessful 422 : ' + "Department has reached capacity!");
          setHappy("text-danger")
          setShowModal(true)

        }
      }
      else if (res.status == 422) {
        if (res.data.data.includes(newEmployee.email)) {
          setMessage('Unsuccessful 422 : ' + "Email is already registered with an existing Employee!");
          setHappy("text-danger")
          setShowModal(true)
        }
      }
    }
    );
  }
  return (
      <div className="container">
        { showModal && <Example message = {message} showModal = {showModal} handleClose={handleClose} happy = {happy}/>}
      <h3 className="my-5" align="left">Register Employee</h3>
      <form onSubmit={saveEmployee}>
        <div className="mb-3">
          <label htmlFor="firstName" className="form-label">First Name</label>
          <input type="text" className="form-control" id="firstName" placeholder="Enter First Name" required/>
          <div className="invalid-feedback">
          </div>
        </div>
        <div className="mb-3">
          <label htmlFor="lastName" className="form-label">Last Name</label>
          <input type="text" className="form-control" id="lastName" placeholder="Enter Last Name"/>
        </div>
        <div className="mb-3">
          <label htmlFor="department" className="form-label">Select Department</label >
          <Select onChange={setSelectedOption} options = {options}/>
        </div>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email address</label>
          <input type="email" className="form-control" id="email" placeholder="Enter Email" required/>
        </div>
        <div className="mb-3">
          <label htmlFor="title" className="form-label">Title</label>
          <input type="text" className="form-control" id="Title" placeholder="Last Name"/>
        </div>
        <div className="mb-3">
          <div className="row align-items-center">
            <div className="col-md-8">
            <label htmlFor="uploadPhoto" className="form-label">Upload Photograph</label>
            <input className="form-control" type="file" id="formFile" onChange={(event) => onChangeImage(event.target.files[0] || null)}/>
            </div>
            <div className="col-md-4">
              <img align="right" className="photo rounded float-right" src={dataUri} />
            </div>
          </div>
        </div>
        <button type="submit" className="btn btn-primary" style={{verticalAlign:"middle"}}>
          { spinner!==true &&<>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Submit&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</> }
            { spinner===true && <div className="d-flex" >
                                  <>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Submit &nbsp;</>

                                  <span className="spinner-border spinner-border-sm text-primary" role="status">
                                  </span>
                                </div>
            }
        </button>
      </form>
      </div>
  )
}
export default Empform;
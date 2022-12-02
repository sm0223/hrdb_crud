import React, {useState} from "react";
import Select from 'react-select';
import employeeService from "../services/employee"

import {ConstraintViolation} from "./constraintViolation";
const ViewEmp = ({employee, handleAction})=> {

  console.log("d",employee);


  //Returning null ig department List is null
  if(employee == null) return (
      <div className="container" style={{padding:100}}>
        <div className="text-center">
          <div className="spinner-border text-primary text-center" style={{height:200, width:200}}> </div>
        </div>
      </div>
  )
  console.log("d",employee.data);
  return (
      <div className="container">
        <h3 align="left">Employee Details</h3>
        <form>
          <div className="mb-3">
            <div className="row align-items-center">
              <div className="col-md-8">
                <div className="mb-3">
                  <label htmlFor="firstName" className="form-label">First Name</label>
                  <input type="text" className="form-control" id="firstName" defaultValue={employee.firstname}  disabled/>
                </div>
                <div className="mb-3">
                  <label htmlFor="lastName" className="form-label">Last Name</label>
                  <input type="text" className="form-control" id="lastName" defaultValue={employee.lastname} disabled/>
                </div>
              </div>
              <div className="col-md-4">
                {
                  employee != null && employee.imageData!=null ?
                    <img align="right" className="photo border rounded float-right" src={employee.imageData}/> :
                    <img align="right" className="photo border rounded float-right" src={require("/home/shux/EsdProjects/frontend/src/assets/no.png")}/>
                }
              </div>
            </div>
          </div>
          <div className="mb-3">
            <label htmlFor="department" className="form-label">Department Name</label >
            <input type="text" className="form-control" id="lastName" defaultValue={employee.empDepartment!=null && employee.empDepartment.dname} disabled/>
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email address</label>
            <input type="text" className="form-control" id="email" defaultValue={employee.email} disabled/>
          </div>
          <div className="mb-3">
            <label htmlFor="title" className="form-label">Title</label>
            <input type="text" className="form-control" id="Title" defaultValue={employee.title} disabled/>
          </div>
          <button className="btn btn-warning" onClick={()=>handleAction(employee, "allEmp")}>Back</button>
        </form>
      </div>
  )
}
export default ViewEmp;
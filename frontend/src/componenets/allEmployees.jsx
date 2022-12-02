import * as React from 'react';
import {Spinner} from "react-bootstrap";

export function AllEmployees(props) {
  const employeeListResponse = props.employees;
  //Returning null if employee List is null
  if(employeeListResponse == null || employeeListResponse.data ==null) return (
      <div className="container" style={{padding:100}}>
        <div className="text-center">
          <div className="spinner-border text-primary text-center" style={{height:200, width:200}}> </div>
        </div>
      </div>
  )
  const employeeList = employeeListResponse.data;

  return (
      <div className="container">
        <h3 className="my-5">Employees</h3>
        <table className="table table-responsive shadow">
          <thead className="table table-dark shadow ">
          <tr>
            <th scope="col"><p className="m-2 text-center">#</p></th>
            <th scope="col"><p className="my-2 text-center">Employee Id</p></th>
            <th scope="col"><p className="my-2 text-center">First Name</p></th>
            <th scope="col"><p className="my-2 text-center">Last Name</p></th>
            <th scope="col"><p className="my-2 text-center">Department</p></th>
            <th scope="col"><p className="my-2 text-center">Email</p></th>
            <th scope="col"><p className="my-2 text-center">Title</p></th>
            <th scope="col"><p className="my-2 text-center">Image Path</p></th>
            <th scope="col"><p className="my-2 text-center text-center">Actions</p></th>
          </tr>
          </thead>
          <tbody>
          {
              employeeList.map(employeedata =>
                <tr className="m-2" key={employeedata.employeeId}>
                  <th> {employeedata.id}</th>
                  <td> {employeedata.employeeId}</td>
                  <td> {employeedata.firstname}</td>
                  <td> {employeedata.lastname}</td>
                  <td> {employeedata.empDepartment != null && employeedata.empDepartment.dname}</td>
                  <td> {employeedata.email}</td>
                  <td> {employeedata.title}</td>
                  <td> {employeedata.photographPath}</td>
                  <td >
                    <p><button className="btn btn-success m-2"
                            id={"view"+employeedata.id}
                            key={"view"+employeedata.id}
                            onClick={()=>props.handleAction(employeedata,"view")}>
                      &nbsp;&nbsp;View&nbsp;&nbsp;
                    </button>

                    <button className="btn btn-warning m-2"
                            id={"update"+employeedata.id}
                            key={"update"+employeedata.id}
                            onClick={()=>props.handleAction(employeedata,"update")}
                    >
                      Update
                    </button>

                    <button className="btn btn-danger m-2"
                            id={"delete"+employeedata.id}
                            key={"delete"+employeedata.id}
                            onClick={()=>props.handleDelete(employeedata)}
                            >
                      Delete
                    </button>
                    </p>
                  </td>
                </tr>
              )
          }
          </tbody>
        </table>
      </div>
  );
};
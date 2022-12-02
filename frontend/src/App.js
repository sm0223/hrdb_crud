import React, {useEffect, useState} from "react";
import axios from "axios";
import "./App.css";



import BasicNavbar from "./componenets/nav";
import Empform from "./componenets/empform";


import {AllEmployees} from "./componenets/allEmployees";
import Updateform from "./componenets/updateForm";
import employeeService from "./services/employee";

import {Dashbord} from "./componenets/dashbord";
import departmentService from "./services/department";
import ViewEmp from "./componenets/viewEmp";
import Example from "./componenets/modal";


function App() {
  const [showComponent, setShowComponent] = useState({
    dashboard :true,
    empForm : false,
    allEmp : false,
    updateEmp :false,
    viewEmp : false,
    showModal : false
  });
  const [departmentList, setDepartmentList] = useState(null);
  const [employeeList, setEmployeeList] = useState(null);
  const [selectedEmployee, setSelectedEmployee] = useState(null);
  const [message, setMessage] = useState(null);

  const getAllDepartmentsHook = () => {

        departmentService.getAllDepartments().then(response => {
          console.log(response.data)
          setDepartmentList(response.data)
        })
  }
  const getAllEmployeesHook = () => {
        employeeService.getAllEmployee().then(response => {
          setEmployeeList(response.data)
        })
  }
  const handleAction = (employee, action) => {
    setSelectedEmployee(employee);
    handleDashboard(action);
    getAllDepartmentsHook();
    getAllEmployeesHook();
  }
  const handleDelete = (employeedata) => {
    employeeService.deleteEmployee(employeedata);
    const emplList = employeeList.data.filter(emp => emp.employeeId !== employeedata.employeeId);
    setEmployeeList({
      data: emplList
    });
  }
  const handleDashboard = (action)=> {
    console.log(action);
    if(action == 'add') {
      setShowComponent( {
        dashboard: false,
        empForm : true,
        allEmp : false,
        updateEmp :false,
        viewEmp : false,
        showModal : false
      })
    }
    else if(action == 'view') {
      setShowComponent( {
        dashboard: false,
        empForm : false,
        allEmp : false,
        updateEmp :false,
        viewEmp : true,
        showModal : false
      })
    }
    else if(action == 'allEmp') {
      setShowComponent( {
        dashboard: false,
        empForm : false,
        allEmp : true,
        updateEmp :false,
        viewEmp : false,
        showModal : false
      })
    }
    else if(action == 'dashboard') {
      setShowComponent( {
        dashboard: true,
        empForm : false,
        allEmp : false,
        updateEmp :false,
        viewEmp : false,
        showModal : false
      })
    }
    else if(action == 'update'){
      setShowComponent( {
        dashboard: false,
        empForm : false,
        allEmp : false,
        updateEmp :true,
        viewEmp : false,
        showModal : false,
      })
    }
  }
  useEffect(getAllDepartmentsHook,[]);
  useEffect(getAllEmployeesHook,[])
  useEffect(handleAction,[])

  return (
    <div className="App">
      <BasicNavbar
          handleDashboard={handleDashboard}
      />
      {showComponent.showModal === true && <Example message = {message} />}

      {showComponent.dashboard == true && <Dashbord
        handleDashboard={handleDashboard}
      />}

      {showComponent.empForm == true && <Empform
        departments = {departmentList}
      />}

      {showComponent.allEmp == true && <AllEmployees
        employees = {employeeList}
        handleAction = {handleAction}
        handleDelete ={handleDelete}
      />}
      {showComponent.updateEmp == true && <Updateform
          departments = {departmentList}
          employee = {selectedEmployee}
          handleAction={handleAction}

      />}

      {showComponent.viewEmp == true && <ViewEmp
          employee = {selectedEmployee}
          handleAction={handleAction}
      />}
    </div>
  );
}

export default App;

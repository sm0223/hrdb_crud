// @flow
import * as React from 'react';

export function Dashbord({handleDashboard}) {
  return (
      <div className="container">
        <div className="row m-5">
          <div className="col-md-6">
            <button className="card text-center" onClick={()=>handleDashboard('add')}>
              <img src={require("/home/shux/EsdProjects/hrms2/src/assets/add_employee.jpg")} className="card-img"/>
                <div className="card-body">
                  <h5 className="card-title">Register New Employee</h5>
                </div>
            </button>
          </div>
          <div className="col-md-6">
            <button className="card text-center" onClick={()=>handleDashboard('allEmp')}>
              <img src={require("/home/shux/EsdProjects/hrms2/src/assets/get_employee.png")} className="card-img"/>
                <div className="card-body">
                  <h5 className="text-center">View All Employee</h5>
                </div>
            </button>
          </div>
        </div>
      </div>
  );
};
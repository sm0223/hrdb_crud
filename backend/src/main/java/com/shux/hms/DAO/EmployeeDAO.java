package com.shux.hms.DAO;

import com.shux.hms.Bean.Department;
import com.shux.hms.Bean.Employee;

import java.util.List;

public interface EmployeeDAO {
    boolean addEmployee(Employee empObj);
    Employee getEmployeeByID (int empID);
    List<Employee> getEmployeeList();
    boolean deleteEmployee(int empID);
    boolean updateEmployee(Employee empObj);
    int getEmployeeListbyDept(Department dept);

    }

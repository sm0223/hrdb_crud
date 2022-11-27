package com.shux.hms.DAO;

import com.shux.hms.Bean.Department;

import java.util.List;

public interface DepartmentDAO {

    boolean addDepartment(Department deptObj);
    List<Department> getDepartmentList();
}

package com.shux.hms.Bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name= "departments")
public class Department {
    @Id
    @Column(name = "deparment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;

    @Column(name = "name", nullable = false, unique = true)
    private String dname;

    @Column(name = "capacity", nullable = false)
    private int capacity;


    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Employee> employeeList;
    public Department() {
    }
    @JsonIgnore
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Department(int departmentId, String dname, int capacity, List<Employee> employeeList) {
        this.departmentId = departmentId;
        this.dname = dname;
        this.capacity = capacity;
        this.employeeList = employeeList;
    }

    public int getDept_id() {
        return departmentId;
    }

    public void setDept_id(int dept_id) {
        this.departmentId = dept_id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(this);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

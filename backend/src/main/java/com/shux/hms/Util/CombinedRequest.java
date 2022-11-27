package com.shux.hms.Util;

import com.shux.hms.Bean.Employee;

public class CombinedRequest {
    private Employee Emp;
    private String image;

    public CombinedRequest() {
    }

    public CombinedRequest(Employee emp, String image) {
        Emp = emp;
        this.image = image;
    }

    public Employee getEmp() {
        return Emp;
    }

    public void setEmp(Employee emp) {
        Emp = emp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

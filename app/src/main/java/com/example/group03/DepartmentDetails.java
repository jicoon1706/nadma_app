package com.example.group03;

public class DepartmentDetails {

    String department, departmentId, address;

    public DepartmentDetails(){

    }

    public DepartmentDetails(String department, String departmentId, String address){
        this.department = department;
        this.departmentId = departmentId;
        this.address = address;

    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}

package com.example.group03;

public class selectCallDetails extends DepartmentDetails {
    private String department;
    private String departmentId;
    private String address;
    private String contactNumber; // For emergency calls

    // Default constructor (needed for Firebase)
    public selectCallDetails() {
    }

    // Parameterized constructor
    public selectCallDetails(String department, String departmentId, String address, String contactNumber) {
        this.department = department;
        this.departmentId = departmentId;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    // Getter and Setter methods
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

package com.example.group03;

public class ReportDetails {

    String date, state, district, area, issue, disaster, severity;

    public ReportDetails(){

    }

    public ReportDetails(String date, String state, String district, String area, String issue, String disaster, String severity){
        this.date = date;
        this.state = state;
        this.district = district;
        this.area = area;
        this.issue = issue;
        this.disaster = disaster;
        this.severity = severity;
    }

    public String getDate() {return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDisaster() {
        return disaster;
    }

    public void setDisaster(String disaster) {
        this.disaster = disaster;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}

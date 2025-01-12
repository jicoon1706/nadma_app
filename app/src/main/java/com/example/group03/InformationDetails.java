package com.example.group03;

public class InformationDetails {

    String date, title, details;
    public InformationDetails(){

    }

    public InformationDetails(String date, String title, String details){
        this.date = date;
        this.title = title;
        this.details = details;
    }

    public String getDate() {
        return date;}

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

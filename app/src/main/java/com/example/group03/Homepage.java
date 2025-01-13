package com.example.group03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity {

    // Declare views
    private SearchView searchView;
    private ImageView imageView;
    private TextView textSms, textCall, textMaps;
    private Button homeButton, callButton, addButton, infoButton, personButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure this matches your layout file name



    }


    private void navigateToHome() {
        // Logic for home navigation
    }

    private void makeCall() {
        // Logic for making a call
    }

    private void addReport() {
        // Logic for adding a report
    }

    private void showInfo() {
        // Logic for showing info
    }

    private void openProfile() {
        // Logic for opening profile
    }
}

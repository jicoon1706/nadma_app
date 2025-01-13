package com.example.group03;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



    }



    public void report_page(View view) {
        Intent intent = new Intent(this, Report.class); // Correct target activity
        startActivity(intent);
    }
    public void info_page(View view) {
        Intent intent = new Intent(this, addInfo.class); // Correct target activity
        startActivity(intent);
    }

    public void home_page(View view) {
        Intent intent = new Intent(this, addInfo.class); // Correct target activity
        startActivity(intent);
    }

    public void info_layout(View view) {
        Intent intent = new Intent(this, infoLayout.class); // Correct target activity
        startActivity(intent);
    }

    public void addDepartment_page(View view) {
        Intent intent = new Intent(this, addDepartment.class); // Correct target activity
        startActivity(intent);
    }

    public void editProfile_page(View view) {
        Intent intent = new Intent(this, addDepartment.class); // Correct target activity
        startActivity(intent);
    }

    public void profilePage_page(View view) {
        Intent intent = new Intent(this, ProfilePage.class); // Correct target activity
        startActivity(intent);
    }



    public void emergencyCall_page(View view) {
        Intent intent = new Intent(this, addDepartment.class); // Correct target activity
        startActivity(intent);
    }

    public void departmentControl_page(View view) {
        Intent intent = new Intent(this, addDepartment.class); // Correct target activity
        startActivity(intent);
    }


}
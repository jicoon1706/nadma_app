package com.example.group03;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Report extends AppCompatActivity implements SensorEventListener {

    private TextView xTextView, yTextView, zTextView;
    private SensorManager sensorManager;
    private Sensor acclerometerSensor;
    private boolean isAccelormeterSensorAvailable, itIsNotFirstTime = false;
    private float currentX, currentY, currentZ, lastX, lastY, lastZ;
    private float xDifference, yDifference, zDifference;
    private float shakeThreshold = 5f;
    private Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report);

        xTextView = findViewById(R.id.xTextView);
        yTextView = findViewById(R.id.yTextView);
        zTextView = findViewById(R.id.zTextView);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null)
        {
            acclerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isAccelormeterSensorAvailable = true;
        }else{
            xTextView.setText("Acclerometer sensor is not available");
            isAccelormeterSensorAvailable = false;
        }


        //Initialize RV
        RecyclerView rv = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        // Initialize Adapter
        List<ReportDetails> list = new ArrayList<>();
        ReportAdapter adapter = new ReportAdapter(Report.this, list);

        rv.setAdapter(adapter);

        //Fetch data from FB and store into list
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/");
        DatabaseReference dbRef = db.getReference("Report");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // Clear the list before adding new data
                for (DataSnapshot data : snapshot.getChildren()) {
                    // Ensure the required fields are available

                    ReportDetails reportDetails = new ReportDetails();
                    reportDetails.setDisaster(data.child("Disaster").getValue(String.class));
                    reportDetails.setArea(data.child("Area").getValue(String.class));
                    reportDetails.setSeverity(data.child("Severity Level").getValue(String.class));
                    reportDetails.setDistrict(data.child("District").getValue(String.class));
                    reportDetails.setIssue(data.child("Issue").getValue(String.class));
                    reportDetails.setState(data.child("State").getValue(String.class));
                    reportDetails.setDate(data.child("Date").getValue(String.class));

                    // Add the student to the list
                    list.add(reportDetails);

                }
                adapter.notifyDataSetChanged(); // Update the adapter
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Report.this, "Failed to fetch data from database", Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void report_page(View view) {
        Intent intent = new Intent(this, Report.class); // Correct target activity
        startActivity(intent);
    }

    public void call_page(View view) {
        Intent intent = new Intent(this, selectCall.class); // Correct target activity
        startActivity(intent);
    }

    public void add_report(View view) {
        Intent intent = new Intent(this, addReport.class); // Correct target activity
        startActivity(intent);
    }

    public void home_page(View view) {
        Intent intent = new Intent(this, Homepage.class); // Correct target activity
        startActivity(intent);
    }

    public void info_page(View view) {
        Intent intent = new Intent(this, addInfo.class); // Correct target activity
        startActivity(intent);
    }

    public void personal_page(View view) {
        Intent intent = new Intent(this, personalPage.class); // Correct target activity
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xTextView.setText(sensorEvent.values[0]+" m/s2");
        yTextView.setText(sensorEvent.values[1]+" m/s2");
        zTextView.setText(sensorEvent.values[2]+" m/s2");

        currentX = sensorEvent.values[0];
        currentY = sensorEvent.values[1];
        currentZ = sensorEvent.values[2];

        if(itIsNotFirstTime)
        {
            xDifference = Math.abs(lastX - currentX);
            yDifference = Math.abs(lastY - currentY);
            zDifference = Math.abs(lastZ - currentZ);

            if((xDifference > shakeThreshold && yDifference > shakeThreshold) ||
            (xDifference > shakeThreshold && zDifference > shakeThreshold) ||
                    (yDifference > shakeThreshold && zDifference > shakeThreshold))
            {
                vibrator.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));

            }
        }

        lastX = currentX;
        lastY = currentY;
        lastZ = currentZ;
        itIsNotFirstTime = true;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isAccelormeterSensorAvailable)
            sensorManager.registerListener(this, acclerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
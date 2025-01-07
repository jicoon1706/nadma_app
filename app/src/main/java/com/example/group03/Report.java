package com.example.group03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report);

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

    public void add_report(View view) {
        Intent intent = new Intent(this, addReport.class); // Correct target activity
        startActivity(intent);
    }
}
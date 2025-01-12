package com.example.group03;

import static com.example.group03.R.id.rv;

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
import java.util.Collections;
import java.util.List;

public class department extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_department);

        //Initialize RV
        RecyclerView rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        // Initialize Adapter
        List<DepartmentDetails> List = new ArrayList<>();
        java.util.List<DepartmentDetails> list = Collections.emptyList();
        DepartmentAdapter adapter = new DepartmentAdapter(department.this, list);

        rv.setAdapter(adapter);

        //Fetch data from FB and store into list
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/");
        DatabaseReference dbRef = db.getReference("DepartmentInfo");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List.clear(); // Clear the list before adding new data
                for (DataSnapshot data : snapshot.getChildren()) {
                    // Ensure the required fields are available

                    DepartmentDetails departmentDetails = new DepartmentDetails();
                    departmentDetails.setDepartment(data.child("Department").getValue(String.class));
                    departmentDetails.setDepartmentId(data.child("Department ID").getValue(String.class));
                    departmentDetails.setAddress(data.child("Address").getValue(String.class));


                    // Add the student to the list
                    DepartmentDetails DepartmentDetails = new DepartmentDetails();
                    List.add(DepartmentDetails);

                }
                adapter.notifyDataSetChanged(); // Update the adapter
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(department.this, "Failed to fetch data from database", Toast.LENGTH_SHORT).show();

            }
        });


    }
}


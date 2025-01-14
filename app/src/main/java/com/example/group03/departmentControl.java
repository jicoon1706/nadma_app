package com.example.group03;

import android.annotation.SuppressLint;
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

public class departmentControl extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_department_control);


        //initialize RV
        RecyclerView rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        //Initialize Adapter
        List<DepartmentDetails> list = new ArrayList<>();
        DepartmentAdapter adapter = new DepartmentAdapter(departmentControl.this, list); // Pass the list
        rv.setAdapter(adapter);


        FirebaseDatabase db = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/");
        DatabaseReference dbRef = db.getReference("DepartmentInfo");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // Avoid duplication
                for (DataSnapshot data : snapshot.getChildren()) {
                    DepartmentDetails departmentDetails = new DepartmentDetails();
                    departmentDetails.setDepartment((String) data.child("Department").getValue());
                    departmentDetails.setDepartmentId((String) data.child("Department ID").getValue());
                    departmentDetails.setAddress((String) data.child("Address").getValue());


                    list.add(departmentDetails);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(departmentControl.this, "Failed to fetch data from Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void department_1(View view) {
        Intent intent = new Intent(this, addDepartment.class); // Correct target activity
        startActivity(intent);
    }

    public void home_page(View view) {
        Intent intent = new Intent(this, Homepage.class); // Correct target activity
        startActivity(intent);
    }
}

package com.example.group03;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addDepartment extends AppCompatActivity {


    EditText etName, etId, etAddress;
    Button btnAdd1, btnView1;

    String department, Id, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);


        btnView1 = findViewById(R.id.btnView1);



//        btnView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //intent
//                Intent i = new Intent(addDepartment.this, addDepartment.class);
//                startActivity(i);
//
//            }
//        });

        btnAdd1 = findViewById(R.id.btnAdd1);

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //obtain the data from EditText
                etName = findViewById(R.id.etName);
                etId = findViewById(R.id.etId);
                etAddress = findViewById(R.id.etAddress);

                department = etName.getText().toString();
                address = etAddress.getText().toString();
                Id = etId.getText().toString();

//                String area = "Department";

                // checking field
                if (department.isEmpty() || Id.isEmpty() || address.isEmpty()){
                    Toast.makeText(addDepartment.this, "All field must be filled", Toast.LENGTH_SHORT).show();

                }else{
                    // insert data
                    //HashMap key , value
                    HashMap hmInfo = new HashMap();
                    hmInfo.put("Department",department);
                    hmInfo.put("Department ID",Id);
                    hmInfo.put("Address",address);


                    // insert data to firebase
                    FirebaseDatabase myDb = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/");
                    DatabaseReference dbRef = myDb.getReference("DepartmentInfo").child(department);
                    dbRef.setValue(hmInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(addDepartment.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                                // clear edit text
                                etName.getText().clear();
                                etAddress.getText().clear();
                                etId.getText().clear();

                            }else {
                                Toast.makeText(addDepartment.this, "Failed to insert data", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });

    }

    public void viewDepartment(View view) {
        Intent intent = new Intent(this, departmentControl.class); // Correct target activity
        startActivity(intent);
    }
}
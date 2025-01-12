package com.example.group03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateDepartment extends AppCompatActivity {

    String a, b, c;
    EditText etName, etId, etAddres;
    Button btnSubmitD, btnSimpan, btnBackD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_department);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Receive data from intent
        a = getIntent().getStringExtra("dataDepartment");
        b = getIntent().getStringExtra("dataDepartmentId");
        c = getIntent().getStringExtra("dataAddress");


        // Set text to the update form
        etName = findViewById(R.id.etName);
        etId = findViewById(R.id.etId);
        etAddres = findViewById(R.id.etAddress);

        etName.setText(a);
        etId.setText(b);
        etAddres.setText(c);


        //bnUTTON EVENT LISTENER
        btnSubmitD = findViewById(R.id.btnSubmitD);
        btnSubmitD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtain edited data from form
                String editedDepartment = etName.getText().toString();
                String editedDepartmentId = etId.getText().toString();
                String editedAddress = etAddres.getText().toString();


                //HasMap
                HashMap hm = new HashMap();
                hm.put("Department", editedDepartment);
                hm.put("Department ID", editedDepartmentId);
                hm.put("Address", editedAddress);


                // Update the edited data into FB
                DatabaseReference dbRef = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/").getReference("DepartmentInfo");
                dbRef.child(c).updateChildren(hm).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(UpdateDepartment.this, "Kemaskini Berjaya", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateDepartment.this, department.class));
                    }
                });




            }
        });

        btnBackD = findViewById(R.id.btnBackD);
        btnBackD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateDepartment.this, department.class));
            }
        });


    }
}
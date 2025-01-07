package com.example.group03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class addReport extends AppCompatActivity {

    Button btnSave, btnSubmit, btnKembali;
    EditText etDate, etState, etDistrict, etArea, etIssue, etDisaster, etSeverity;
    String date, state, district, area, issue, disaster, severity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_report);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtain the data from the EditText
                etDate = findViewById(R.id.dateField);
                etState = findViewById(R.id.stateField);
                etDistrict = findViewById(R.id.districtField);
                etArea = findViewById(R.id.areaField);
                etIssue = findViewById(R.id.issueField);
                etDisaster = findViewById(R.id.disasterField);
                etSeverity = findViewById(R.id.severityField);


                date = etDate.getText().toString();
                state = etState.getText().toString();
                district = etDistrict.getText().toString();
                area = etArea.getText().toString();
                issue = etIssue.getText().toString();
                disaster = etDisaster.getText().toString();
                severity = etSeverity.getText().toString();

                if(date.isEmpty() || state.isEmpty() || district.isEmpty() || area.isEmpty() || issue.isEmpty() || disaster.isEmpty() || severity.isEmpty() ){
                    Toast.makeText(addReport.this, "All field must be filled", Toast.LENGTH_SHORT).show();
                }else{
                    //HashMap key, value
                    HashMap hmInfo = new HashMap();
                    hmInfo.put("Date",date);
                    hmInfo.put("State",state);
                    hmInfo.put("District",district);
                    hmInfo.put("Area",area);
                    hmInfo.put("Issue",issue);
                    hmInfo.put("Disaster",disaster);
                    hmInfo.put("Severity Level",severity);

                    //Insert data to firebase
                    FirebaseDatabase myDb = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/");
                    DatabaseReference dbRef = myDb.getReference("Report").child(area);
                    dbRef.setValue(hmInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(addReport.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                                //clear edit Text
                                etDate.getText().clear();
                                etState.getText().clear();
                                etDistrict.getText().clear();
                                etArea.getText().clear();
                                etIssue.getText().clear();
                                etDisaster.getText().clear();
                                etSeverity.getText().clear();
                            }else{
                                Toast.makeText(addReport.this, "Failed to insert data",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }


            }
        });

        btnKembali = findViewById(R.id.btnBack);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent
                Intent i = new Intent(addReport.this, Report.class);
                startActivity(i);

            }
        });

    }


}
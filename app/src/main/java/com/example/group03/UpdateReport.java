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

public class UpdateReport extends AppCompatActivity {

    String a, b, c, d, e, f, g;
    EditText etDate, etState, etDistrict, etArea, etIssue, etDisaster, etSeverity;
    Button btnSubmit, btnSimpan, btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_report);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Receive data from intent
        a = getIntent().getStringExtra("dataDisaster");
        b = getIntent().getStringExtra("dataSeverity");
        c = getIntent().getStringExtra("dataArea");
        d = getIntent().getStringExtra("dataDate");
        e = getIntent().getStringExtra("dataState");
        f = getIntent().getStringExtra("dataDistrict");
        g = getIntent().getStringExtra("dataIssue");

        // Set text to the update form
        etDisaster = findViewById(R.id.disasterField);
        etSeverity = findViewById(R.id.severityField);
        etArea = findViewById(R.id.areaField);
        etDate = findViewById(R.id.dateField);
        etState = findViewById(R.id.stateField);
        etDistrict = findViewById(R.id.districtField);
        etIssue = findViewById(R.id.issueField);

        etDisaster.setText(a);
        etSeverity.setText(b);
        etArea.setText(c);
        etDate.setText(d);
        etState.setText(e);
        etDistrict.setText(f);
        etIssue.setText(g);

        //bnUTTON EVENT LISTENER
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtain edited data from form
                String editedDisaster = etDisaster.getText().toString();
                String editedSeverity = etSeverity.getText().toString();
                String editedArea = etArea.getText().toString();
                String editedDate = etDate.getText().toString();
                String editedState = etState.getText().toString();
                String editedDistrict = etDistrict.getText().toString();
                String editedIssue = etIssue.getText().toString();

                //HasMap
                HashMap hm = new HashMap();
                hm.put("Disaster", editedDisaster);
                hm.put("Severity Level", editedSeverity);
                hm.put("Area", editedArea);
                hm.put("Date", editedDate);
                hm.put("State", editedState);
                hm.put("District", editedDistrict);
                hm.put("Issue", editedIssue);

                // Update the edited data into FB
                DatabaseReference dbRef = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/").getReference("Report");
                dbRef.child(c).updateChildren(hm).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(UpdateReport.this, "Kemaskini Berjaya", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateReport.this, Report.class));
                    }
                });




            }
        });

        btnKembali = findViewById(R.id.btnBack);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateReport.this, Report.class));
            }
        });


    }
}
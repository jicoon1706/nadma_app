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

public class UpdateInfo extends AppCompatActivity {

    String a, b, c;

    private EditText etDate, etTitle, etDetails;

    Button btnSubmit, btnKembali, btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.update_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Receive data from intent
        a = getIntent().getStringExtra("dataDate");
        b = getIntent().getStringExtra("dataTitle");
        c = getIntent().getStringExtra("dataDetails");

        // Set text to the update form
        etDetails = findViewById(R.id.detailsField);
        etTitle = findViewById(R.id.titleField);
        etDate = findViewById(R.id.dateField);

        etDate.setText(a);
        etTitle.setText(b);
        etDate.setText(c);

        //bnUTTON EVENT LISTENER
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtain edited data from form
                String editedDate = etDate.getText().toString();
                String editedTitle = etTitle.getText().toString();
                String editedDetails = etDetails.getText().toString();

                //HasMap
                HashMap hm = new HashMap();
                hm.put("Date", editedDate);
                hm.put("Title", editedTitle);
                hm.put("Details", editedDetails);

                // Update the edited data into FB
                DatabaseReference dbRef = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/").getReference("Information");
                dbRef.child(c).updateChildren(hm).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(UpdateInfo.this, "Kemaskini Berjaya", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateInfo.this, Information.class));
                    }
                });




            }
        });

        btnKembali = findViewById(R.id.btnBack);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateInfo.this, Information.class));
            }
        });


    }
}



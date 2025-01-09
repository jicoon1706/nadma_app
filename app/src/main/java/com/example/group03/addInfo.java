package com.example.group03;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addInfo extends AppCompatActivity {

    Button btnSubmit , btnKembali;
    EditText etDate, etTitle, etDetails;
    String date, title, details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_info);


        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtain the data from the EditText
                etDate = findViewById(R.id.dateField);
                etTitle = findViewById(R.id.titleField);
                etDetails = findViewById(R.id.detailsField);


                date = etDate.getText().toString();
                title = etTitle.getText().toString();
                details = etDetails.getText().toString();

                if(date.isEmpty() || title.isEmpty() || details.isEmpty() ){
                    Toast.makeText(addInfo.this, "All field must be filled", Toast.LENGTH_SHORT).show();
                }else{
                    //HashMap key, value
                    HashMap hmInfo = new HashMap();
                    hmInfo.put("Date",date);
                    hmInfo.put("Title",title);
                    hmInfo.put("Details",details);


                    //Insert data to firebase
                    FirebaseDatabase myDb = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/");
                    DatabaseReference dbRef = myDb.getReference("Information").child(area);
                    dbRef.setValue(hmInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(addInfo.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                                //clear edit Text
                                etDate.getText().clear();
                                etTitle.getText().clear();
                               etDetails.getText().clear();

                            }else{
                                Toast.makeText(addInfo.this, "Failed to insert data",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }


            }
        });

        btnKembali = findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent
                Intent i = new Intent(addInfo.this, Report.class);
                startActivity(i);

            }
        });

    }



}


}

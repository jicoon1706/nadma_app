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

public class editProfile extends AppCompatActivity {

    EditText etUsername, etPassword, etEmail, etPhoneNumber, etUserAddress;
    Button btnSubmitP, btnBackP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize UI components
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etUserAddress = findViewById(R.id.etUserAddress);

        btnSubmitP = findViewById(R.id.btnSubmitP);
        btnBackP = findViewById(R.id.btnBackP);

        // Receive data from intent
        String username = getIntent().getStringExtra("dataUsername");
        String password = getIntent().getStringExtra("dataPassword");
        String email = getIntent().getStringExtra("dataEmail");
        String phoneNumber = getIntent().getStringExtra("dataPhoneNumber");
        String userAddress = getIntent().getStringExtra("dataUserAddress");

        // Populate fields with existing data
        etUsername.setText(username);
        etPassword.setText(password);
        etEmail.setText(email);
        etPhoneNumber.setText(phoneNumber);
        etUserAddress.setText(userAddress);

        // Button click listeners
        btnSubmitP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collect edited data
                String editedUsername = etUsername.getText().toString().trim();
                String editedPassword = etPassword.getText().toString().trim();
                String editedEmail = etEmail.getText().toString().trim();
                String editedPhoneNumber = etPhoneNumber.getText().toString().trim();
                String editedUserAddress = etUserAddress.getText().toString().trim();

                // Validate inputs
                if (editedUsername.isEmpty() || editedPassword.isEmpty() || editedEmail.isEmpty() ||
                        editedPhoneNumber.isEmpty() || editedUserAddress.isEmpty()) {
                    Toast.makeText(editProfile.this, "All fields are required.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Prepare data for update
                HashMap<String, Object> updatedData = new HashMap<>();
                updatedData.put("Username", editedUsername);
                updatedData.put("Password", editedPassword);
                updatedData.put("Email", editedEmail);
                updatedData.put("PhoneNumber", editedPhoneNumber);
                updatedData.put("UserAddress", editedUserAddress);

                // Update data in Firebase
                DatabaseReference dbRef = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/")
                        .getReference("Profile");

                dbRef.child(username).updateChildren(updatedData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(editProfile.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(editProfile.this, department.class));
                        finish();
                    }
                });
            }
        });

        btnBackP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(editProfile.this, department.class));
                finish();
            }
        });
    }

    public void profile (View view) {
        Intent intent = new Intent(this, ProfilePage.class); // Correct target activity
        startActivity(intent);
    }
}

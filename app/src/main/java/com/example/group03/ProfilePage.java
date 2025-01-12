package com.example.group03;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {

    private static final String TAG = "ProfilePage";

    // UI Elements
    private TextView tvProfileTitle, tvLabelUsername, tvLabelPassword, tvLabelEmail, tvLabelPhoneNumber, tvLabelAddress;
    private EditText etUsername, etPassword, etEmail, etPhoneNumber, etUserAddress;
    private ImageView btnEditProfile, profilePicture;
    private Button btnBackP;

    // Firebase
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Initialize Firebase reference
        databaseReference = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/").getReference("Profile");

        // Bind UI elements
        tvProfileTitle = findViewById(R.id.tvProfileTitle);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etUserAddress = findViewById(R.id.etUserAddress);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        profilePicture = findViewById(R.id.profilePicture);
        btnBackP = findViewById(R.id.btnBackP);

        // Load data from Firebase
        loadUserProfile();

        // Handle edit profile button click
        btnEditProfile.setOnClickListener(view -> saveUserProfile());
    }

    private void loadUserProfile() {
        databaseReference.child("Password").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String username = snapshot.child("username").getValue(String.class);
                    String password = snapshot.child("password").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String phoneNumber = snapshot.child("phoneNumber").getValue(String.class);
                    String address = snapshot.child("address").getValue(String.class);

                    etUsername.setText(username);
                    etPassword.setText(password);
                    etEmail.setText(email);
                    etPhoneNumber.setText(phoneNumber);
                    etUserAddress.setText(address);
                } else {
                    Toast.makeText(ProfilePage.this, "User data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to read user data: " + error.getMessage());
                Toast.makeText(ProfilePage.this, "Failed to load profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserProfile() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        String address = etUserAddress.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(username, password, email, phoneNumber, address);

        databaseReference.child("Password").setValue(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(ProfilePage.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProfilePage.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class User {
        public String username;
        public String password;
        public String email;
        public String phoneNumber;
        public String address;

        public void Profile() {
            // Default constructor required for Firebase
        }

        public User(String username, String password, String email, String phoneNumber, String address) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }
    }
}
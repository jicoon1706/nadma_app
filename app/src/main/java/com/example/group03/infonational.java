package com.example.group03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class infonational extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infonational);

        //Initialize RV
        RecyclerView rv = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        // Initialize Adapter
        List<InformationDetails> list = new ArrayList<>();
        InfoAdapter adapter = new InfoAdapter(infonational.this, list);

        rv.setAdapter(adapter);

        //Fetch data from FB and store into list
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/");
        DatabaseReference dbRef = db.getReference("Information");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // Clear the list before adding new data
                for (DataSnapshot data : snapshot.getChildren()) {
                    // Ensure the required fields are available

                    InformationDetails informationDetails = new InformationDetails();
                    informationDetails.setTitle(data.child("Title").getValue(String.class));
                    informationDetails.setDate(data.child("Date").getValue(String.class));
                    informationDetails.setDetails(data.child("Details").getValue(String.class));


                    // Add the student to the list
                    list.add(informationDetails);

                }
                adapter.notifyDataSetChanged(); // Update the adapter
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(infonational.this, "Failed to fetch data from database", Toast.LENGTH_SHORT).show();

            }
        });
}

    public void info_page(View view) {
        Intent intent = new Intent(this, addInfo.class); // Correct target activity
        startActivity(intent);
    }

    public void info_national(View view) {
        Intent intent = new Intent(this, infonational.class); // Correct target activity
        startActivity(intent);
    }
    public void info_news(View view) {
        Intent intent = new Intent(this, infonews.class); // Correct target activity
        startActivity(intent);
    }


}

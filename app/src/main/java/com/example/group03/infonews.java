package com.example.group03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class infonews extends AppCompatActivity {
    Context context;
    List<ReportDetails> list;
    EditText etDate, etTitle, etDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infonews);
    }
    public void info_page(View view) {
        Intent intent = new Intent(this, addInfo.class); // Correct target activity
        startActivity(intent);
    }

    public void report_page(View view) {
        Intent intent = new Intent(this, Report.class); // Correct target activity
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

    public void info_local(View view) {
        Intent intent = new Intent(this, infolocal.class); // Correct target activity
        startActivity(intent);
    }

    public void update_info(View view) {
        Intent intent = new Intent(this, UpdateInfo.class); // Correct target activity
        startActivity(intent);
    }

}



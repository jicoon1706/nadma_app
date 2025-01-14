package com.example.group03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class infonews extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infonews);
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



package com.example.friendflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        Intent intent = new Intent(MainActivity.this, CalendarActivity.class);

        button.setOnClickListener(v -> {
            startActivity(intent);
        });
    }
}
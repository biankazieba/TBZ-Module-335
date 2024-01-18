package com.example.friendflow;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        ImageButton backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {
            finish();
        });

        ImageButton button = findViewById(R.id.addButton);

        Intent intent = new Intent(CalendarActivity.this, AppointmentActivity.class);

        button.setOnClickListener(v -> {
            startActivity(intent);
        });
    }
}

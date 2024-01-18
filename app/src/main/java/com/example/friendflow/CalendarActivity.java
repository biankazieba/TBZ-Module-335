package com.example.friendflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        ImageButton backButton = findViewById(R.id.backButton);
        CalendarView calendarView = findViewById(R.id.calendarView);

        backButton.setOnClickListener(v -> finish());

        ImageButton button = findViewById(R.id.addButton);
        Intent intent = new Intent(CalendarActivity.this, AppointmentActivity.class);

        button.setOnClickListener(v -> startActivity(intent));

        updateDateTextView(Calendar.getInstance());

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            updateDateTextView(year, month, dayOfMonth);
        });
    }

    private void updateDateTextView(int year, int month, int dayOfMonth) {
        String selectedDate = String.format(Locale.getDefault(), "%02d.%02d.%04d", dayOfMonth, month + 1, year);
        TextView dateTextView = findViewById(R.id.date);
        dateTextView.setText(selectedDate);
    }

    private void updateDateTextView(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        updateDateTextView(year, month, dayOfMonth);
    }
}

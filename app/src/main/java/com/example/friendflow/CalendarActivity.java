package com.example.friendflow;

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

    private TextView dateTextView;
    private TextView weekdayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        ImageButton backButton = findViewById(R.id.backButton);
        dateTextView = findViewById(R.id.date);
        weekdayTextView = findViewById(R.id.weekday);
        CalendarView calendarView = findViewById(R.id.calendarView);

        backButton.setOnClickListener(v -> finish());

        ImageButton button = findViewById(R.id.addButton);

        Intent intent = new Intent(CalendarActivity.this, AppointmentActivity.class);

        button.setOnClickListener(v -> {
            startActivity(intent);
        });

        Calendar today = Calendar.getInstance();
        updateTextViews(today);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, dayOfMonth);
            updateTextViews(selectedDate);
        });
    }

    private void updateTextViews(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(calendar.getTime());
        dateTextView.setText(formattedDate);

        String[] weekdays = new String[]{"SONNTAG", "MONTAG", "DIENSTAG", "MITTWOCH", "DONNERSTAG", "FREITAG", "SAMSTAG"};
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String weekday = weekdays[dayOfWeek - 1];
        weekdayTextView.setText(weekday);
    }
}

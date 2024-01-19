package com.example.friendflow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.friendflow.R;
import com.example.friendflow.services.NotificationService;
import com.example.friendflow.calendar.CalendarHelper;
import com.example.friendflow.utils.NotificationUtils;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private TextView dateTextView;
    private TextView weekdayTextView;
    private TextView showTaskTitle;
    private NotificationService notificationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        notificationService = new NotificationService(this);
        NotificationUtils.createNotificationChannel(this);

        initViews();
        setListeners();

        Calendar today = Calendar.getInstance();
        updateTextViews(today);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent appointmentIntent = getIntent();
        String title = appointmentIntent.getStringExtra("TITLE");
        showTaskTitle.setText(title);
    }

    private void initViews() {
        ImageButton backButton = findViewById(R.id.backButton);
        dateTextView = findViewById(R.id.date);
        weekdayTextView = findViewById(R.id.weekday);
        CalendarView calendarView = findViewById(R.id.calendarView);
        showTaskTitle = findViewById(R.id.showtasktitle);

        ImageButton addButton = findViewById(R.id.addButton);
        Button notifyButton = findViewById(R.id.notify);

        backButton.setOnClickListener(v -> finish());

        Intent intent = new Intent(CalendarActivity.this, AppointmentActivity.class);
        addButton.setOnClickListener(v -> startActivity(intent));

        notifyButton.setOnClickListener(v -> sendNotification());
    }

    private void setListeners() {
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, dayOfMonth);
            updateTextViews(selectedDate);
        });
    }

    private void updateTextViews(Calendar calendar) {
        dateTextView.setText(CalendarHelper.formatCalendarDate(calendar));

        String[] weekdays = new String[]{"SONNTAG", "MONTAG", "DIENSTAG", "MITTWOCH", "DONNERSTAG", "FREITAG", "SAMSTAG"};
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String weekday = weekdays[dayOfWeek - 1];
        weekdayTextView.setText(weekday);
    }

    private void sendNotification() {
        String notificationTitle = "FRIENDFLOW";
        String notificationText = "Heute könnt ihr etwas zusammen unternehmen!";
        NotificationUtils.createNotificationBuilder(this, notificationTitle, notificationText);
        notificationService.sendNotification(notificationTitle, notificationText);
    }
}

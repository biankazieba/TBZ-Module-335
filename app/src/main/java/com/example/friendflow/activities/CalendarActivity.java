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
    private NotificationService notificationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        notificationService = new NotificationService(this);
        NotificationUtils.createNotificationChannel(this);

        Intent appointmentIntent = getIntent();
        String title = appointmentIntent.getStringExtra("TITLE");
        String date = appointmentIntent.getStringExtra("DATE");
        String fromTime = appointmentIntent.getStringExtra("FROM_TIME");
        String tillTime = appointmentIntent.getStringExtra("TILL_TIME");

        ImageButton backButton = findViewById(R.id.backButton);
        dateTextView = findViewById(R.id.date);
        weekdayTextView = findViewById(R.id.weekday);
        CalendarView calendarView = findViewById(R.id.calendarView);
        TextView showtasktitle = findViewById(R.id.showtasktitle);

        showtasktitle.setText(title);

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

        Button notifyButton = findViewById(R.id.notify);
        notifyButton.setOnClickListener(v -> {
            String notificationTitle = "FRIENDFLOW";
            String notificationText = "Heute könnt ihr etwas zusammen unternehmen!";
            NotificationUtils.createNotificationBuilder(this, notificationTitle, notificationText);
            notificationService.sendNotification(notificationTitle, notificationText);
        });
    }

    private void updateTextViews(Calendar calendar) {
        dateTextView.setText(CalendarHelper.formatCalendarDate(calendar));

        String[] weekdays = new String[]{"SONNTAG", "MONTAG", "DIENSTAG", "MITTWOCH", "DONNERSTAG", "FREITAG", "SAMSTAG"};
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String weekday = weekdays[dayOfWeek - 1];
        weekdayTextView.setText(weekday);
    }
}

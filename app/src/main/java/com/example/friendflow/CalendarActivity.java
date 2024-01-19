package com.example.friendflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private TextView dateTextView;
    private TextView weekdayTextView;
    private static final String CHANNEL_ID = "defaultChannel";
    private static final String CHANNEL_NAME = "Default Channel";
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

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

        Button notifybutton = findViewById(R.id.notify);
        notifybutton.setOnClickListener(v -> sendNotification());

        this.notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);

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

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("FRIENDFLOW")
                .setContentText("Heute könnt ihr etwas zusammen unternehmen!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManager.notify(0, builder.build());
    }
}

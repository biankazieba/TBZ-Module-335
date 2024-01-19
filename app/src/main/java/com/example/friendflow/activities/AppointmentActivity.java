package com.example.friendflow.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.app.AlertDialog;
import android.widget.TextView;

import com.example.friendflow.R;

public class AppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        ImageButton backButton = findViewById(R.id.backButton);
        TableRow openPopup = findViewById(R.id.openPopup);

        backButton.setOnClickListener(v -> finish());

        openPopup.setOnClickListener(view -> showPopup());

        EditText tasktitle = findViewById(R.id.tasktitle);
        EditText taskdate = findViewById(R.id.taskdate);
        EditText fromtime = findViewById(R.id.fromtime);
        EditText tilltime = findViewById(R.id.tilltime);
        Button createbutton = findViewById(R.id.createbutton);
        TextView result = findViewById(R.id.result);

        createbutton.setOnClickListener(v -> {
            String title = tasktitle.getText().toString();
            String date = taskdate.getText().toString();
            String fromTime = fromtime.getText().toString();
            String tillTime = tilltime.getText().toString();

            Intent intent = new Intent(AppointmentActivity.this, CalendarActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("DATE", date);
            intent.putExtra("FROM_TIME", fromTime);
            intent.putExtra("TILL_TIME", tillTime);
            startActivity(intent);

            tasktitle.setText("");
            taskdate.setText("");
            fromtime.setText("");
            tilltime.setText("");
        });
    }

    private void showPopup() {
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView);

        builder.setPositiveButton("HINZUFÜGEN", (dialogInterface, i) -> dialogInterface.dismiss());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

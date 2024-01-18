package com.example.friendflow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

public class AppointmentActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        ImageButton backButton = findViewById(R.id.backButton);
        TableRow openPopup = findViewById(R.id.openPopup);

        backButton.setOnClickListener(v -> {
            finish();
        });

        openPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup();
            }
        });

        EditText tasktitle  = findViewById(R.id.tasktitle);
        EditText taskdate = findViewById(R.id.taskdate);
        EditText fromtime = findViewById(R.id.fromtime);
        EditText tilltime = findViewById(R.id.tilltime);
        Button createbutton = findViewById(R.id.createbutton);
        TextView result = findViewById(R.id.result);

        createbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = tasktitle.getText().toString();
                String date = taskdate.getText().toString();
                String fromTime = fromtime.getText().toString();
                String tillTime = tilltime.getText().toString();

                result.setText("Title:\t" + title + "\nDate:\t" + date + "Fromtime:\t" + fromTime + "Tilltme:\t" + tillTime);

                tasktitle.setText("");
                taskdate.setText("");
                fromtime.setText("");
                tilltime.setText("");
            }
        });
    }

    private void showPopup() {
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView);

        builder.setPositiveButton("HINZUFÜGEN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
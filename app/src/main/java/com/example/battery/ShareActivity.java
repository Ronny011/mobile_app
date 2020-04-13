package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {
    TextView status;//low/medium/high
    ImageView icon;//emojis

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        status = findViewById(R.id.status);
        icon = findViewById(R.id.icon);
//        if (batt >= 1 && batt <= 29)
//            status.setText("Low");
//        else if (batt >= 30 && batt <= 74)
//            status.setText("Medium");
//        else if (batt >= 75 && batt <= 100)
//            status.setText("High");
//    }
}}

package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {
    TextView status;//low/medium/high
    ImageView icon;//emojis
    int percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        status = findViewById(R.id.status);
        icon = findViewById(R.id.icon);
        Intent intent = getIntent();
        percentage = intent.getIntExtra("percentage", 0);
        System.out.println(percentage);
        if (percentage >= 1 && percentage <= 29)
            status.setText("Low");
        else if (percentage >= 30 && percentage <= 74)
            status.setText("Medium");
        else if (percentage >= 75 && percentage <= 100)
            status.setText("High");
    }
}


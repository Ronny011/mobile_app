package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {
    private int relevant;//the current icon depending on percentage
    int[] images = {R.drawable.open_hands, R.drawable.thumbs_up, R.drawable.thumbs_down};//emojis
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        TextView status = findViewById(R.id.status);
        ImageView icon = findViewById(R.id.icon);
        Intent intent = getIntent();
        int percentage = intent.getIntExtra("percentage", 0);
        System.out.println(percentage);
        if (percentage >= 1 && percentage <= 29){
            status.setText(R.string.low);
            status.setTextColor(Color.RED);
            status.setBackgroundColor(Color.GREEN);
            icon.setImageResource(images[2]);}
        else if (percentage >= 30 && percentage <= 74){
            status.setText(R.string.medium);
            status.setTextColor(Color.GREEN);
            status.setBackgroundColor(Color.YELLOW);
            icon.setImageResource(images[0]);}
        else if (percentage >= 75 && percentage <= 100){
            status.setText(R.string.high);
            status.setTextColor(Color.BLUE);
            status.setBackgroundColor(Color.GRAY);
            icon.setImageResource(images[1]);}
    }
}



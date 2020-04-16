package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button share;
    TextView battMessage;
    BroadcastReceiver battBroadcast;
    int pct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        share = findViewById(R.id.button);//button element
        share.setOnClickListener(this);
        battMessage = findViewById(R.id.battBox);
        battBroadcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                pct = intent.getIntExtra("level",0);
                battMessage.setText("Battery percentage " + String.valueOf(pct) + "%");
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(battBroadcast,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(battBroadcast);
    }

    @Override
    public void onClick(View v) {
        String ppp = String.valueOf(pct);
        Intent intent2 = new Intent(this, ShareActivity.class);
        intent2.putExtra("percentage", ppp);
        startActivity(intent2);//goes to second activity on click
    }
}



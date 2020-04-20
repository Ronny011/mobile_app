package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String regex_p = "^[+][0-9]{10,13}$";//for phone field
    private static String regex_n = ".*\\d+.*";// for name field
    private Button share;
    private Button dummy;
    private TextView name;
    private TextView phone;
    private TextView battMessage;
    BroadcastReceiver battBroadcast;
    private int pct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        share = findViewById(R.id.button);//button element
        dummy = findViewById(R.id.trans_button);
        battMessage = findViewById(R.id.battBox);
        name = findViewById(R.id.nameBox);
        phone = findViewById(R.id.phoneBox);
        share.setOnClickListener(this);
        dummy.setOnClickListener(this);
        battBroadcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                pct = intent.getIntExtra("level",0);
                battMessage.setText("Battery percentage " + String.valueOf(pct) + "%");
            }
        };
        name.addTextChangedListener(watcher);
        phone.addTextChangedListener(watcher);
    }
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameChange = name.getText().toString();
            String phoneChange = phone.getText().toString();
            if(nameChange.isEmpty() || phoneChange.isEmpty())
            {
                try {
                    throw new Exception("All fields must be filled");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    share.setEnabled(false);
                }
            }
            else if (nameChange.matches(regex_n) || phoneChange.matches(regex_p))
            {
                try
                {
                    throw new Exception("Make sure you provide correct input");
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                    share.setEnabled(false);
                }
            }
            else if (nameChange.contains(" ") || phoneChange.contains(" "))
            {
                try
                {
                    throw new Exception("No spaces are allowed in the fields");
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    share.setEnabled(false);
                }
            }
            else
                share.setEnabled(true);//if everything is ok
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

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
        if (v.getId() == R.id.trans_button){

        }
        else if (v.getId() == R.id.button){
            Intent share = new Intent(this, ShareActivity.class);
            share.putExtra("percentage", pct);
            startActivity(share);//goes to second activity on click
        }
    }
}



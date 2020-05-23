package com.example.firstprojectever.plugins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.firstprojectever.Activity.MainActivity;
import com.example.firstprojectever.R;
import com.example.firstprojectever.Storage.data_booking;

public class homestart extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3500;
    data_booking db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homestart);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homestart = new Intent(homestart.this, MainActivity.class);
                startActivity(homestart);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}

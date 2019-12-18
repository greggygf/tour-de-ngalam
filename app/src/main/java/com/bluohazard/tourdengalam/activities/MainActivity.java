package com.bluohazard.tourdengalam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.settings.SharedPref;

public class MainActivity extends AppCompatActivity {

    SharedPref sharedPref;
    public ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);

        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.DarkAppTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);

        // Change the logo when dark/day mode

        if (sharedPref.loadNightModeState() == true) {
            logo.setImageResource(R.drawable.logo_dark);
        } else {
            logo.setImageResource(R.drawable.logo);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}

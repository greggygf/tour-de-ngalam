package com.bluohazard.tourdengalam.activities.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.MainMenuActivity;
import com.bluohazard.tourdengalam.settings.SharedPref;

public class AboutActivity extends AppCompatActivity {

    SharedPref sharedPref;

    public ImageView imageLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);

        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.DarkAppTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        imageLogo = findViewById(R.id.imageLogo);

        // Change the logo when dark/day mode

        if (sharedPref.loadNightModeState() == true) {
            imageLogo.setImageResource(R.drawable.logo_dark);
        } else {
            imageLogo.setImageResource(R.drawable.logo);
        }
    }

    public void onClickMainMenu(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}

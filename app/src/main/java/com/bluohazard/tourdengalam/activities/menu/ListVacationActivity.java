package com.bluohazard.tourdengalam.activities.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.MainMenuActivity;
import com.bluohazard.tourdengalam.activities.list_vacation.BeachActivity;
import com.bluohazard.tourdengalam.activities.list_vacation.MountainActivity;
import com.bluohazard.tourdengalam.activities.list_vacation.PlaygroundActivity;
import com.bluohazard.tourdengalam.settings.SharedPref;

public class ListVacationActivity extends AppCompatActivity {

    SharedPref sharedPref;

    public ImageView beachLogo, mountainLogo, playgroundLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);

        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.DarkAppTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vacation);

        beachLogo = findViewById(R.id.beachLogo);
        mountainLogo = findViewById(R.id.mountainLogo);
        playgroundLogo = findViewById(R.id.playgroundLogo);

        // Change the logo when dark/day mode

        if (sharedPref.loadNightModeState() == true) {
            beachLogo.setImageResource(R.drawable.sunset_dark);
            mountainLogo.setImageResource(R.drawable.mountain_dark);
            playgroundLogo.setImageResource(R.drawable.playground_dark);
        } else {
            beachLogo.setImageResource(R.drawable.sunset);
            mountainLogo.setImageResource(R.drawable.mountain);
            playgroundLogo.setImageResource(R.drawable.playground);
        }

    }

    public void onClickMainMenu(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void onClickBeach(View view) {
        Intent intent = new Intent(this, BeachActivity.class);
        startActivity(intent);
    }

    public void onClickMountain(View view) {
        Intent intent = new Intent(this, MountainActivity.class);
        startActivity(intent);
    }

    public void onClickPlayground(View view) {
        Intent intent = new Intent(this, PlaygroundActivity.class);
        startActivity(intent);
    }
}

package com.bluohazard.tourdengalam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void onClickBackMainMenu(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}

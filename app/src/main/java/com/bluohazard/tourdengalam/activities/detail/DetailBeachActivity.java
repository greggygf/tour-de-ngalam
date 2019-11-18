package com.bluohazard.tourdengalam.activities.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.bluohazard.tourdengalam.R;

public class DetailBeachActivity extends AppCompatActivity {

    TextView tvNameBeach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_beach);

        tvNameBeach = findViewById(R.id.tv_name_beach);
        tvNameBeach.setText(getIntent().getStringExtra("name"));
    }
}

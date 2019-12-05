package com.bluohazard.tourdengalam.activities.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.list_vacation.BeachActivity;
import com.bumptech.glide.Glide;

public class DetailBeachActivity extends AppCompatActivity {

    TextView tvNameBeach, tvLocation, tvDescription;
    public ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_beach);

        tvNameBeach = findViewById(R.id.tv_name_beach);
        tvNameBeach.setText(getIntent().getStringExtra("name"));

        image = findViewById(R.id.tv_image_beach);
        setDisplayImage(getIntent().getStringExtra("image-url"), DetailBeachActivity.this);

        tvLocation = findViewById(R.id.tv_location_beach);
        tvLocation.setText(getIntent().getStringExtra("location"));

        tvDescription = findViewById(R.id.tv_description_beach);
        tvDescription.setText(getIntent().getStringExtra("description"));
    }

    public void setDisplayImage(String imageUrl, Context context) {
        Glide.with(context)
                .load(imageUrl)
                .into(image);
    }

    public void onClickBeach(View view) {
        Intent intent = new Intent(this, BeachActivity.class);
        startActivity(intent);
    }
}

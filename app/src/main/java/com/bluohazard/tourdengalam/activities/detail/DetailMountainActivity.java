package com.bluohazard.tourdengalam.activities.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.list_vacation.MountainActivity;
import com.bumptech.glide.Glide;

public class DetailMountainActivity extends AppCompatActivity {

    TextView tvNameMountain, tvLocation, tvDescription;
    public ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mountain);

        tvNameMountain = findViewById(R.id.tv_name_mountain);
        tvNameMountain.setText(getIntent().getStringExtra("name"));

        image = findViewById(R.id.tv_image_mountain);
        setDisplayImage(getIntent().getStringExtra("image-url"), DetailMountainActivity.this);

        tvLocation = findViewById(R.id.tv_location_mountain);
        tvLocation.setText(getIntent().getStringExtra("location"));

        tvDescription = findViewById(R.id.tv_description_mountain);
        tvDescription.setText(getIntent().getStringExtra("description"));
    }

    public void setDisplayImage(String imageUrl, Context context) {
        Glide.with(context)
                .load(imageUrl)
                .into(image);
    }

    public void onClickMountain(View view) {
        Intent intent = new Intent(this, MountainActivity.class);
        startActivity(intent);
    }
}

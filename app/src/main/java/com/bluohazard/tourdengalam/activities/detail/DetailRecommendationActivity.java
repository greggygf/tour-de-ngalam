package com.bluohazard.tourdengalam.activities.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.ResultActivity;
import com.bumptech.glide.Glide;

public class DetailRecommendationActivity extends AppCompatActivity {

    TextView tvNameRecommendation, tvLocation, tvDescription;
    public ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recommendation);

        tvNameRecommendation = findViewById(R.id.tv_name_recommendation);
        tvNameRecommendation.setText(getIntent().getStringExtra("name"));

        image = findViewById(R.id.tv_image_recommendation);
        setDisplayImage(getIntent().getStringExtra("image-url"), DetailRecommendationActivity.this);

        tvLocation = findViewById(R.id.tv_location_recommendation);
        tvLocation.setText(getIntent().getStringExtra("location"));

        tvDescription = findViewById(R.id.tv_description_recommendation);
        tvDescription.setText(getIntent().getStringExtra("description"));
    }

    public void setDisplayImage(String imageUrl, Context context) {
        Glide.with(context)
                .load(imageUrl)
                .into(image);
    }

    public void onClickResult(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}

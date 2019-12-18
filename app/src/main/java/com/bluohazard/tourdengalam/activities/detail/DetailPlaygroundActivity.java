package com.bluohazard.tourdengalam.activities.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.list_vacation.PlaygroundActivity;
import com.bluohazard.tourdengalam.settings.SharedPref;
import com.bumptech.glide.Glide;

public class DetailPlaygroundActivity extends AppCompatActivity {

    TextView tvNamePlayground, tvLocation, tvDescription;
    public ImageView image;

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);

        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.DarkAppTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_playground);

        tvNamePlayground = findViewById(R.id.tv_name_playground);
        tvNamePlayground.setText(getIntent().getStringExtra("name"));

        image = findViewById(R.id.tv_image_playground);
        setDisplayImage(getIntent().getStringExtra("image-url"), DetailPlaygroundActivity.this);

        tvLocation = findViewById(R.id.tv_location_playground);
        tvLocation.setText(getIntent().getStringExtra("location"));

        tvDescription = findViewById(R.id.tv_description_playground);
        tvDescription.setText(getIntent().getStringExtra("description"));
    }

    public void setDisplayImage(String imageUrl, Context context) {
        Glide.with(context)
                .load(imageUrl)
                .into(image);
    }

    public void onClickPlayground(View view) {
        Intent intent = new Intent(this, PlaygroundActivity.class);
        startActivity(intent);
    }
}

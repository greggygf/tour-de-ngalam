package com.bluohazard.tourdengalam.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.menu.AboutActivity;
import com.bluohazard.tourdengalam.activities.menu.HistoryActivity;
import com.bluohazard.tourdengalam.activities.menu.ListVacationActivity;
import com.bluohazard.tourdengalam.activities.menu.SurveyRecommendationActivity;
import com.jackandphantom.blurimage.BlurImage;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Blur Images
        ImageView imageView = findViewById(R.id.wallpaper);
        Bitmap bitmap = BlurImage.with(getApplicationContext()).load(R.drawable.kampungbiru).intensity(5).Async(true).getImageBlur();
        imageView.setImageBitmap(bitmap);
    }

    public void onClickListVacation(View view) {
        Intent intent = new Intent(this, ListVacationActivity.class);
        startActivity(intent);
    }

    public void onClickAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void onClickHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void onClickRecommendation(View view) {
        Intent intent = new Intent(this, SurveyRecommendationActivity.class);
        startActivity(intent);
    }
}

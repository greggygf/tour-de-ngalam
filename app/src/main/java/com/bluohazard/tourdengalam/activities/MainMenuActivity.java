package com.bluohazard.tourdengalam.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bluohazard.tourdengalam.R;
import com.bluohazard.tourdengalam.activities.menu.AboutActivity;
import com.bluohazard.tourdengalam.activities.menu.HistoryActivity;
import com.bluohazard.tourdengalam.activities.menu.ListVacationActivity;
import com.bluohazard.tourdengalam.activities.menu.SurveyRecommendationActivity;
import com.bluohazard.tourdengalam.settings.Setting;
import com.bluohazard.tourdengalam.settings.SharedPref;
import com.jackandphantom.blurimage.BlurImage;

public class MainMenuActivity extends AppCompatActivity {

    SharedPref sharedPref;
    Button listVacationButton, recommendationButton, aboutButton, historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);

        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.DarkAppTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // search the button logo
        listVacationButton = findViewById(R.id.btnList);
        recommendationButton = findViewById(R.id.btnRecommendation);
        aboutButton = findViewById(R.id.btnAbout);
        historyButton = findViewById(R.id.btnHistory);

        // drawable for light mode
        Drawable imgListVacation = getResources().getDrawable(R.drawable.search);
        Drawable imgRecommendation = getResources().getDrawable(R.drawable.recommendation);
        Drawable imgAbout = getResources().getDrawable(R.drawable.leaflet);
        Drawable imgHistory = getResources().getDrawable(R.drawable.city2);

        // drawable for dark mode
        Drawable imgListVacationDark = getResources().getDrawable(R.drawable.search_dark);
        Drawable imgRecommendationDark = getResources().getDrawable(R.drawable.recommendation_dark);
        Drawable imgAboutDark = getResources().getDrawable(R.drawable.leaflet_dark);
        Drawable imgHistoryDark = getResources().getDrawable(R.drawable.city_dark);

        // set the image when dark or light
        if (sharedPref.loadNightModeState() == true) {
            listVacationButton.setCompoundDrawablesWithIntrinsicBounds(null, imgListVacationDark, null, null);
            recommendationButton.setCompoundDrawablesWithIntrinsicBounds(null, imgRecommendationDark, null, null);
            aboutButton.setCompoundDrawablesWithIntrinsicBounds(null, imgAboutDark, null, null);
            historyButton.setCompoundDrawablesWithIntrinsicBounds(null, imgHistoryDark, null, null);
        } else {
            listVacationButton.setCompoundDrawablesWithIntrinsicBounds(null, imgListVacation, null, null);
            recommendationButton.setCompoundDrawablesWithIntrinsicBounds(null, imgRecommendation, null, null);
            aboutButton.setCompoundDrawablesWithIntrinsicBounds(null, imgAbout, null, null);
            historyButton.setCompoundDrawablesWithIntrinsicBounds(null, imgHistory, null, null);
        }

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

    public void onClickSettings(View view) {
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }
}

package com.bluohazard.tourdengalam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
        Intent intent = new Intent(this,ListVacationActivity.class);
        startActivity(intent);
    }

    public void onClickAbout(View view) {
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }
}

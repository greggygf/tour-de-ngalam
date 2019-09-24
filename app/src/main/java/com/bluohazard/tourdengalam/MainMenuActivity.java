package com.bluohazard.tourdengalam;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.jackandphantom.blurimage.BlurImage;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Blur Images
        ImageView imageView = findViewById(R.id.wallpaper);
        Bitmap bitmap = BlurImage.with(getApplicationContext()).load(R.drawable.kampungbiru).intensity(20).Async(true).getImageBlur();
        imageView.setImageBitmap(bitmap);
    }
}

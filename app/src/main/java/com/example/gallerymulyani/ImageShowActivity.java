package com.example.gallerymulyani;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ImageShowActivity extends AppCompatActivity {

    ImageView imageShow;
    String imagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        if (getSupportActionBar() != null) {
            ActionBar backButton = getSupportActionBar();
            backButton.setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setTitle(R.string.title);
            getSupportActionBar().setTitle(MainActivity.arrayListImages.get(0).getPhotoName());
        }

        imageShow = (ImageView) findViewById(R.id.imageShow);
        imagePath = getIntent().getExtras().getString("String Image Path", null);

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        imageShow.setImageBitmap(bitmap);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

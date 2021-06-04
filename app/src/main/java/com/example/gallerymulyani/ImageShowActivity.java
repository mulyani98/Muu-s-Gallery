package com.example.gallerymulyani;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ImageShowActivity extends AppCompatActivity {

    ImageView imageShow;
    String imagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        //inisialisasi imageview by id xml
        imageShow = (ImageView) findViewById(R.id.imageShow);

        /* getintent data imagePath yang dipass dari intent di photoActivity */
        imagePath = getIntent().getExtras().getString("String Image Path", null);

        /* kalau supportactionbar tidak sama dengan null
        * kalau actionbar-nya ada */
        if (getSupportActionBar() != null) {
            ActionBar backButton = getSupportActionBar();

            /*
            * setDisplayHomeAsUpEnabled, kalau true berarti kembali ke halaman sebelumnya (prev activity) (satu level sebelumnya)
            * kalau false kembali ke top level (halaman awal app) */
            backButton.setDisplayHomeAsUpEnabled(true);

            /* file = representasi abstrak dari file dan nama path direktori */
            File imageFile = new File (imagePath);

            /* getname dari File yang isinya imagePath */
            String fileName = imageFile.getName();

            //set nama action bar-nya
            getSupportActionBar().setTitle(fileName);
        }

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        imageShow.setImageBitmap(bitmap);
    }

    /*
    * This method is called whenever the user chooses to navigate Up within your application's
    * activity hierarchy from the action bar */
    @Override
    public boolean onSupportNavigateUp() {

        /* Called when the activity has detected the user's press of the back key */
        onBackPressed();
        return true;
    }
}

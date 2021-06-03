package com.example.gallerymulyani;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

public class SplashActivity extends Activity {

    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //intent ke main.activity setelah menampilkan splash screen selama 2 detik
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000); //menampilkan splash screen selama 2 detik
    }
}

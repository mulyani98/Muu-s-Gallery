package com.example.gallerymulyani;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class PicHolder {

    public ImageView picture;

    PicHolder(@NonNull View itemView){
        super();

        picture = itemView.findViewById(R.id.imageHolder);
    }
}

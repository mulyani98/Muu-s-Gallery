package com.example.gallerymulyani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;

import static androidx.core.view.ViewCompat.setTransitionName;

public class PictureAdapter extends ArrayAdapter<PicHolder> {

    private ArrayList<PictureFacer> pictureList;
    private Context pictureContext;

    public PictureAdapter (ArrayList<PictureFacer> pictureList, Context pictureContext){
        super(pictureContext,R.layout.picture_folder_item);
        this.pictureList = pictureList;
        this.pictureContext = pictureContext;
    }

    public PicHolder onCreateViewHolder(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View cell = inflater.inflate(R.layout.picture_folder_item, container, false);
        return new PicHolder(cell);
    }

    public void onBindViewHolder(@NonNull final PicHolder holder, final int position) {

        final PictureFacer image = pictureList.get(position);

        setTransitionName(holder.picture, String.valueOf(position) + "_image");

        holder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public int getItemCount() {
        return pictureList.size();
    }
}

package com.example.gallerymulyani;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PhotoActivity extends AppCompatActivity {

    int position;
    String folderName;
    private GridView gridView;
    GridViewAdapter adapter;

    ArrayList<ImagesModel> allImages;

    //tried at home
//    String albumPath;
//    TextView albumName;
//    GridView gridViewPicture;
//    ArrayList<PictureFacer> allPicture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tried at home
//        setContentView(R.layout.activity_imagedisplay);

        if (getSupportActionBar() != null) {
            ActionBar backButton = getSupportActionBar();
            backButton.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.album);
        }

        //original
        gridView = (GridView) findViewById(R.id.gridViewFolder);
//        position = getIntent().getIntExtra("folderName", 0);

        //try again
        position = getIntent().getIntExtra("value",0);

//        allImages = (ArrayList<ImagesModel>) getIntent().getSerializableExtra("gambarku", );


//        folderName = getIntent().getIntExtra("gambarku", 0);

        //asli
//        adapter = new GridViewAdapter(this, MainActivity.filteredImages);

        //try again
        adapter = new GridViewAdapter(this, MainActivity.arrayListImages, position);

        gridView.setAdapter(adapter);


        //tried at home
//        albumName = findViewById(R.id.albumName);
////        albumName.setText(getIntent().getStringExtra("albumName"));
//
//        albumPath = getIntent().getStringExtra("albumPath");
//        allPicture = new ArrayList<>();
//        gridViewPicture = findViewById(R.id.gridViewPicture);
//
//        if (allPicture.isEmpty()) {
//            allPicture = getAllPicturebyFolder(albumPath);
//            gridViewPicture.setAdapter(new PictureAdapter(allPicture, PhotoActivity.this));
//        }
//        else {
//
//        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //tried at home
    public void onPicClicked(String pictureFolderPath,String folderName) {

    }

    //tried at home
//    public ArrayList<PictureFacer> getAllPicturebyFolder(String path) {
//
//        ArrayList<PictureFacer> allImages = new ArrayList<>();
//        Uri allPictureUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        String[] projection = {MediaStore.Images.ImageColumns.DATA,
//                MediaStore.Images.Media.DISPLAY_NAME,
//                MediaStore.Images.Media.SIZE};
//
//        Cursor cursor = PhotoActivity.this.getContentResolver().query(allPictureUri, projection,
//                MediaStore.Images.Media.DATA + "like ?", new String[]{"%" + path + "%"}, null);
//
//        try {
//            cursor.moveToFirst();
//            do {
//                PictureFacer pic = new PictureFacer();
//                pic.setPictureName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)));
//                pic.setPicturePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
//                pic.setPictureSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));
//
//                allImages.add(pic);
//            }
//            while (cursor.moveToNext());
//            cursor.close();
//            ArrayList<PictureFacer> reSelection = new ArrayList<>();
//
//            for (int i = allImages.size() - 1; i > -1; i--) {
//                reSelection.add(allPicture.get(i));
//            }
//
//            allImages = reSelection;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return allImages;
//    }

}

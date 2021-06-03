package com.example.gallerymulyani;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PhotoActivity extends AppCompatActivity {

    private GridView gridViewListPhoto;
    GridViewAdapter gridViewAdapter;
    String selectedFolder;

    public ArrayList<ImagesModel> filteredImages = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* get intent folderpath dari mainactivity */
        selectedFolder = getIntent().getExtras().getString("filter","");

        /* arraylist photo yang sudah difilter per folder
        * disini foldernya cuman satu, yang di klik, bukan semua folder
        * bedanya, arrayListImages berisi semua folder yang ada berserta fotonya */
        filterImage();

        if (getSupportActionBar() != null) {
            ActionBar backButton = getSupportActionBar();
            backButton.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(filteredImages.get(0).getFolderName());
        }

        gridViewListPhoto = (GridView) findViewById(R.id.gridViewFolder);
        gridViewAdapter = new GridViewAdapter(this, filteredImages);
        gridViewListPhoto.setAdapter(gridViewAdapter);

        gridViewListPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* passing data photo path ke imageshowactivity */
                Intent intent = new Intent(PhotoActivity.this, ImageShowActivity.class);
                intent.putExtra("String Image Path", filteredImages.get(position).getPhotoPath());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    /* memfilter allimages yang ada di storage menjadi folder yang diklik */
    public void filterImage(){

        /* looping allimages */
        for (int i=0; i < MainActivity. allGalleryImages.size(); i++){

            /* di cek apakah folderpath dari allimages sama dengan selectedfolder
            (folderpath yang diget dari mainactivity)*/
            if (MainActivity.allGalleryImages.get(i).getFolderPath().equals(selectedFolder)){

                //buat imagesmodel baru
                ImagesModel filteredImagesModel = new ImagesModel();

                //set path foldernya
                filteredImagesModel.setFolderPath(selectedFolder);

                //set nama foldernya
                filteredImagesModel.setFolderName(MainActivity.allGalleryImages.get(i).getFolderName());

                //set path fotonya
                filteredImagesModel.setPhotoPath(MainActivity.allGalleryImages.get(i).getPhotoPath());

                //tambah numOfPics-nya (buat ditampilin di FolderSize)
                filteredImagesModel.addNumOfPics();

                //tambahkan imagesmodel baru tersebut kedalam array filteredImages
                filteredImages.add(filteredImagesModel);
            }
        }
    }

}

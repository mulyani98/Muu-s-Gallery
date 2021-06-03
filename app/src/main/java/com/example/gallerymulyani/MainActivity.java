package com.example.gallerymulyani;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ImagesModel> arrayListImages = new ArrayList<>();
    public static ArrayList<ImagesModel> allGalleryImages = new ArrayList<>();

    GridView gridViewFolder;
    private static final int REQUEST_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            ActionBar backButton = getSupportActionBar();
            backButton.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
        }

        gridViewFolder = (GridView) findViewById(R.id.gridViewFolder);
        gridViewFolder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent move = new Intent(getApplicationContext(), PhotoActivity.class);

                //passing folder path ke photo activity
                move.putExtra("filter", arrayListImages.get(position).getFolderPath());
                startActivity(move);

            }
        });

        //permission
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSIONS);
        }

        ArrayList<ImagesModel> folds = folderName_ImagePath();

        FolderAdapter folderAdapter = new FolderAdapter(MainActivity.this, folds);

        if (folds.isEmpty()){
            Toast.makeText(MainActivity.this, "There is no Album here",Toast.LENGTH_LONG).show();
        }
        else{
            gridViewFolder.setAdapter(folderAdapter);
        }

    }

    public ArrayList<ImagesModel> folderName_ImagePath(){
        ArrayList<String> folderPath = new ArrayList<>();

        Uri allImagesuri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID};

        Cursor cursor = this.getContentResolver().query(allImagesuri, projection, null, null, null);

        try{
            //kalau cursor tidak sama dengan null
            if (cursor != null){
                //maka cursor Move to the last row.
                cursor.moveToFirst();
            }
            do{
                ImagesModel folds = new ImagesModel();

                String namePhoto = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                String nameAlbum = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                String pathPhoto = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

                /* substring (beginIndex (inclusive), endIndex (exclusive)
                * disini beginIndex = 0
                * endIndex = */
                String pathAlbum = pathPhoto.substring(0, pathPhoto.lastIndexOf(nameAlbum + "/"));

                pathAlbum = pathAlbum + nameAlbum + "/";

                ImagesModel allImagesModel = new ImagesModel();
                allImagesModel.setFolderPath(pathAlbum);
                allImagesModel.setFolderName(nameAlbum);
                allImagesModel.setPhotoPath(pathPhoto);
                allImagesModel.setPhotoName(namePhoto);
                allImagesModel.addNumOfPics();
                allGalleryImages.add(allImagesModel);


                /*
                * jika folderPath*/
                if (!folderPath.contains(pathAlbum)){
                    folderPath.add(pathAlbum);

                    folds.setFolderPath(pathAlbum);
                    folds.setFolderName(nameAlbum);
                    folds.setPhotoPath(pathPhoto);
                    folds.setPhotoName(namePhoto);
                    folds.addNumOfPics();
                    arrayListImages.add(folds);

                }

                else {
                    for (int i =0; i<arrayListImages.size(); i++){
                        if (arrayListImages.get(i).getFolderPath().equals(pathAlbum)) {
                            arrayListImages.get(i).setPhotoPath(pathPhoto);
                            arrayListImages.get(i).addNumOfPics();
                        }
                    }
                }

            }while (cursor.moveToNext());
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        FolderAdapter folderAdapter = new FolderAdapter(getApplicationContext(), arrayListImages);
        gridViewFolder.setAdapter(folderAdapter);
        return arrayListImages;

    }

    //caritau
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                //cari tau
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        folderName_ImagePath();
                    } else {
                        Toast.makeText(MainActivity.this, "The app was not allowed to read or write your storage", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


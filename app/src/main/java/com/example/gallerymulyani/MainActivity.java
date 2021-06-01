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
    public static ArrayList<ImagesModel> filteredImages = new ArrayList<>();

    boolean booleanFolder;
    FolderAdapter folderAdapter;
    GridView gridViewFolder;
    private static final int REQUEST_PERMISSIONS = 100;
//    private static final int REQUEST_PERMISSIONS = 1;

//    //trial and error
//    public static ArrayList<ImagesModel> pictureFolder = new ArrayList<>();
//    ArrayList<String> picturePath = new ArrayList<>();

    //tried at home
//    PictureFolderAdapter pictureFolderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            ActionBar backButton = getSupportActionBar();
            backButton.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title);
        }

        //second
        gridViewFolder = (GridView) findViewById(R.id.gridViewFolder);
        gridViewFolder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent move = new Intent(getApplicationContext(), PhotoActivity.class);
                move.putExtra("value", position);

//                move.putExtra("gambarku", allGalleryImages);

                startActivity(move);

                //original
//                Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
////                intent.putExtra("folderName",folderName_ImagePath());
//                intent.putExtra("value",position);
//                startActivity(intent);

                //tried at home
//                Intent move = new Intent(MainActivity.this,PhotoActivity.class);
////                move.putExtra("albumPath",arrayListImages);
//                move.putExtra("albumPath",folderName_ImagePath());
//
//                //move.putExtra("recyclerItemSize",getCardsOptimalWidth(4));
//                startActivity(move);
            }
        });

        //original
        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        }
        else {
            Log.e("Else","Else");
//            folderName_ImagePath();
            fn_imagespath();
        }


        //trial and error
        //pop up permissions
//        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE))) {
//
//            } else {
//                ActivityCompat.requestPermissions(MainActivity.this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
//            }
//        }

//        RecyclerView recyclerView =findViewById(R.id.recyclerFolder);
//        recyclerView.hasFixedSize();
//        ArrayList<ImagesModel> folds = getPicturePath();
////        FolderAdapter folderAdapter = new FolderAdapter(MainActivity.this, folds);
//
//        if (folds.isEmpty()){
//            Toast.makeText(MainActivity.this, "There is no Album here",Toast.LENGTH_LONG).show();
//        }
//        else{
//            RecyclerView.Adapter folderAdapter = new FolderAdapter(MainActivity.this, folds);
//            recyclerView.setAdapter(folderAdapter);
//        }

        //original beneran
//        ArrayList<ImagesModel> folds = folderName_ImagePath();
//        FolderAdapter folderAdapter = new FolderAdapter(MainActivity.this, folds);
//
//        if (folds.isEmpty()){
//            Toast.makeText(MainActivity.this, "There is no Album here",Toast.LENGTH_LONG).show();
//        }
//        else{
//            gridViewFolder.setAdapter(folderAdapter);
//        }

        //tried at home
//        if(ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED)
//            ActivityCompat.requestPermissions(MainActivity.this,
//                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                    REQUEST_PERMISSIONS);
//
//        ArrayList<ImagesModel> folds = folderName_ImagePath();
//
//        if (folds.isEmpty()){
//            Toast.makeText(this, "There is no Album here", Toast.LENGTH_LONG).show();
//        }
//        else{
//            FolderAdapter folderAdapter = new FolderAdapter(MainActivity.this, folds);
//            gridViewFolder.setAdapter(folderAdapter);
//        }


    }

    //original
    public ArrayList<ImagesModel> folderName_ImagePath(){
        arrayListImages.clear();

//        int int_position = 0;
//        Uri uri;
//        Cursor cursor;
//
//        int column_index_data, column_index_folder_name;
//
//        String absolutePathOfImage = null;
//
//        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//
//        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
//
//        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, null);
//
//        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

//        while (cursor.moveToNext()){
//            absolutePathOfImage = cursor.getString(column_index_data);
//            Log.e("Column", absolutePathOfImage);
//            Log.e("Folder", cursor.getString(column_index_folder_name));
//
//            for (int i = 0; i < arrayListImages.size(); i++){
//                if (arrayListImages.get(i).getFolderName().equals(cursor.getString(column_index_folder_name))){
//                    booleanFolder = true;
//                    int_position = i;
//                    break;
//                }
//                else {
//                    booleanFolder = false;
//                }
//            }
//
//            if (booleanFolder){
//                ArrayList<String> arrayListPath = new ArrayList<>(arrayListImages.get(int_position).getArrayList_ImagePath());
//                arrayListPath.add(absolutePathOfImage);
//                arrayListImages.get(int_position).setArrayList_ImagePath(arrayListPath);
//                arrayListImages.get(int_position).setFirstPic(arrayListPath);
//            }
//            else {
//                ArrayList<String> arrayListPath = new ArrayList<>();
//                arrayListPath.add(absolutePathOfImage);
//                ImagesModel imagesModel = new ImagesModel();
//
//                imagesModel.setFolderName(cursor.getString(column_index_folder_name));
//                imagesModel.setArrayList_ImagePath(arrayListPath);
//                imagesModel.setFirstPic(arrayListPath);
//
//                arrayListImages.add(imagesModel);
//            }
//
//        }
//
//        for (int i = 0; i < arrayListImages.size(); i++){
//            Log.e("FOLDER", arrayListImages.get(i).getFolderName());
//
//            for(int j = 0; j < arrayListImages.get(i).getArrayList_ImagePath().size(); j++){
//                Log.e("FILE", arrayListImages.get(i).getArrayList_ImagePath().get(j));
//            }
//        }

        //original original
//        ArrayList<ImagesModel> pictureFolder = new ArrayList<>();

        ArrayList<String> folderPath = new ArrayList<>();

        Uri allImagesuri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID};

        Cursor cursor = this.getContentResolver().query(allImagesuri, projection, null, null, null);

        try{
            if (cursor != null){
                cursor.moveToFirst();
            }
            do{
                ImagesModel folds = new ImagesModel();

                String namePhoto = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                String nameAlbum = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                String pathPhoto = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                String pathAlbum = pathPhoto.substring(0, pathPhoto.lastIndexOf(nameAlbum + "/"));

                pathAlbum = pathAlbum + nameAlbum + "/";

                ImagesModel allImagesModel = new ImagesModel();
                allImagesModel.setStringImagePath(pathAlbum);
                allImagesModel.setFolderName(nameAlbum);
                allImagesModel.setFirstPic(pathPhoto);
                allImagesModel.addPics();
                allGalleryImages.add(allImagesModel);

                for (int i=0; i < allGalleryImages.size(); i++){
                    if (allGalleryImages.get(i).getStringImagePath().equals(pathAlbum)){
                        ImagesModel filteredImagesModel = new ImagesModel();
                        filteredImagesModel.setStringImagePath(pathAlbum);
                        filteredImagesModel.setFolderName(nameAlbum);
                        filteredImagesModel.setFirstPic(pathPhoto);
                        filteredImagesModel.addPics();
                        filteredImages.add(filteredImagesModel);
                    }
                    else {

                    }
                }

                if (!folderPath.contains(pathAlbum)){
                    folderPath.add(pathAlbum);

                    folds.setStringImagePath(pathAlbum);
                    folds.setFolderName(nameAlbum);
                    folds.setFirstPic(pathPhoto);
                    folds.addPics();
                    arrayListImages.add(folds);

                }

                else {
                    for (int i =0; i<arrayListImages.size(); i++){
                        if (arrayListImages.get(i).getStringImagePath().equals(pathAlbum)) {
                            arrayListImages.get(i).setFirstPic(pathPhoto);
                            arrayListImages.get(i).addPics();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                        folderName_ImagePath();
                        fn_imagespath();

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

//    //tried at home
//    public void onPicClicked(String pictureFolderPath,String folderName) {
//        Intent move = new Intent(MainActivity.this,PhotoActivity.class);
//        move.putExtra("folderPath",pictureFolderPath);
//        move.putExtra("folderName",folderName);
//
//        //move.putExtra("recyclerItemSize",getCardsOptimalWidth(4));
//        startActivity(move);
//    }

    //trial and error
//    ArrayList<ImagesModel> getPicturePath(){
//
//
//        Uri allImagesUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        String[] projection = {MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
//                MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID};
//
//        Cursor cursor = this.getContentResolver().query(allImagesUri, projection, null, null, null);
//
//
//        try{
//            if (cursor != null){
//                cursor.moveToFirst();
//            }
//            do {
//                ImagesModel folder = new ImagesModel();
//                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
//                String folders = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
//                String dataPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
//
//
//                String folderPaths = dataPath.substring(0, dataPath.lastIndexOf(folders + "/"));
//                folderPaths = folderPaths+folders+"/";
//
//                if (!picturePath.contains(folderPaths)){
//                    picturePath.add(folderPaths);
//
//                    folder.setPath(folderPaths);
//                    folder.setFolderName(folders);
//                    folder.setFirstPic(dataPath);
//                    folder.addPics();
//                    pictureFolder.add(folder);
//                }
//                else{
//                    for (int i = 0; i < pictureFolder.size(); i++){
//                        if(pictureFolder.get(i).getPath().equals(folderPaths)){
//                            pictureFolder.get(i).setFirstPic(dataPath);
//                            pictureFolder.get(i).addPics();
//                        }
//                    }
//                }
//            }
//            while (cursor.moveToNext());
//            cursor.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i<pictureFolder.size(); i++){
//            Log.d("Picture Folder", pictureFolder.get(i).getFolderName()+ " and path = "+pictureFolder.get(i).getPath()+" "+pictureFolder.get(i).getNumberOfPics());
//
//        }
//
//        return pictureFolder;
//    }

    //try again
    public ArrayList<ImagesModel> fn_imagespath() {
        arrayListImages.clear();

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            Log.e("Column", absolutePathOfImage);
            Log.e("Folder", cursor.getString(column_index_folder_name));

            for (int i = 0; i < arrayListImages.size(); i++) {
                if (arrayListImages.get(i).getFolderName().equals(cursor.getString(column_index_folder_name))) {
                    booleanFolder = true;
                    int_position = i;
                    break;
                } else {
                    booleanFolder = false;
                }
            }


            if (booleanFolder) {

                ArrayList<String> al_path = new ArrayList<>();
                al_path.addAll(arrayListImages.get(int_position).getArrayList_ImagePath());
                al_path.add(absolutePathOfImage);
                arrayListImages.get(int_position).setArrayList_ImagePath(al_path);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                al_path.add(absolutePathOfImage);
                ImagesModel obj_model = new ImagesModel();
                obj_model.setFolderName(cursor.getString(column_index_folder_name));
                obj_model.setArrayList_ImagePath(al_path);

                arrayListImages.add(obj_model);


            }


        }


        for (int i = 0; i < arrayListImages.size(); i++) {
            Log.e("FOLDER", arrayListImages.get(i).getFolderName());
            for (int j = 0; j < arrayListImages.get(i).getArrayList_ImagePath().size(); j++) {
                Log.e("FILE", arrayListImages.get(i).getArrayList_ImagePath().get(j));
            }
        }
        folderAdapter = new FolderAdapter(getApplicationContext(),arrayListImages);
        gridViewFolder.setAdapter(folderAdapter);
        return arrayListImages;
    }
}


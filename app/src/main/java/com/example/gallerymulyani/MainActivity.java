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
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ImagesModel> allFolderListImages = new ArrayList<>();
    public static ArrayList<ImagesModel> allGalleryImages = new ArrayList<>();
    /*
    * belajar parcel biar gak pakai public static */

    GridView gridViewFolder;
    private static final int PERMISSION_READ_STORAGE = 1;

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
                move.putExtra("filter", allFolderListImages.get(position).getFolderPath());
                startActivity(move);

            }
        });
        ArrayList<ImagesModel> folds = new ArrayList<>();

        //permission
        //kalau permission-nya belum di ijinkan
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            //munculin pop up untuk deny/allow permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_READ_STORAGE);
        }
        //kalau permissionnya dah diijinkan
        else {
            folds = folderName_ImagePath();
        }

        FolderAdapter folderAdapter = new FolderAdapter(MainActivity.this, folds);

        //kalau gak ada albumnya(isinya)
        if (folds.isEmpty()){
            Toast.makeText(MainActivity.this, "There is no Album here",Toast.LENGTH_LONG).show();
        }
        //kalau ada albumnya(isinya)
        else{
            gridViewFolder.setAdapter(folderAdapter);
        }

    }

    private ArrayList<ImagesModel> folderName_ImagePath(){
        ArrayList<String> folderPath = new ArrayList<>();

        /* bedanya internal dan external uri
        * internal = penyimpanan yang tidak dipakai bersama(?), jadi kaya yang gak kelihatan oleh user
        * external = penyimpanan yang dipakai bersama
        * sudah nyoba yang internal, hasilnya folder-folder media yang isinya foto bukan dari user, melainnkan dari hp-nya(?)
        *
        * in some cases, 'internal' and 'external' are not mutually exclusive. for example, the 'primary'
        * external storage of your device may be internal if no SD card is present
        * */
        Uri allImagesuri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        Uri allImagesuri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;

        /*
        * uri = request apa yang diambil, misal image, video, audio, dll
        * projection = informasi yang diambil dari request tersebut, misal _id, bucket_display_name, dll
        * cursor = sebagai iterator dari query yang diminta. isinya yang direquest dari uri dan projection*/

        String[] projection = { MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID};

        Cursor cursor = this.getContentResolver().query(allImagesuri, projection, null, null, null);

        try{
            //kalau cursor tidak sama dengan null
            if (cursor != null){
                /* maka cursor Move to the first row.
                * ngecek dari awal*/
                cursor.moveToFirst();
            }
            do{
                ImagesModel folds = new ImagesModel();

                /*getString -> return nilai string dari column yang direquest*/
                String namePhoto = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                String nameAlbum = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                String pathPhoto = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

                /* substring (beginIndex (inclusive), endIndex (exclusive)
                * substring -> motong string
                * disini beginIndex = 0
                * endIndex =  /name album/... .img
                * disini dapetnya -> storage/download/0/DCIM/ */
                String pathAlbum = pathPhoto.substring(0, pathPhoto.lastIndexOf(nameAlbum + "/"));

                /*
                * disini buat dapet path album
                * = path album yang diatas (storage/download/0/DCIM/) + namaAlbum (Camera) + /
                * jadinya storage/download/0/DCIM/Camera/ */
                pathAlbum = pathAlbum + nameAlbum + "/";

                //buat imagesmodel baru
                ImagesModel allImagesModel = new ImagesModel();

                //set path dan nama dari folder dan photonya
                allImagesModel.setFolderPath(pathAlbum);
                allImagesModel.setFolderName(nameAlbum);
                allImagesModel.setPhotoPath(pathPhoto);
                allImagesModel.setPhotoName(namePhoto);

                //tambahkan numofpics (untuk textview foldersize)
                allImagesModel.addNumOfPics();

                //masukkan ke array allGalleryImages
                allGalleryImages.add(allImagesModel);


                /*
                * jika folderPath gak ada pathalbum
                * tidak ada isi path albumnya
                * kan folderpath  itu path-nya album */
                if (!folderPath.contains(pathAlbum)){
                    /*di folderPath tambah pathalbum
                    * disini folderPath diisi pathAlbum */
                    folderPath.add(pathAlbum);

                    //set path dan nama dari folder dan photonya
                    folds.setFolderPath(pathAlbum);
                    folds.setFolderName(nameAlbum);
                    folds.setPhotoPath(pathPhoto);
                    folds.setPhotoName(namePhoto);

                    //tambahkan numofpics (untuk textview foldersize)
                    folds.addNumOfPics();

                    //masukkan ke array allFolderListImages
                    allFolderListImages.add(folds);

                }

                //kalo ada isinya
                else {
                    for (int i =0; i<allFolderListImages.size(); i++){
                        //arraylist ganti allfolderlistimage
                        //kalo albumnya sama
                        if (allFolderListImages.get(i).getFolderPath().equals(pathAlbum)) {
                            //update foto
                            allFolderListImages.get(i).setPhotoPath(pathPhoto);
                            allFolderListImages.get(i).addNumOfPics();
                        }
                    }
                }

            }
            while (cursor.moveToNext()); //berhenti kalo dah gak ada gambar (sudah habis)

            /*
            * Closes the Cursor, releasing all of its resources and making it completely invalid
            * */
            cursor.close();

        }catch (Exception e){
            //menangkap error, tapi tetep jalan
            e.printStackTrace();
        }

        FolderAdapter folderAdapter = new FolderAdapter(getApplicationContext(), allFolderListImages);
        gridViewFolder.setAdapter(folderAdapter);
        return allFolderListImages;

    }

    @Override
    /*
    * ini dipanggil setiap request permission
    * sesudah dialog permission*/
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) { //requestCode yang nge-set Permission_read_storage (dideklarasi di atas = 1)
            case PERMISSION_READ_STORAGE: {
                //looping semua result permission yang ada
                for (int i = 0; i < grantResults.length; i++) {
                    //cek apakah permission sudah di granted/diijinkan (allow)
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        folderName_ImagePath();
                    }
                    //kalau permission belum diijinkan (deny)
                    else {
                        Toast.makeText(MainActivity.this, "The app was not allowed to read or write your storage", Toast.LENGTH_LONG).show();
                    }
                }
            }
            /* misalnya permission yang lain
            *
            * case PERMISSION_READ_CAMERA: {
                ....
              }
              case PERMISSION_xxx: {
                ....
              }
            */

        }
    }

    /*
     * This method is called whenever the user chooses to navigate Up (kembali ke activity sebelumnya)
     * within your application's activity hierarchy from the action bar */
    @Override
    public boolean onSupportNavigateUp() {
        /* Called when the activity has detected the user's press of the back key
        * dipanggil setiap click action bar back */
        onBackPressed();
        return true;
    }
}


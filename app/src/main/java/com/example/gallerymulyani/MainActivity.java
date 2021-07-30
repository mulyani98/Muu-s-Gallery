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

    //allfolderlistimages (array list yang berisi images per album (semua album)
    public static ArrayList<ImagesModel> allFolderListImages = new ArrayList<>();
    //allgaleryimages (arraylist yang isinya semua gambar yang ada)
    public static ArrayList<ImagesModel> allGalleryImages = new ArrayList<>();
    /*
    * belajar parcel biar gak pakai public static
    * pakai public static biar bisa di akses di photo activity.class */

    GridView gridViewFolder;
    private static final int PERMISSION_READ_STORAGE = 1;
    /* permission-nya ada satu */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize gridview by id xml
        gridViewFolder = (GridView) findViewById(R.id.gridViewFolder);

        //kalau gak ada action barnya
        if (getSupportActionBar() != null) {
            //buat action bar baru
            ActionBar backButton = getSupportActionBar();
            //set action bar biar bisa back ke ui sebelumnya
            backButton.setDisplayHomeAsUpEnabled(true);
            //action bar kasih nama
            getSupportActionBar().setTitle(R.string.app_name);
        }

        //buat array list baru
        //untuk check apakah ada isinya
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
            //arraylist baru sama dengan array list yang ada isinya (arraylist peralbum)
            folds = folderName_ImagePath();
        }

        //buat folder adapter
        FolderAdapter folderAdapter = new FolderAdapter(MainActivity.this, folds);

        //untuk ngecek apakah ada isinya (ada albumnya) atau tidak
        //kalau gak ada albumnya(isinya)
        if (folds.isEmpty()){
            //munculin toast, gak ada albumnya
            Toast.makeText(MainActivity.this, "There is no Album here",Toast.LENGTH_LONG).show();
        }
        //kalau ada albumnya(isinya)
        else{
            //set adapternya jadi folder adapater (mainactivity kan isinya album-album)
            gridViewFolder.setAdapter(folderAdapter);
        }


        //kalau album di click
        gridViewFolder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //masuk ke photo activity
                Intent move = new Intent(getApplicationContext(), PhotoActivity.class);

                //passing folder path ke photo activity
                move.putExtra("filter", allFolderListImages.get(position).getFolderPath());
                startActivity(move);

            }
        });

    }

    //digunakan untuk mengambil gambar jadiin perfolder
    private ArrayList<ImagesModel> folderName_ImagePath(){

        /* bedanya internal dan external uri
        * internal = penyimpanan yang tidak dipakai bersama(?), jadi kaya yang gak kelihatan oleh user
        * external = penyimpanan yang dipakai bersama
        * sudah nyoba yang internal, hasilnya folder-folder media yang isinya foto bukan dari user, melainnkan dari hp-nya(?)
        *
        * in some cases, 'internal' and 'external' are not mutually exclusive. for example, the 'primary'
        * external storage of your device may be internal if no SD card is present
        * */
        //used to request item that we need
        Uri allImagesuri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        Uri allImagesuri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;

        /*
        * uri = request apa yang diambil, misal image, video, audio, dll
        * projection = informasi yang diambil dari request tersebut, misal _id, bucket_display_name, dll
        * cursor = sebagai iterator dari query yang diminta. isinya yang direquest dari uri dan projection*/

        //contains what info that will be taken from the request
        String[] projection = { MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        //used to query all the requested item
        Cursor cursor = this.getContentResolver().query(allImagesuri, projection, null, null, null);

        try{
            //kalau cursor tidak sama dengan null
            //if cursor is not equal to null
            if (cursor != null){
                /* maka cursor Move to the first row.
                * ngecek dari awal*/
                //move cursor to the first row
                cursor.moveToFirst();
            }
            do{

                /* getString -> return nilai string dari column yang direquest
                * getColumnIndexOrThrow -> Returns the zero-based index for the given column name,
                * or throws if the column doesn't exist */
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
                //berisi semua photo
                allGalleryImages.add(allImagesModel);

                //arraylist string baru buat cek ada albumnya gak
                ArrayList<String> folderPath = new ArrayList<>();
                /*
                * jika folderPath gak ada pathalbum
                * tidak ada isi path albumnya
                * kan folderpath  itu path-nya album
                * folderpath doesn't contains path album*/
                if (!folderPath.contains(pathAlbum)){
                    ImagesModel folds = new ImagesModel();

                    /*di folderPath tambah pathalbum
                    * disini folderPath diisi pathAlbum
                    * di add biar jadi masuk album (jadi satu peralbum), kalau gak di add nanti  isinya album jadi album sendiri (per satu nama album jadi banyak album sesuai dengan jumlah fotonya */
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
                    //looping semua isi allfolderlistimages
                    for (int i =0; i<allFolderListImages.size(); i++){
                        //kalo album pathnya allfolderlistimages sama dengan pathalbum
                        if (allFolderListImages.get(i).getFolderPath().equals(pathAlbum)) {
                            //update foto (path photo)
//                            allFolderListImages.get(i).setPhotoPath(pathPhoto);
                            //update textview size album
                            allFolderListImages.get(i).addNumOfPics();
                        }
                    }
                }

            }
            while (cursor.moveToNext()); //move to next cursor
            //berhenti kalo dah gak ada gambar (sudah habis)

            /*
            * Closes the Cursor, releasing all of its resources and making it completely invalid
            * */
            cursor.close();

        }catch (Exception e){
            //menangkap error, tapi tetep jalan
            e.printStackTrace();
        }

        FolderAdapter myFolderAdapter = new FolderAdapter(getApplicationContext(), allFolderListImages);
        gridViewFolder.setAdapter(myFolderAdapter);

        return allFolderListImages;

    }

    @Override
    /*
    * ini dipanggil setiap request permission
    * sesudah dialog permission*/
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //looping semua requestcode
        switch (requestCode) { //requestCode yang nge-set Permission_read_storage (dideklarasi di atas = 1)
            //case yang permission read storage
            case PERMISSION_READ_STORAGE: {
                //looping semua result permission yang ada
                for (int i = 0; i < grantResults.length; i++) {
                    //cek apakah permission sudah di granted/diijinkan (allow)
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        //kalau diijinkan, masuk arraylist folderName_ImagePath (arraylist perfolder)
                        folderName_ImagePath();
                    }
                    //kalau permission belum diijinkan (deny)
                    else {
                        //munculin toast
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


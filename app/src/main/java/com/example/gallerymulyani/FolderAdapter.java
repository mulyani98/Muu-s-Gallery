package com.example.gallerymulyani;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

    public class FolderAdapter extends ArrayAdapter<ImagesModel>{

    Context myContext;
    ViewHolder viewHolder;
    ArrayList<ImagesModel> myArrayListFolder = new ArrayList<>(); //penamaan jangan sama

    //object untuk menampung view yang berisi textview foldername, foldersize dan imageview folder
    //to collect view that contains tv folder name, size and iv folder
    private class ViewHolder {
        TextView textView_FolderName, textView_FolderSize;
        ImageView imageViewFolder;
    }

    /* context -> merupakan interface untuk global information mengenai application environment (lingkungan aplikasi)
    * ini constructor (special method that is used to initialize objects)
    * untuk menginisialisasi object */
    public FolderAdapter(Context context, ArrayList<ImagesModel> arrayListFolders){

        /* super => Refers to superclass (parent) objects
        * super sebagai default constructor
        * disini superclassnya arrayadapter<imagesmodel> */
        super(context, R.layout.adapter_folder, arrayListFolders);
        this.myContext = context;
        this.myArrayListFolder = arrayListFolders; //
    }

    //mendapatkan/return View yang akan ditampilkan
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //kalau convertview sama dengan null
        if (convertView == null){
            //create new ViewHolder
            viewHolder = new ViewHolder();

            /* convertView diset viewnya jadi layout adapter_folder, parentnya: gridview(?)
             * layoutinflater => untuk masang view ke suatu activity, nempelin xml-nya
             * layoutinflater.from => memperoleh layoutinflater dari context (disini adapter_folder) */
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);


            //inisialisasi ui komponen by id xml
            viewHolder.textView_FolderName = (TextView) convertView.findViewById(R.id.textViewFolderName);
            viewHolder.textView_FolderSize = (TextView) convertView.findViewById(R.id.textViewFolderSize);
            viewHolder.imageViewFolder = (ImageView) convertView.findViewById(R.id.coverAlbum);

            /* setTag digunakan sebagai referensi ke object yang merujuk pada beberapa bagian dari view
            * (yang ditampilkan di gridview) instead pakai findviewbyid
            * disini converviewnya null, jadi di settag isinya viewholder
            * Tag used to store data within a view without resorting to another data structure
            * menyimpan data dalam tampilan  ---- tanpa menggunakan struktur data lain
            * menyimpan viewHolder kedalam convertView */
            convertView.setTag(viewHolder);


            /*
            * bitmapfactory => membuat object bitmap dari
            * decodefile => decode pathfile ke bitmap
            * disini bitmap dibuat dari pathPhoto yang ada di arrayListFolder */
            Bitmap mybitmap = BitmapFactory.decodeFile(myArrayListFolder.get(position).getPhotoPath());

            /*
            * createScaledBitmap => mengatur scalebitmap (sourcenya, width dan height-nya, sama filter-nya
            * bitmapThumbnal => bitmap diatur scale-nya, sourcenya mybitmap, width 300, height 300, filternya false
            * filter -> true if the source should be filtered.*/
            Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(mybitmap, 300, 300, false);

            /* imageview di set imagebitmapnya, sebagai konten/isi dari imageview tersebut
            * disini diisi bitmap yang sudah diatur scalenya, yaitu pakai bitmapThumbnail  */
            viewHolder.imageViewFolder.setImageBitmap(bitmapThumbnail);

        }
        else {
            /* kalau convertviewnya gak null, viewholder-nya sama dengan convertview
            yang udah ada tersebut (pakai get tag)
            disini convertview gak null, jadi viewholdernya diisi convertview yang udah ada */
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*set text dari folder name dan folder size*/
        viewHolder.textView_FolderName.setText(myArrayListFolder.get(position).getFolderName());

        /* ini set text kan mintanya string, karena foldersize pakai numberofpics itu int,
        * jadi pakai stringvalueof (mengembalikan nilai menjadi string)
        * disini juga bisa pakai +" ", tetapi readability codenya tidak bagus */
//        viewHolder.textView_FolderSize.setText(arrayListFolder.get(position).getNumberOfPics()+"");
        viewHolder.textView_FolderSize.setText(String.valueOf(myArrayListFolder.get(position).getNumberOfPics()));

        return convertView;
    }

}

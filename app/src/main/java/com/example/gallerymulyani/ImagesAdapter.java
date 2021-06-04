package com.example.gallerymulyani;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ImagesAdapter extends ArrayAdapter<ImagesModel> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<ImagesModel> arrayListImages = new ArrayList<>();

    //object untuk menampung view yang berisi textview folder name & size, dan imageviewImages
    private static class ViewHolder{
        TextView textViewFolderName, textViewFolderSize;
        ImageView imageViewImages;
    }

    /* context -> merupakan interface untuk global information mengenai application environment (lingkungan aplikasi)
     * ini constructor (special method that is used to initialize objects) */
    public ImagesAdapter(Context context, ArrayList<ImagesModel> arrayListImages){

        /* super => Refers to superclass (parent) objects
         * super sebagai default constructor
         * disini superclassnya arrayadapter<imagesmodel> */
        super(context, R.layout.adapter_folder, arrayListImages);
        this.context = context;
        this.arrayListImages = arrayListImages;
    }

    //mendapatkan/return View yang akan ditampilkan
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //kalau convertview sama dengan null
        if (convertView == null){
            viewHolder = new ViewHolder();

            /* convertView diset viewnya jadi layout adapter_folder, parentnya: gridview(?)
            * layoutinflater => untuk masang view ke suatu activity, nempelin xml-nya
            * layoutinflater.from => memperoleh layoutinflater dari context (disini adapter_folder) */
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);

            //inisialisasi ui komponen by id xml
            viewHolder.textViewFolderName = (TextView) convertView.findViewById(R.id.textViewFolderName);
            viewHolder.textViewFolderSize = (TextView) convertView.findViewById(R.id.textViewFolderSize);
            viewHolder.imageViewImages = (ImageView) convertView.findViewById(R.id.coverAlbum);

            /* setTag digunakan sebagai referensi ke object yang merujuk pada beberapa bagian dari view
             * (yang ditampilkan di gridview) instead pakai findviewbyid
             * disini converviewnya null, jadi di settag isinya viewholder*/
            convertView.setTag(viewHolder);

            /*
             * bitmapfactory => membuat object bitmap dari
             * decodefile => decode pathfile ke bitmap
             * disini bitmap dibuat dari pathPhoto yang ada di arrayListImages */
            Bitmap bitmap = BitmapFactory.decodeFile(arrayListImages.get(position).getPhotoPath());

            /*
             * createScaledBitmap => mengatur scalebitmap (sourcenya, width dan height-nya, sama filter-nya
             * bitmapThumbnal => bitmap diatur scale-nya, sourcenya mybitmap, width 300, height 300, filternya false */
            Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(bitmap, 300, 300, false);

            /* imageview di set imagebitmapnya, sebagai konten/isi dari imageview tersebut
             * disini diisi bitmap yang sudah diatur scalenya, yaitu pakai bitmapThumbnail  */
            viewHolder.imageViewImages.setImageBitmap(bitmapThumbnail);
        }
        else {
            /* kalau convertviewnya gak null, viewholder-nya sama dengan convertview
            yang udah ada tersebut (pakai get tag)
            disini convertview gak null, jadi viewholdernya diisi convertview yang udah ada*/
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*
        * textview foldername dan foldersize di set menjadi invisible (tidak kelihatan text-nya)*/
        viewHolder.textViewFolderName.setVisibility(View.INVISIBLE);
        viewHolder.textViewFolderSize.setVisibility(View.INVISIBLE);

        return  convertView;

    }
}

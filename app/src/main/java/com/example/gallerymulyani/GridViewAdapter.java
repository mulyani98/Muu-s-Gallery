package com.example.gallerymulyani;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<ImagesModel> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<ImagesModel> arrayListImages = new ArrayList<>();
    String folderName;

    ArrayList<String> imagesPath = new ArrayList<>();

    int pos;


    private static class ViewHolder{
        TextView textViewFolderName, textViewFolderSize;
        ImageView imageViewImages;
    }

    public GridViewAdapter(Context context, ArrayList<ImagesModel> arrayListImages,int pos){
//    public GridViewAdapter(Context context, ArrayList<ImagesModel> arrayListImages, String folderName){
    //asli
//        public GridViewAdapter(Context context, ArrayList<ImagesModel> arrayListImages){
        super(context, R.layout.adapter_folder, arrayListImages);
        this.context = context;
        this.arrayListImages = arrayListImages;
//        this.folderName = folderName;
        this.pos = pos;
    }

//    @Override
//    public int getCount() {
//        Log.e("ADAPTER LIST SIZE", arrayListImages.get(pos).getArrayList_ImagePath().size() + "");
//        return arrayListImages.get(pos).getArrayList_ImagePath().size();
//    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    //try again
    @Override
    public int getViewTypeCount() {
        if (arrayListImages.get(pos).getArrayList_ImagePath().size() > 0){
            return arrayListImages.get(pos).getArrayList_ImagePath().size();
        }
        else {
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//        View view;
//        view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);
//
//        TextView textViewFolderName = (TextView) convertView.findViewById(R.id.textViewFolderName);
//        TextView textViewFolderSize = (TextView) convertView.findViewById(R.id.textViewFolderSize);
//        ImageView imageViewImages = (ImageView) convertView.findViewById(R.id.imageViewPhoto);
//
//        textViewFolderName.setVisibility(View.INVISIBLE);
//        textViewFolderSize.setVisibility(View.INVISIBLE);
//        imageViewImages.setImageBitmap();
//
//        convertView.setTag(viewHolder);

        if (convertView == null){
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);

            viewHolder.textViewFolderName = (TextView) convertView.findViewById(R.id.textViewFolderName);
            viewHolder.textViewFolderSize = (TextView) convertView.findViewById(R.id.textViewFolderSize);
            viewHolder.imageViewImages = (ImageView) convertView.findViewById(R.id.imageViewPhoto);

//            Bitmap bitmap = BitmapFactory.decodeFile(arrayListImages.get(position).getFirstPic());

            //try again
            Bitmap bitmap = BitmapFactory.decodeFile(arrayListImages.get(position).getArrayList_ImagePath().get(position));
            Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(bitmap, 150, 150, false);

            //original
//            Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(bitmap, 300, 300, false);
            viewHolder.imageViewImages.setImageBitmap(bitmapThumbnail);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewFolderName.setVisibility(View.INVISIBLE);
        viewHolder.textViewFolderSize.setVisibility(View.INVISIBLE);

        return  convertView;

        //second
//        viewHolder = new ViewHolder();
//
//        View childView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);
//        viewHolder.textViewFolderName = (TextView) convertView.findViewById(R.id.textViewFolderName);
//        viewHolder.textViewFolderSize = (TextView) convertView.findViewById(R.id.textViewFolderSize);
//        viewHolder.imageViewImages = (ImageView) convertView.findViewById(R.id.imageViewPhoto);
//
//        Bitmap bitmap = BitmapFactory.decodeFile(imagesPath.get(position));
//        Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(bitmap, 300, 300, false);
//
//        viewHolder.imageViewImages.setImageBitmap(bitmapThumbnail);
////        convertView.setTag(viewHolder);


//        return childView;
//


    }
}

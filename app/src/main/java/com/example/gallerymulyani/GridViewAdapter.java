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

public class GridViewAdapter extends ArrayAdapter<ImagesModel> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<ImagesModel> arrayListImages = new ArrayList<>();

    private static class ViewHolder{
        TextView textViewFolderName, textViewFolderSize;
        ImageView imageViewImages;
    }

    public GridViewAdapter(Context context, ArrayList<ImagesModel> arrayListImages){
        super(context, R.layout.adapter_folder, arrayListImages);
        this.context = context;
        this.arrayListImages = arrayListImages;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);

            viewHolder.textViewFolderName = (TextView) convertView.findViewById(R.id.textViewFolderName);
            viewHolder.textViewFolderSize = (TextView) convertView.findViewById(R.id.textViewFolderSize);
            viewHolder.imageViewImages = (ImageView) convertView.findViewById(R.id.coverAlbum);

            Bitmap bitmap = BitmapFactory.decodeFile(arrayListImages.get(position).getPhotoPath());
            Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(bitmap, 300, 300, false);
            viewHolder.imageViewImages.setImageBitmap(bitmapThumbnail);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*
        * textview foldername dan foldersize di set menjadi invisible (tidak kelihatan text-nya)*/
        viewHolder.textViewFolderName.setVisibility(View.INVISIBLE);
        viewHolder.textViewFolderSize.setVisibility(View.INVISIBLE);

        return  convertView;

    }
}

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

    //original
    Context context;
    ViewHolder viewHolder;
    ArrayList<ImagesModel> arrayListImages = new ArrayList<>();
    ArrayList<String> imagesPath = new ArrayList<>();

//    //trial and error
//    ArrayList<ImagesModel> folders = new ArrayList<>();
//    Context contextFolder;


    private static class ViewHolder {
        TextView textView_FolderName, textView_FolderSize;
        ImageView imageViewPhoto;
    }

    public FolderAdapter(Context contextFolder, ArrayList<ImagesModel> arrayListFolders){
        //original
        super(contextFolder, R.layout.adapter_folder, arrayListFolders);
        this.context = context;
        this.arrayListImages = arrayListFolders;

        //trial and error
////        super(contextFolder, R.layout.picture_folder_item, arrayListFolders );
//        this.folders = arrayListFolders;
//        this.contextFolder = contextFolder;
    }

    //original
    @Override
    public int getCount() {
        Log.e("FOLDERADAPTER LIST SIZE", arrayListImages.size() +"");
        return arrayListImages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (arrayListImages.size() > 0){
            return arrayListImages.size();
        }
        else
            return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);

            viewHolder.textView_FolderName = (TextView) convertView.findViewById(R.id.textViewFolderName);
            viewHolder.textView_FolderSize = (TextView) convertView.findViewById(R.id.textViewFolderSize);
            viewHolder.imageViewPhoto = (ImageView) convertView.findViewById(R.id.imageViewPhoto);

            convertView.setTag(viewHolder);

            Bitmap bitmap = BitmapFactory.decodeFile(arrayListImages.get(position).getArrayList_ImagePath().get(0));
            Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(bitmap, 300, 300, false);
            viewHolder.imageViewPhoto.setImageBitmap(bitmapThumbnail);

//            Glide.with(context).load("file://" + al_menu.get(position).getAl_imagepath().get(0))
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
//                    .into(viewHolder.iv_image);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView_FolderName.setText(arrayListImages.get(position).getFolderName());
//        viewHolder.textView_FolderSize.setText(arrayListImages.get(position).getNumberOfPics() + "");

//        viewHolder.textView_FolderSize.setText(arrayListImages.get(position).getStringImagePath());

        //try again
        viewHolder.textView_FolderSize.setText(arrayListImages.get(position).getArrayList_ImagePath().size()+"");

        return convertView;

        //second
//        viewHolder = new ViewHolder();
////        convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);
//
//        View childView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);
//        viewHolder.textView_FolderName = (TextView) convertView.findViewById(R.id.textViewFolderName);
//        viewHolder.textView_FolderSize = (TextView) convertView.findViewById(R.id.textViewFolderSize);
//        viewHolder.imageViewPhoto = (ImageView) convertView.findViewById(R.id.imageViewPhoto);
//
//            Bitmap bitmap = BitmapFactory.decodeFile(imagesPath.get(position));
//            Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(bitmap, 300, 300, false);
//
////        convertView.setTag(viewHolder);
//
//        viewHolder.imageViewPhoto.setImageBitmap(bitmapThumbnail);

        //second
//        return childView;

    }

    //trial error
//    public class FolderHolder extends RecyclerView.ViewHolder{
//        ImageView folderPic;
//        TextView folderName;
//        TextView folderSize;
//        CardView folderCard;
//
//        public FolderHolder(View itemView){
//            super(itemView);
//            folderPic = itemView.findViewById(R.id.folderPic);
//            folderName = itemView.findViewById(R.id.folderName);
//            folderSize = itemView.findViewById(R.id.folderSize);
//            folderCard = itemView.findViewById(R.id.folderCard);
//        }
//    }
//
//    public FolderHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View cell = inflater.inflate(R.layout.picture_folder_item, parent, false);
//        return new FolderHolder(cell);
//    }
//
//    public void onBindViewHolder(FolderHolder holder, int position){
//        final ImagesModel folder = folders.get(position);
//
//
//        String text = ""+folder.getFolderName();
//        String folderSizeString = ""+folder.getNumberOfPics()+" Media";
//        holder.folderSize.setText(folderSizeString);
//        holder.folderName.setText(text);
//
//        holder.folderPic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//    }
//
//    public int getItemCount(){
//        return folders.size();
//    }

        //tried at home

}

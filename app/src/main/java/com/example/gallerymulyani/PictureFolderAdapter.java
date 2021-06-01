//package com.example.gallerymulyani;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//
//public class PictureFolderAdapter extends ArrayAdapter<ImagesModel> {
//
//    private ArrayList<ImagesModel> folders;
//    private Context contextFolder;
//
//    public PictureFolderAdapter(ArrayList<ImagesModel> folders, Context contextFolder){
//        super(contextFolder, R.layout.picture_folder_item, folders);
//        this.folders = folders;
//        this.contextFolder = contextFolder;
//    }
//
//    public FolderHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View cell = inflater.inflate(R.layout.picture_folder_item, parent, false);
//        return new FolderHolder(cell);
//    }
//
//    public void onBindViewHolder(@NonNull FolderHolder holder, int position) {
//        final ImagesModel folder = folders.get(position);
//
//        Bitmap bitmap = BitmapFactory.decodeFile(folder.getFirstPic());
//        Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(bitmap, 300, 300, false);
//        holder.folderPic.setImageBitmap(bitmapThumbnail);
//
//        //setting the number of images
//        String text = ""+folder.getFolderName();
//        String folderSizeString=""+folder.getNumberOfPics()+" Media";
//        holder.folderSize.setText(folderSizeString);
//        holder.folderName.setText(text);
//
//        holder.folderPic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                listenToClick.onPicClicked(folder.getPath(),folder.getFolderName());
//            }
//        });
//
//    }
//
//    public int getItemCount() {
//        return folders.size();
//    }
//
//    public class FolderHolder{
//        ImageView folderPic;
//        TextView folderName;
//        TextView folderSize;
//        CardView folderCard;
//
//        public FolderHolder(View itemView){
//            super();
//            folderPic = itemView.findViewById(R.id.folderPic);
//            folderName = itemView.findViewById(R.id.folderName);
//            folderSize = itemView.findViewById(R.id.folderSize);
//            folderCard = itemView.findViewById(R.id.folderCard);
//
//        }
//    }
//}

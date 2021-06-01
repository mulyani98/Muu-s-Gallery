package com.example.gallerymulyani;

import java.util.ArrayList;

public class ImagesModel {

    private String folderName;

    //try again
    ArrayList<String> arrayList_ImagePath = new ArrayList<String>();

    private String stringImagePath;
    private String firstPic;
    private int numberOfPics = 0;
    private String albumPics;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String stringFolder) {
        this.folderName = stringFolder;
    }

    //try again
    public ArrayList<String> getArrayList_ImagePath() {
        return arrayList_ImagePath;
    }

    public void setArrayList_ImagePath(ArrayList<String> arrayList_ImagePath) {
        this.arrayList_ImagePath = arrayList_ImagePath;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public int getNumberOfPics() {
        return numberOfPics;
    }

    public void setNumberOfPics(int numberOfPics) {
        this.numberOfPics = numberOfPics;
    }

    public String getStringImagePath() {
        return stringImagePath;
    }

    public void setStringImagePath(String stringImagePath) {
        this.stringImagePath = stringImagePath;
    }

    public String getAlbumPics() {
        return albumPics;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics;
    }

    public void addPics(){
        this.numberOfPics++;
    }

    public void appendImageList(String path){
        this.arrayList_ImagePath.add(path);
    }

    //    private String path;
//    private String folderName;
//    private int numberOfPics = 0;
//    private String firstPic;
//
//    public ImagesModel(){
//
//    }
//
//    public ImagesModel(String path, String folderName){
//
//        this.path = path;
//        this.folderName = folderName;
//
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public String getFolderName() {
//        return folderName;
//    }
//
//    public void setFolderName(String folderName) {
//        this.folderName = folderName;
//    }
//
//    public int getNumberOfPics() {
//        return numberOfPics;
//    }
//
//    public void setNumberOfPics(int numberOfPics) {
//        this.numberOfPics = numberOfPics;
//    }
//
}

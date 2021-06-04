package com.example.gallerymulyani;

import java.util.ArrayList;

public class ImagesModel {

    private String folderName;
    private String photoName;
    private String folderPath;
    private String photoPath;
    private int numberOfPics = 0;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String stringFolder) {
        this.folderName = stringFolder;
    }

    public void setPhotoPath(String firstPic) {
        this.photoPath = firstPic;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public int getNumberOfPics() {
        return numberOfPics;
    }

    public void setNumberOfPics(int numberOfPics) {
        this.numberOfPics = numberOfPics;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String stringImagePath) {
        this.folderPath = stringImagePath;
    }

    //menambahan numberOfPics (yang ditampilkan di text view FolderSize
    public void addNumOfPics(){
        this.numberOfPics++;
    }

    public String getPhotoName() {
        return photoName;
    }

    //digunakan untuk title action bar pas show image view
    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}

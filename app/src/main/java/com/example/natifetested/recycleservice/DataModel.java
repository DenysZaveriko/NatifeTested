package com.example.natifetested.recycleservice;

public class DataModel {
    private String imageURL;

    public DataModel(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

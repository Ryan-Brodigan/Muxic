package com.example.brian.muxic;

import java.util.ArrayList;

public class Artist {
    private String name;
    private String lastFMUrl;
    private Integer playcount;
    private Integer listeners;
    private String imageURL;
    private ArrayList<String> imageURL2;

    public Artist(String name, String lastFMUrl, Integer playcount, Integer listeners, String imageURL,ArrayList<String> imageURL2){
        this.name = name;
        this.lastFMUrl = lastFMUrl;
        this.playcount = playcount;
        this.listeners = listeners;
        this.imageURL = imageURL;
        this.imageURL2 = imageURL2;
    }

    public String getName(){
        return this.name;
    }

    public String getLastFMUrl() { return this.lastFMUrl; }

    public Integer getPlaycount(){
        return this.playcount;
    }

    public Integer getListeners(){
        return this.listeners;
    }

    public String getImageURL(){
        return this.imageURL;
    }

    public ArrayList<String> getImageURL2(){
        return this.imageURL2;
    }
}

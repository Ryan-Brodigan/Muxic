package com.example.brian.muxic;

import java.util.ArrayList;

public class Artist {
    private String name;
    private String lastFMUrl;
    private Integer playcount;
    private Integer listeners;
    private ArrayList<String> imageURL;

    public Artist(String name, String lastFMUrl, Integer playcount, Integer listeners, ArrayList<String> imageURL){
        this.name = name;
        this.lastFMUrl = lastFMUrl;
        this.playcount = playcount;
        this.listeners = listeners;
        this.imageURL = imageURL;
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

    public ArrayList<String> getImageURL(){
        return this.imageURL;
    }

    public String toString(){
        return "Name: " + this.name + ", LastFMUrl: " + this.lastFMUrl + ", Listeners: " + this.listeners + ", Playcount: " + this.playcount + ", imageURL: " + this.imageURL.toString();
    }
}

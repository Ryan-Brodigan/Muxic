package com.example.brian.muxic;
import java.util.ArrayList;

public class Artist {
    private String name;
    private Integer playcount;
    private Integer listeners;
    private ArrayList<String> imageURL;

    public Artist(String name, Integer playcount, Integer listeners, ArrayList<String> imageURL){
        this.name = name;
        this.playcount = playcount;
        this.listeners = listeners;
        this.imageURL = imageURL;
    }

    public String getName(){
        return this.name;
    }

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
        return "Name: " + this.name + ", Listeners: " + this.listeners + ", Playcount: " + this.playcount + ", imageURL: " + this.imageURL.toString();
    }
}

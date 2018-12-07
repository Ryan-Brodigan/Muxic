package com.example.brian.muxic;

import java.util.ArrayList;

public class Track {
    private String name;
    private String lastFMUrl;
    private Integer playcount;
    private String artistName;
    private ArrayList<String> imageUrl;
    private String imageURL2;

    public Track(String name, String lastFMUrl, Integer playcount, String artistName, ArrayList<String> imageUrl, String imageURL2){
        this.name = name;
        this.lastFMUrl = lastFMUrl;
        this.playcount = playcount;
        this.artistName = artistName;
        this.imageUrl = imageUrl;
        this.imageURL2 = imageURL2;
    }

    public String getName(){
        return this.name;
    }

    public String getLastFMUrl(){
        return this.lastFMUrl;
    }

    public Integer getPlaycount(){
        return this.playcount;
    }

    public String getArtistName(){
        return this.artistName;
    }

    public ArrayList<String> getImageUrl(){
        return this.imageUrl;
    }

    public String getImageURL2(){
        return this.imageURL2;
    }
}

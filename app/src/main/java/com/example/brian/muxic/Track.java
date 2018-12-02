package com.example.brian.muxic;

import java.util.ArrayList;

public class Track {
    private String name;
    private String lastFMUrl;
    private Integer playcount;
    private String artistName;
    private ArrayList<String> imageUrl;

    public Track(String name, String lastFMUrl, Integer playcount, String artistName, ArrayList<String> imageUrl){
        this.name = name;
        this.lastFMUrl = lastFMUrl;
        this.playcount = playcount;
        this.artistName = artistName;
        this.imageUrl = imageUrl;
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

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Track Name: ").append(name)
                .append("\n")
                .append("Play Count: ").append(playcount)
                .append("\n")
                .append("Artist Name: ").append(artistName)
                .append("\n")
                .append("Image: ").append(imageUrl).toString();
    }

}

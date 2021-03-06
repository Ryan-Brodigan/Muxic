package com.example.brian.muxic;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity(tableName = "saved_tracks")
public class Track {

    @ColumnInfo(name = "name")
    private String name;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "lastFMUrl")
    private String lastFMUrl;

    @ColumnInfo(name = "playcount")
    private Integer playcount;

    @ColumnInfo(name = "artistName")
    private String artistName;

    @ColumnInfo(name = "imageUrls")
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

    public void setName(String name) {this.name = name;}

    public void setLastFMUrl(String lastFMUrl) {this.lastFMUrl = lastFMUrl;}

    public void setPlaycount(Integer playcount) {this.playcount = playcount;}

    public void setArtistName(String artistName) {this.artistName = artistName;}

    public void setImageUrl(ArrayList<String> imageUrl) {this.imageUrl = imageUrl;}

    public void setImageUrl2(String imageUrl2) {this.imageURL2 = imageUrl2;}

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

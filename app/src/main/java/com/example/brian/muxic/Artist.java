package com.example.brian.muxic;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity(tableName = "saved_artists")
public class Artist {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID")
    private String ID;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "lastFMUrl")
    private String lastFMUrl;

    @ColumnInfo(name = "playcount")
    private Integer playcount;

    @ColumnInfo(name = "listeners")
    private Integer listeners;

    @ColumnInfo(name = "imageUrls")
    private ArrayList<String> imageURL;

    public Artist(String ID, String name, String lastFMUrl, Integer playcount, Integer listeners, ArrayList<String> imageURL){
        this.ID = ID;
        this.name = name;
        this.lastFMUrl = lastFMUrl;
        this.playcount = playcount;
        this.listeners = listeners;
        this.imageURL = imageURL;
    }

    public String getID() {return this.ID;}

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

    public void setID(String ID) {this.ID = ID;}

    public void setName(String name) {this.name = name;}

    public void setLastFMUrl(String lastFMUrl) {this.lastFMUrl = lastFMUrl;}

    public void setPlaycount(Integer playcount) {this.playcount = playcount;}

    public void setListeners(Integer listeners) {this.listeners = listeners;}

    public void setImageURL(ArrayList<String> imageURL) {this.imageURL = imageURL;}

    public String toString(){
        return "ID: " + this.ID + ", Name: " + this.name + ", LastFMUrl: " + this.lastFMUrl + ", Listeners: " + this.listeners + ", Playcount: " + this.playcount + ", imageURL: " + this.imageURL.toString();
    }
}

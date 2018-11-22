package com.example.brian.muxic;

public class Track {
    private String name;
    private Integer playcount;
    private String artistName;
    private String imageUrl;

    public Track(String name, Integer playcount, String artistName, String imageUrl){
        this.name = name;
        this.playcount = playcount;
        this.artistName = artistName;
        this.imageUrl = imageUrl;
    }

    public String getName(){
        return this.name;
    }

    public Integer getPlaycount(){
        return this.playcount;
    }

    public String getArtistName(){
        return this.artistName;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(name)
                .append(playcount)
                .append(artistName)
                .append(imageUrl).toString();
    }

}

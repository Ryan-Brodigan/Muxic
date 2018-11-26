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
                .append("Track Name: ").append(name)
                .append("\n")
                .append("Play Count: ").append(playcount)
                .append("\n")
                .append("Artist Name: ").append(artistName)
                .append("\n")
                .append("Image: ").append(imageUrl).toString();
    }

}

package com.example.brian.muxic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayTopTracks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_tracks);
        RetrieveTopTracksTask t = new RetrieveTopTracksTask(DisplayTopTracks.this);
        t.libraryList = new ArrayList<>();
        t.listView = findViewById(R.id.trackList);
        t.execute();
    }

}


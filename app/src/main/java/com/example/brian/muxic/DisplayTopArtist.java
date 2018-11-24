package com.example.brian.muxic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayTopArtist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_artists);
        RetrieveTopArtistsTask t = new RetrieveTopArtistsTask(DisplayTopArtist.this);
        t.artistList = new ArrayList<>();
        t.listView = findViewById(R.id.list);
        t.execute();
    }

}

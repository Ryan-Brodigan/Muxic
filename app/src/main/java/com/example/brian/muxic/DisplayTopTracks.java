package com.example.brian.muxic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class DisplayTopTracks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_tracks);
        RetrieveTopTracksTask t = new RetrieveTopTracksTask(DisplayTopTracks.this);
        t.libraryList = new ArrayList<>();
        t.listView = findViewById(R.id.trackList);
        t.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track track = (Track) parent.getItemAtPosition(position);
                Intent i = new Intent(DisplayTopTracks.this, DetailedTrackViewActivity.class);
                i.putExtra("TrackName", track.getName());
                i.putExtra("ArtistName", track.getArtistName());
                i.putExtra("TrackPlaycount", track.getPlaycount());
                i.putExtra("TrackUrl", track.getLastFMUrl());
                i.putExtra("TrackImage", track.getImageUrl().get(2));
                startActivity(i);
            }
        });
        t.execute();
    }

}


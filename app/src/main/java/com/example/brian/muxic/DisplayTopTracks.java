package com.example.brian.muxic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_tracks_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.action_top_artists:
                Intent aActivity = new Intent(DisplayTopTracks.this, DisplayTopArtist.class);
                startActivity(aActivity);
                return true;
            case R.id.action_favourite_artists:
                Intent faActivity = new Intent(DisplayTopTracks.this, DisplayFavouriteArtistsActivity.class);
                startActivity(faActivity);
                return true;
            case R.id.action_favourite_tracks:
                Intent ftActivity = new Intent(DisplayTopTracks.this, DisplayFavouriteTracksActivity.class);
                startActivity(ftActivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


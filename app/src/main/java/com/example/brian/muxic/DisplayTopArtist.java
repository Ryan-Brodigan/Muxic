package com.example.brian.muxic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;

public class DisplayTopArtist extends AppCompatActivity {

    //ImageView ivBasicImage = (ImageView) findViewById(R.id.thumbnails);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_artists);
        RetrieveTopArtistsTask t = new RetrieveTopArtistsTask(DisplayTopArtist.this);
        t.artistList = new ArrayList<>();
        t.listView = findViewById(R.id.list);
        t.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artist a = (Artist) parent.getItemAtPosition(position);
                Intent i = new Intent(DisplayTopArtist.this, DetailedArtistViewActivity.class);
                i.putExtra("ArtistName", a.getName());
                i.putExtra("ArtistUrl", a.getLastFMUrl());
                i.putExtra("ArtistListeners", a.getListeners());
                i.putExtra("ArtistPlaycount", a.getPlaycount());
                i.putExtra("ArtistImg",a.getImageURL());
                i.putExtra("ArtistImage", a.getImageURL2().get(2));
                startActivity(i);
            }
        });
        t.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_tracks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_top_tracks:
                Intent tActivity = new Intent(DisplayTopArtist.this, DisplayTopTracks.class);
                startActivity(tActivity);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

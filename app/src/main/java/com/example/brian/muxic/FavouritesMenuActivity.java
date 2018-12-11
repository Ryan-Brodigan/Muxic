package com.example.brian.muxic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class FavouritesMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_menu);

        final Button viewFavouriteArtistsButton = findViewById(R.id.favourite_artists_button);
        final Button viewFavouriteTracksButton = findViewById(R.id.favourite_tracks_button);

        viewFavouriteArtistsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                favouriteArtists();
            }
        });

        viewFavouriteTracksButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                favouriteTracks();
            }
        });
    }

    public void favouriteArtists(){
        Intent i = new Intent(FavouritesMenuActivity.this, DisplayFavouriteArtistsActivity.class);
        startActivity(i);
    }

    public void favouriteTracks(){
        Intent i = new Intent(FavouritesMenuActivity.this, DisplayFavouriteTracksActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.favourite_menu_view, menu);
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
                Intent aActivity = new Intent(FavouritesMenuActivity.this, DisplayTopArtist.class);
                startActivity(aActivity);
                return true;
            case R.id.action_top_tracks:
                Intent tActivity = new Intent(FavouritesMenuActivity.this, DisplayTopTracks.class);
                startActivity(tActivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

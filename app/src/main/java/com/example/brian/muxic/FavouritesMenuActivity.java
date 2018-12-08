package com.example.brian.muxic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}

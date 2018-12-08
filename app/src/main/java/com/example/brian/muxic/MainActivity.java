package com.example.brian.muxic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void topArtists(View view){
        Intent artistsintent = new Intent(MainActivity.this, DisplayTopArtist.class);
        startActivity(artistsintent);
    }

    public void topTracks(View view){
        Intent tracksIntent = new Intent(MainActivity.this, DisplayTopTracks.class);
        startActivity(tracksIntent);
    }

    public void favourites(View view){
        Intent favouritesIntent = new Intent(MainActivity.this, FavouritesMenuActivity.class);
        startActivity(favouritesIntent);
    }
}

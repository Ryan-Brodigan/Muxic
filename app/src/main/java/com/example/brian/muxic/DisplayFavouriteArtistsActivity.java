package com.example.brian.muxic;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplayFavouriteArtistsActivity extends AppCompatActivity {

    private ArtistViewModel artistVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_favourite_artists);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FavArtistAdapter adapter = new FavArtistAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        artistVM = ViewModelProviders.of(this).get(ArtistViewModel.class);

        artistVM.getAllArtists().observe(this, new Observer<List<Artist>>() {
            @Override
            public void onChanged(@Nullable final List<Artist> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setArtists(words);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favourite_artists_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.action_favourite_tracks:
                Intent afActivity = new Intent(DisplayFavouriteArtistsActivity.this, DisplayFavouriteTracksActivity.class);
                startActivity(afActivity);
                return true;
            case R.id.action_top_artists:
                Intent aActivity = new Intent(DisplayFavouriteArtistsActivity.this, DisplayTopArtist.class);
                startActivity(aActivity);
                return true;
            case R.id.action_top_tracks:
                Intent tActivity = new Intent(DisplayFavouriteArtistsActivity.this, DisplayTopTracks.class);
                startActivity(tActivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

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

import java.util.List;

public class DisplayFavouriteTracksActivity extends AppCompatActivity {

    private TrackViewModel trackVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_favourite_tracks);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FavTrackAdapter adapter = new FavTrackAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        trackVM = ViewModelProviders.of(this).get(TrackViewModel.class);

        trackVM.getAllTracks().observe(this, new Observer<List<Track>>() {
            @Override
            public void onChanged(@Nullable final List<Track> tracks) {
                // Update the cached copy of the words in the adapter.
                adapter.setTracks(tracks);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favourite_tracks_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.action_favourite_artists:
                Intent afActivity = new Intent(DisplayFavouriteTracksActivity.this, DisplayFavouriteArtistsActivity.class);
                startActivity(afActivity);
                return true;
            case R.id.action_top_artists:
                Intent aActivity = new Intent(DisplayFavouriteTracksActivity.this, DisplayTopArtist.class);
                startActivity(aActivity);
                return true;
            case R.id.action_top_tracks:
                Intent tActivity = new Intent(DisplayFavouriteTracksActivity.this, DisplayTopTracks.class);
                startActivity(tActivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

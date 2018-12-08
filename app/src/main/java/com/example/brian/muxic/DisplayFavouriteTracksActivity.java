package com.example.brian.muxic;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

        // Get a new or existing ViewModel from the ViewModelProvider.
        trackVM = ViewModelProviders.of(this).get(TrackViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        trackVM.getAllTracks().observe(this, new Observer<List<Track>>() {
            @Override
            public void onChanged(@Nullable final List<Track> tracks) {
                // Update the cached copy of the words in the adapter.
                adapter.setTracks(tracks);
            }
        });
    }
}

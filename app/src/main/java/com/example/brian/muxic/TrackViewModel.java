package com.example.brian.muxic;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class TrackViewModel extends AndroidViewModel {
    private TrackRepository tRepository;

    private LiveData<List<Track>> mAllTracks;

    public TrackViewModel (Application application) {
        super(application);
        tRepository = new TrackRepository(application);
        mAllTracks = tRepository.getAllTracks();
    }

    LiveData<List<Track>> getAllTracks() { return mAllTracks; }

    public void insert(Track track) { tRepository.insert(track); }

    public void deleteByURL(String URL) {tRepository.deleteByURL(URL); }
}

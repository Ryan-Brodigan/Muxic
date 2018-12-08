package com.example.brian.muxic;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class ArtistViewModel extends AndroidViewModel {
    private ArtistRepository aRepository;

    private LiveData<List<Artist>> mAllArtists;

    public ArtistViewModel (Application application) {
        super(application);
        aRepository = new ArtistRepository(application);
        mAllArtists = aRepository.getAllArtists();
    }

    LiveData<List<Artist>> getAllArtists() { return mAllArtists; }

    public void insert(Artist artist) { aRepository.insert(artist); }

    public void deleteByID(String ID) {aRepository.deleteByID(ID); }
}

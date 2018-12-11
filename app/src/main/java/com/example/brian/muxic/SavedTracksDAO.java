package com.example.brian.muxic;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface SavedTracksDAO {

    @Insert
    void insert(Track track);

    @Query("DELETE FROM saved_tracks WHERE lastFMUrl = :lastFMUrl")
    void deleteByUrl(String lastFMUrl);

    @Query("SELECT * FROM saved_tracks ORDER BY name ASC")
    LiveData<List<Track>> selectAll();
}

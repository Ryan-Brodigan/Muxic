package com.example.brian.muxic;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface SavedArtistsDAO {

    @Insert
    void insert(Artist artist);

    @Query("DELETE FROM saved_artists WHERE ID = :ID")
    void deleteByID(String ID);

    @Query("SELECT * FROM saved_artists ORDER BY name ASC")
    LiveData<List<Artist>> selectAll();

    @Query("SELECT * FROM saved_artists WHERE ID = :ID")
    Artist selectByID(String ID);
}

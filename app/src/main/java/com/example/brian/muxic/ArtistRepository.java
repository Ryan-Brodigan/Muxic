package com.example.brian.muxic;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class ArtistRepository {
    private SavedArtistsDAO sArtistsDao;
    private LiveData<List<Artist>> sAllArtists;

    ArtistRepository(Application application) {
        MusicDatabase db = MusicDatabase.getDatabase(application);
        sArtistsDao = db.savedArtistsDAO();
        sAllArtists = sArtistsDao.selectAll();
    }

    LiveData<List<Artist>> getAllArtists() { return sAllArtists; }

    public void insert (Artist artist) {
        new insertAsyncTask(sArtistsDao).execute(artist);
    }

    public void deleteByID(String ID) {
        new deleteByIDAsyncTask(sArtistsDao).execute(ID);
    }

    private static class insertAsyncTask extends AsyncTask<Artist, Void, Void> {

        private SavedArtistsDAO mAsyncTaskDao;

        insertAsyncTask(SavedArtistsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Artist... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteByIDAsyncTask extends AsyncTask<String, Void, Void> {
        private SavedArtistsDAO mAsyncTaskDao;

        deleteByIDAsyncTask(SavedArtistsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.deleteByID(params[0]);
            return null;
        }
    }
}

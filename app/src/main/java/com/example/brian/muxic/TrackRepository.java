package com.example.brian.muxic;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class TrackRepository {
    private SavedTracksDAO sTracksDao;
    private LiveData<List<Track>> sAllTracks;

    TrackRepository(Application application) {
        MusicDatabase db = MusicDatabase.getDatabase(application);
        sTracksDao = db.savedTracksDAO();
        sAllTracks = sTracksDao.selectAll();
    }

    LiveData<List<Track>> getAllTracks() {
        return sAllTracks;
    }

    public void insert (Track track) {
        new TrackRepository.insertAsyncTask(sTracksDao).execute(track);
    }

    public void deleteByURL(String URL) {
        new TrackRepository.deleteByURLAsyncTask(sTracksDao).execute(URL);
    }

    private static class insertAsyncTask extends AsyncTask<Track, Void, Void> {

        private SavedTracksDAO mAsyncTaskDao;

        insertAsyncTask(SavedTracksDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Track... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteByURLAsyncTask extends AsyncTask<String, Void, Void> {
        private SavedTracksDAO mAsyncTaskDao;

        deleteByURLAsyncTask(SavedTracksDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.deleteByUrl(params[0]);
            return null;
        }
    }
}

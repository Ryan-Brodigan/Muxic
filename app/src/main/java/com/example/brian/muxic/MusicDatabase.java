package com.example.brian.muxic;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Artist.class, Track.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MusicDatabase extends RoomDatabase {
    public abstract SavedArtistsDAO savedArtistsDAO();
    public abstract SavedTracksDAO savedTracksDAO();

    private static volatile MusicDatabase INSTANCE;

    static MusicDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MusicDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MusicDatabase.class, "music_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

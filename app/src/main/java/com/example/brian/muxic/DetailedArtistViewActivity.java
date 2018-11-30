package com.example.brian.muxic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class DetailedArtistViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_artist_view);
        Intent i = getIntent();
        parseValues(i);
    }

    public void parseValues(Intent i){
        //Extract values from Intent
        String artistName = i.getExtras().getString("ArtistName");
        String artistImageURL = i.getExtras().getString("ArtistImage");
        Integer artistListeners = i.getExtras().getInt("ArtistListeners", 0);
        Integer artistPlaycount = i.getExtras().getInt("ArtistPlaycount", 0);

        //Get appropriate Views
        TextView artistNameView = findViewById(R.id.detailedArtistView_NameTitle);
        TextView artistListenersView =  findViewById(R.id.listenersValue);
        TextView artistPlaycountView =  findViewById(R.id.playcountValue);
        ImageView artistImageView = findViewById(R.id.artistMediumImage);

        //Apply appropriate values
        artistNameView.setText(artistName);
        artistListenersView.setText(artistListeners.toString());
        artistPlaycountView.setText(artistPlaycount.toString());

        //Use AsyncTask to download and apply image
        DownloadImageTask t = new DownloadImageTask(artistImageView, artistImageURL);
        t.execute();
    }

}
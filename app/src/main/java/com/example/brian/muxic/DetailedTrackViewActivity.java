package com.example.brian.muxic;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedTrackViewActivity extends AppCompatActivity {

    private String lastFMUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_track_view);
        Intent i = getIntent();
        parseValues(i);

        final Button button = findViewById(R.id.goToLastFMButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLastFMUrl();
            }
        });
    }

    public void openLastFMUrl(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.lastFMUrl));
        startActivity(browserIntent);
    }

    public void parseValues(Intent i){
        //Extract values from Intent
        String trackName = i.getExtras().getString("TrackName");
        String artistName = i.getExtras().getString("ArtistName");
        String trackImageURL = i.getExtras().getString("TrackImage");
        Integer trackPlaycount = i.getExtras().getInt("TrackPlaycount", 0);

        // Assign lastFMUrl value
        this.lastFMUrl = i.getExtras().getString("TrackUrl");

        //Get appropriate Views
        TextView trackNameView = findViewById(R.id.detailedTrackView_NameTitle);
        TextView trackArtistView =  findViewById(R.id.artistValue);
        TextView trackPlaycountView =  findViewById(R.id.playcountValue);
        ImageView trackImageView = findViewById(R.id.trackMediumImage);

        //Apply appropriate values
        trackNameView.setText(trackName);
        trackArtistView.setText(artistName);
        trackPlaycountView.setText(trackPlaycount.toString());

        //Use AsyncTask to download and apply image
        DownloadImageTask t = new DownloadImageTask(trackImageView, trackImageURL);
        t.execute();
    }
}

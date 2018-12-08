package com.example.brian.muxic;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedTrackViewActivity extends AppCompatActivity {

    private Track viewTrack;
    private TrackViewModel trackVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_track_view);

        trackVM = ViewModelProviders.of(this).get(TrackViewModel.class);

        Intent i = getIntent();
        parseValues(i);

        checkIfFavourited();

        final Button button = findViewById(R.id.goToLastFMButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLastFMUrl();
            }
        });

        final Button favouriteButton = findViewById(R.id.favourite_button);
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                favouriteTrack();
            }
        });

        final Button unfavouriteButton = findViewById(R.id.unfavourite_button);
        unfavouriteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                unfavouriteTrack();
            }
        });
    }

    //FAB Options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_top_artists:
                Intent aActivity = new Intent(DetailedTrackViewActivity.this, DisplayTopArtist.class);
                startActivity(aActivity);
                return true;
            case R.id.action_top_tracks:
                Intent tActivity = new Intent(DetailedTrackViewActivity.this, DisplayTopTracks.class);
                startActivity(tActivity);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Button actions
    public void checkIfFavourited(){
//        if(trackVM.selectByURL(viewTrack.getLastFMUrl()) != null){
//            Button faveButton = findViewById(R.id.favourite_button);
//            faveButton.setVisibility(View.INVISIBLE);
//        }
//        else{
//            Button unfaveButton = findViewById(R.id.unfavourite_button);
//            unfaveButton.setVisibility(View.INVISIBLE);
//        }
    }

    public void openLastFMUrl(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.viewTrack.getLastFMUrl()));
        startActivity(browserIntent);
    }

    public void favouriteTrack(){
        Button faveButton = findViewById(R.id.favourite_button);
        faveButton.setVisibility(View.INVISIBLE);

        Button unfaveButton = findViewById(R.id.unfavourite_button);
        unfaveButton.setVisibility(View.VISIBLE);

        trackVM.insert(viewTrack);
    }

    public void unfavouriteTrack(){
        Button unfaveButton = findViewById(R.id.unfavourite_button);
        unfaveButton.setVisibility(View.INVISIBLE);

        Button faveButton = findViewById(R.id.favourite_button);
        faveButton.setVisibility(View.VISIBLE);

        trackVM.deleteByURL(viewTrack.getLastFMUrl());
    }

    public void parseValues(Intent i){
        //Extract values from Intent
        String trackName = i.getExtras().getString("TrackName");
        String artistName = i.getExtras().getString("ArtistName");
        String trackImageURL = i.getExtras().getString("TrackImage");
        Integer trackPlaycount = i.getExtras().getInt("TrackPlaycount", 0);
        String lastFMUrl = i.getExtras().getString("TrackUrl");

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

        //Create viewTrack object from the parameters
        ArrayList<String> images = new ArrayList<>();
        images.add(trackImageURL);
        this.viewTrack = new Track(trackName, lastFMUrl, trackPlaycount, artistName, images, images.get(0));
    }
}

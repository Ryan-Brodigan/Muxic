package com.example.brian.muxic;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class DetailedArtistViewActivity extends AppCompatActivity {

    private Artist viewArtist;
    private ArtistViewModel artistVM;
    private String lastFMUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_artist_view);

        artistVM = ViewModelProviders.of(this).get(ArtistViewModel.class);

        Intent i = getIntent();
        parseValues(i);

        final Button button = findViewById(R.id.goToLastFMButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLastFMUrl();
            }
        });

        final Button favouriteButton = findViewById(R.id.favourite_button);
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                favouriteArtist();
            }
        });

        final Button unfavouriteButton = findViewById(R.id.unfavourite_button);
        unfavouriteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                unfavouriteArtist();
            }
        });
    }

    //FAB menu
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
                Intent aActivity = new Intent(DetailedArtistViewActivity.this, DisplayTopArtist.class);
                startActivity(aActivity);
                return true;
            case R.id.action_top_tracks:
                Intent tActivity = new Intent(DetailedArtistViewActivity.this, DisplayTopTracks.class);
                startActivity(tActivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Button actions
    public void openLastFMUrl(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(viewArtist.getLastFMUrl()));
        startActivity(browserIntent);
    }

    public void favouriteArtist(){
        Button faveButton = findViewById(R.id.favourite_button);
        faveButton.setVisibility(View.INVISIBLE);

        Button unfaveButton = findViewById(R.id.unfavourite_button);
        unfaveButton.setVisibility(View.VISIBLE);

        artistVM.insert(viewArtist);
    }

    public void unfavouriteArtist(){
        Button unfaveButton = findViewById(R.id.unfavourite_button);
        unfaveButton.setVisibility(View.INVISIBLE);

        Button faveButton = findViewById(R.id.favourite_button);
        faveButton.setVisibility(View.VISIBLE);

        artistVM.deleteByID(viewArtist.getID());
    }

    public void parseValues(Intent i){
        //Extract values from Intent
        String artistID = i.getExtras().getString("ArtistID");
        String artistName = i.getExtras().getString("ArtistName");
        String artistImageURL = i.getExtras().getString("ArtistImage");
        Integer artistListeners = i.getExtras().getInt("ArtistListeners", 0);
        Integer artistPlaycount = i.getExtras().getInt("ArtistPlaycount", 0);
        String lastFMUrl = i.getExtras().getString("ArtistUrl");

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

        //Create viewArtist
        ArrayList<String> images = new ArrayList<>();
        images.add(artistImageURL);
        viewArtist = new Artist(artistID, artistName, lastFMUrl, artistPlaycount, artistListeners, images.get(0), images);
    }

}

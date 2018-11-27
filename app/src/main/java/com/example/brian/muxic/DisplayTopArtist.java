package com.example.brian.muxic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class DisplayTopArtist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_artists);
        RetrieveTopArtistsTask t = new RetrieveTopArtistsTask(DisplayTopArtist.this);
        t.artistList = new ArrayList<>();
        t.listView = findViewById(R.id.list);
        t.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artist a = (Artist) parent.getItemAtPosition(position);
                Intent i = new Intent(DisplayTopArtist.this, DetailedArtistViewActivity.class);
                i.putExtra("ArtistName", a.getName());
                i.putExtra("ArtistListeners", a.getListeners());
                i.putExtra("ArtistPlaycount", a.getPlaycount());
                i.putExtra("ArtistImage", a.getImageURL().get(0));
                startActivity(i);
            }
        });
        t.execute();
    }

}

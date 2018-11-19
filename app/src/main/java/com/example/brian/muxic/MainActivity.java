package com.example.brian.muxic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrieveTopArtistsTask t = new RetrieveTopArtistsTask((TextView) findViewById(R.id.artist_name));
        t.execute();
    }


}

package com.example.brian.muxic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrieveTopArtistsTask t = new RetrieveTopArtistsTask(MainActivity.this);
        t.artistList = new ArrayList<>();
        t.listView = findViewById(R.id.list);
        t.execute();
    }


}

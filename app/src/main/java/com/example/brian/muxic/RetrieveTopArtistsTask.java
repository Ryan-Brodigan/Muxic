package com.example.brian.muxic;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class RetrieveTopArtistsTask extends AsyncTask<Void, Void, Void> {

    //private TextView textView;
    private String uriTag = MainActivity.class.getSimpleName();
    private ProgressBar progressBar;
    private DisplayTopArtist context;
    protected ListView listView;
    protected ArrayList<Artist> artistList;
    private static String lastFMURL = "http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=74f88c78b264c0f0bcb407833629961b&format=json";

    public RetrieveTopArtistsTask(DisplayTopArtist context){
        this.context = context;
        this.artistList = new ArrayList<>();
        this.listView = listView;
    }

    @Override
    protected void onPreExecute() {
        progressBar = context.findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        uriHandler handler = new uriHandler();

        String stringJson = handler.requestSend(lastFMURL);
        Log.e(uriTag, "From LastFm" + stringJson);

        if(stringJson != null){
            try{
                JSONObject jsonObj = new JSONObject(stringJson);
                JSONArray library = jsonObj.getJSONObject("artists").getJSONArray("artist");

                for(int i = 0; i < library.length(); i++){
                    JSONObject lib = library.getJSONObject(i);
                    String artistName = lib.getString("name");
                    Integer playCount = lib.getInt("playcount");
                    Integer listeners = lib.getInt("listeners");
                    JSONArray getImageURL = lib.getJSONArray("image");
                    String imageUrl = getImageURL.getJSONObject(1).getString("#text");
                    Artist newArtists = new Artist(artistName,playCount,listeners,imageUrl);
                    artistList.add(newArtists);
                }

            }catch(final JSONException e){
                Log.e(uriTag, "Parsing Error: " + e.getMessage());
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context.getApplicationContext(), "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        else {
            Log.e(uriTag, "Couldn't get json from server.");
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context.getApplicationContext(), "Json parsing error: ",Toast.LENGTH_LONG).show();
                }
            });

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        progressBar.setVisibility(View.INVISIBLE);
        ArrayAdapter<Artist> adapter = new ArrayAdapter<Artist>(this.context,R.layout.list_details,R.id.name,artistList);
        listView.setAdapter(adapter);
    }

}

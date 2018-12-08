package com.example.brian.muxic;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import android.widget.ProgressBar;

public class RetrieveTopTracksTask extends AsyncTask<Void, Void, Void> implements SearchView.OnQueryTextListener {

    private String uriTag = MainActivity.class.getSimpleName();
    private ProgressBar progressBar;
    private DisplayTopTracks context;
    protected ListView listView;
    private static String lastFMURL = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=74f88c78b264c0f0bcb407833629961b&format=json";
    protected static ArrayList<Track> libraryList;
    private TrackAdapter trackAdapter;
    private SearchView editsearch;

    public RetrieveTopTracksTask(DisplayTopTracks context){
        this.context = context;
        this.libraryList = new ArrayList<>();
        this.listView = listView;
    }

    @Override
    protected void onPreExecute(){
        progressBar = context.findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        uriHandler handler = new uriHandler();
        String stringJson = handler.requestSend(lastFMURL);
        Log.e(uriTag, "From LastFm" + stringJson);

        if(stringJson != null){
            try{
                JSONObject jsonObj = new JSONObject(stringJson);
                JSONArray library = jsonObj.getJSONObject("tracks").getJSONArray("track");
                for(int i = 0; i < library.length(); i++){
                    JSONObject lib = library.getJSONObject(i);
                    String trackName = lib.getString("name");
                    String lastFMUrl = lib.getString("url");
                    Integer playCount = lib.getInt("playcount");
                    JSONObject getArtistName = lib.getJSONObject("artist");
                    String artistName = getArtistName.getString("name");
                    JSONArray getImageURL = lib.getJSONArray("image");
                    ArrayList<String> images = new ArrayList<>();
                    String smallImageUrl = getImageURL.getJSONObject(0).getString("#text");
                    String mediumImageUrl = getImageURL.getJSONObject(1).getString("#text");
                    String largeImageUrl = getImageURL.getJSONObject(2).getString("#text");
                    images.add(smallImageUrl);
                    images.add(mediumImageUrl);
                    images.add(largeImageUrl);
                    Track newTrack = new Track(trackName, lastFMUrl, playCount,artistName,images,mediumImageUrl);
                    libraryList.add(newTrack);
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
        trackAdapter = new TrackAdapter(this.context, libraryList);
        listView.setAdapter(trackAdapter);
        editsearch = (SearchView) this.context.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
    }

    //when a user types the query, this method will be executed.
    //For example, if he types “a,” then this method will run, simultaneously for all other words.
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        trackAdapter.filter(text);
        return false;
    }
}
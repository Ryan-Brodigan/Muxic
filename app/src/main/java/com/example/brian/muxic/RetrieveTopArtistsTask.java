package com.example.brian.muxic;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
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
import android.widget.EditText;

public class RetrieveTopArtistsTask extends AsyncTask<Void, Void, Void> implements SearchView.OnQueryTextListener {

    private String uriTag = MainActivity.class.getSimpleName();
    private ProgressBar progressBar;
    private DisplayTopArtist context;
    protected ListView listView;
    protected static ArrayList<Artist> artistList;
    private static String lastFMURL = "http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=74f88c78b264c0f0bcb407833629961b&format=json";
    private ArtistAdapter artistAdapter;
    private SearchView editsearch;

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
                    String ID = lib.getString("mbid");
                    String artistName = lib.getString("name");
                    String lastFMUrl = lib.getString("url");
                    Integer playCount = lib.getInt("playcount");
                    Integer listeners = lib.getInt("listeners");
                    JSONArray getImageURL = lib.getJSONArray("image");
                    String smallImageUrl = getImageURL.getJSONObject(0).getString("#text");
                    String mediumImageUrl = getImageURL.getJSONObject(1).getString("#text");
                    String largeImageUrl = getImageURL.getJSONObject(2).getString("#text");
                    ArrayList<String> images = new ArrayList<>();
                    images.add(smallImageUrl);
                    images.add(mediumImageUrl);
                    images.add(largeImageUrl);
                    Artist newArtists = new Artist(ID,artistName,lastFMUrl,playCount,listeners,mediumImageUrl,images);
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
        artistAdapter = new ArtistAdapter(this.context, artistList);
        listView.setAdapter(artistAdapter);
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
        artistAdapter.filter(text);
        return false;
    }


}

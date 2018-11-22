package com.example.brian.muxic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
//Async Method that pulls the JSON from The Url.
public class RetrieveTopTracksTask extends AsyncTask<Void, Void, Void> {

    private String uriTag = MainActivity.class.getSimpleName();
    private ProgressBar progressBar;
    private MainActivity context;
    protected ListView listView;

    private static String lastFMURL = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=74f88c78b264c0f0bcb407833629961b&format=json";
    ArrayList<Track> libraryList;
//    ArrayList<Track> withDuplicates;
    public RetrieveTopTracksTask(MainActivity context){
        this.context = context;
        this.libraryList = new ArrayList<>();
//        this.withDuplicates = new ArrayList<>();
        this.listView = listView;
    }

    //Run Progress Bar when pulling json
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

                // Getting JSON Array node
                JSONArray library = jsonObj.getJSONObject("tracks").getJSONArray("track");
//                JSONArray getImageURL = jsonObj.getJSONObject("tracks").getJSONArray()
//                String imageUrl;
                //Track newTrack = new Track();
                //ArrayList<Track> withDuplicates = new ArrayList<Track>();
                for(int i = 0; i < library.length(); i++){
                    JSONObject lib = library.getJSONObject(i);
//                    JSONArray getTrackDetail = lib.getJSONObject("tracks");
                    String trackName = lib.getString("name");
                    Integer playCount = lib.getInt("playcount");

                    JSONObject getArtistName = lib.getJSONObject("artist");
                    String artistName = getArtistName.getString("name");

                    //Reiterate through JSON and get image array and objects.
                    JSONArray getImageURL = lib.getJSONArray("image");
                    for(int j = 0; j < getImageURL.length(); j++){
                        JSONObject getImage = getImageURL.getJSONObject(0);
                        String imageUrl = getImage.getString("#text");

                        Track newTrack = new Track(trackName,playCount,artistName,imageUrl);

                        //Removing the duplicates using hashset
                        //withDuplicates.add(newTrack);
//                        withDuplicates.add(newTrack);
//                        Set<Track> removeDuplicates = new HashSet<Track>(withDuplicates);

                        libraryList.add(newTrack);
                    }



//                    JSONObject getArtist = lib.getJSONObject("artist");
//                    String artistName = getArtist.getString("name");
//                    HashMap<String,String> trackLibrary = new HashMap<>();
//                    trackLibrary.put("name", trackName);
//                    trackLibrary.put("playcount", playCount);
//                    trackLibrary.put("name", artistName);

//                     newTrack = new Track(trackName,playCount,artistName,imageUrl);


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
        ArrayAdapter<Track> adapter = new ArrayAdapter<Track>(this.context,R.layout.list_details,R.id.name,libraryList);
        listView.setAdapter(adapter);
    }

}
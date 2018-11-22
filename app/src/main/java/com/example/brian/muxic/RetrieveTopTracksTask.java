package com.example.brian.muxic;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import android.widget.ProgressBar;
//Async Method that pulls the JSON from The Url.
public class RetrieveTopTracksTask extends AsyncTask<Void, Void, Void> {

    private String uriTag = MainActivity.class.getSimpleName();
    private ProgressBar progressBar;
    private MainActivity context;
    protected ListView listView;

    private static String lastFMURL = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=74f88c78b264c0f0bcb407833629961b&format=json";
    ArrayList<Track> libraryList;
    public RetrieveTopTracksTask(MainActivity context){
        this.context = context;
        this.libraryList = new ArrayList<>();
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
                JSONArray library = jsonObj.getJSONObject("tracks").getJSONArray("track");

                for(int i = 0; i < library.length(); i++){
                    JSONObject lib = library.getJSONObject(i);
                    String trackName = lib.getString("name");
                    Integer playCount = lib.getInt("playcount");
                    JSONObject getArtistName = lib.getJSONObject("artist");
                    String artistName = getArtistName.getString("name");
                    JSONArray getImageURL = lib.getJSONArray("image");
                    String imageUrl = getImageURL.getJSONObject(1).getString("#text");
                    Track newTrack = new Track(trackName,playCount,artistName,imageUrl);
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
        ArrayAdapter<Track> adapter = new ArrayAdapter<Track>(this.context,R.layout.list_details,R.id.name,libraryList);
        listView.setAdapter(adapter);
    }

}
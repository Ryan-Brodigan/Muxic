package com.example.brian.muxic;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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
    private ArrayList<Artist> artists;
    private TextView textView;

    public RetrieveTopArtistsTask(TextView txtView){
        textView = txtView;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(Void... params) {
        artists = getTopArtists();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        textView.setText(artists.get(0).getName());
    }

    public static ArrayList<Artist> getTopArtists() {
        ArrayList<Artist> artistsList = new ArrayList<>();
        try {
            JSONObject artistsJSON = readJsonFromUrl("http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=74f88c78b264c0f0bcb407833629961b&format=json");
            JSONArray artistsJSONArray = artistsJSON.getJSONObject("artists").getJSONArray("artist");
            String name = artistsJSONArray.getJSONObject(0).getString("name");
            Integer listeners = artistsJSONArray.getJSONObject(0).getInt("listeners");
            Integer playcount = artistsJSONArray.getJSONObject(0).getInt("playcount");
            String imageURL = artistsJSONArray.getJSONObject(0).getJSONArray("image").getJSONObject(2).getString("#text");
            Log.d("API SHTUFF", "API DATA: " + name + ", " + listeners + ", " + playcount + ", " + imageURL);
            Artist newArtist = new Artist(name, playcount, listeners, imageURL);
            artistsList.add(newArtist);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return artistsList;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}

package com.example.brian.muxic;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class uriHandler {

    //The java.lang.Class.getSimpleName() returns the simple name of the underlying class as given in the source code.
    private static final String uriTag = uriHandler.class.getSimpleName();


    public uriHandler(){

    }

    public String requestSend(String requestToUrl){

        String res = null;

        try{
            URL url = new URL(requestToUrl);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");

            InputStream inputStream = new BufferedInputStream(connect.getInputStream());
            res = convertJsonToString(inputStream);
        }catch(IOException e){
            Log.e(uriTag, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(uriTag, "Exception: " + e.getMessage());
        }

        return res;
    }

    private String convertJsonToString(InputStream inputstream) {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(inputstream));
        StringBuilder sb = new StringBuilder();
        String getLine;

        try {
            while ((getLine = bfr.readLine()) != null) {
                sb.append(getLine).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}

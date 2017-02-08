package com.example.ali.test;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ali on 2/7/2017.
 */

public class Test extends DownloadActivity {
    OnResult onResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonParser jsonParser = new JsonParser();
        String[] objects = {"results"};
        jsonParser.setObjects(objects);
        setParser(jsonParser);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie";
        final String SORT = "sort_by";
        final String APPID_PARAM = "api_key";

        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(SORT, "popularity.desc")
                .appendQueryParameter(APPID_PARAM, "144eefdfe75e0f8cb5d9f9b68d178670")
                .build();

        setUrl(builtUri.toString());
        Log.v("Test","Url :"+ builtUri.toString());
        onResult = new DownloadActivity.OnResult(){

            @Override
            public void onSuccess(List<Movie> result) {
                for (Movie m:result){
                    Log.v("Test","OnSuccess "+ m.getPosterUrl());
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.v("Test","OnError "+ errorMessage);
            }
        };
        try {
            fetch(onResult);
        } catch (InterruptedException | MalformedURLException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }


    }



}

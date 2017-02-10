package com.example.ali.test.controller.activity;

import android.os.Bundle;

import com.example.ali.test.R;
import com.example.ali.test.controller.activity.DownloadActivity;

/**
 * Created by Ali on 2/7/2017.
 */

public class MainActivity extends DownloadActivity {
    String url  = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=144eefdfe75e0f8cb5d9f9b68d178670";
    String reviewUrl = "http://api.themoviedb.org/3/movie/346672/reviews?api_key=144eefdfe75e0f8cb5d9f9b68d178670";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();





    }



}

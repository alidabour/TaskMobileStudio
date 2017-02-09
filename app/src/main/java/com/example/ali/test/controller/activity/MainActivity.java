package com.example.ali.test.controller.activity;

import android.os.Bundle;

import com.example.ali.test.R;
import com.example.ali.test.controller.activity.DownloadActivity;

/**
 * Created by Ali on 2/7/2017.
 */

public class MainActivity extends DownloadActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if(savedInstanceState ==null){
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment,new DetailFragment())
//                    .commit();
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();





    }



}

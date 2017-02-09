package com.example.ali.test;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ali.test.adapter.MovieRecycleAdapter;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    MovieRecycleAdapter movieRecycleAdapter;
    DownloadActivity.OnResult onResult;

    Button button;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JsonParser jsonParser = new JsonParser();
        String[] objects = {"poster_path","title"};
        jsonParser.setObjects(objects);
        ((MainActivity)getActivity()).setParser(jsonParser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.mainRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Test","onClick");
                ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction()
//                        .detach(MainFragment.this)
//                        .hide(MainFragment.this)
//                        .remove(MainFragment.this)
                        .replace(R.id.fragment,new DetailFragment())
                        .commit();
            }
        });
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie";
        final String SORT = "sort_by";
        final String APPID_PARAM = "api_key";

        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(SORT, "popularity.desc")
                .appendQueryParameter(APPID_PARAM, "144eefdfe75e0f8cb5d9f9b68d178670")
                .build();

        ((MainActivity)getActivity()).setUrl(builtUri.toString());
        onResult = new DownloadActivity.OnResult(){

            @Override
            public void onSuccess(List<Movie> result) {
                movieRecycleAdapter = new MovieRecycleAdapter(getContext(), result);
                recyclerView.setAdapter(movieRecycleAdapter);

                for (Movie m:result){
                    Log.v("MainFragment","OnSuccess "+ m.getPosterUrl());
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.v("MainFragment","OnError "+ errorMessage);
            }
        };
        try {
            ((MainActivity)getActivity()).fetch(onResult);
        } catch (InterruptedException | MalformedURLException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }

    }
}

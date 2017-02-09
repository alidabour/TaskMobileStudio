package com.example.ali.test.controller.fragment;


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

import com.example.ali.test.Example;
import com.example.ali.test.Movie2;
import com.example.ali.test.MovieService;
import com.example.ali.test.controller.activity.DownloadActivity;
import com.example.ali.test.JsonParser;
import com.example.ali.test.controller.activity.MainActivity;
import com.example.ali.test.R;
import com.example.ali.test.adapter.MovieRecycleAdapter;
import com.example.ali.test.model.Movie;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    MovieRecycleAdapter movieRecycleAdapter;
    DownloadActivity.OnResultListener onResultListener;

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
        ((MainActivity)getActivity()).setDataModel(Movie.class.getName());
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
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.themoviedb.org/3/discover/")
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        Observable<Example> movieData1 = movieService.getMovieData("popularity.desc","144eefdfe75e0f8cb5d9f9b68d178670");

        movieData1.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieData -> {
                    Log.v("Movie Data","Data title:"+ movieData.getResults().get(0).getTitle()
                    );
                });
        final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie";
        final String SORT = "sort_by";
        final String APPID_PARAM = "api_key";

        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(SORT, "popularity.desc")
                .appendQueryParameter(APPID_PARAM, "144eefdfe75e0f8cb5d9f9b68d178670")
                .build();

        ((MainActivity)getActivity()).setUrl(builtUri.toString());
        Log.v("Test","Url :"+builtUri.toString());
//        onResultListener = new DownloadActivity.OnResultListener(){
//
//            @Override
//            public void onSuccess(List<Object> result) {
//                movieRecycleAdapter = new MovieRecycleAdapter(getContext(),result);
//                recyclerView.setAdapter(movieRecycleAdapter);
//
//                for (Object m:result){
//                    Log.v("MainFragment","OnSuccess "+ ((Movie)m).getPosterUrl());
//                }
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//                Log.v("MainFragment","OnError "+ errorMessage);
//            }
//        };
        onResultListener = new DownloadActivity.OnResultListener() {
            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        };
        try {
            ((MainActivity)getActivity()).fetch(onResultListener);
        } catch (InterruptedException | MalformedURLException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }

    }
}

package com.example.ali.test.controller.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ali.test.controller.activity.DownloadActivity;
import com.example.ali.test.JsonParser;
import com.example.ali.test.controller.activity.MainActivity;
import com.example.ali.test.R;
import com.example.ali.test.model.Movie;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    DownloadActivity.OnResultListener onResultListenerReviews;
    DownloadActivity.OnResultListener onResultListenerVideos;
    TextView title;
    String text = " ";
    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container.removeAllViews();
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);
        title = (TextView) view.findViewById(R.id.title);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/movie";
        final String APPID_PARAM = "api_key";

        Uri builtUri= Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendPath("346672")
                .appendPath("reviews")
                .appendQueryParameter(APPID_PARAM,"144eefdfe75e0f8cb5d9f9b68d178670")
                .build();
        ((MainActivity)getActivity()).setUrl(builtUri.toString());
        String[] objects = {"author","content"};
        JsonParser jsonParser = new JsonParser();
        jsonParser.setObjects(objects);
        ((MainActivity)getActivity()).setParser(jsonParser);
        ((MainActivity)getActivity()).setDataModel(Movie.class.getName());
        Log.v("Test","Reviews Url : "+builtUri.toString());
//        onResultListenerReviews = new DownloadActivity.OnResultListener(){
//            @Override
//            public void onSuccess(List<Object> movies) {
//                Log.v("Test","DF. OnSuccess "+ ((Movie)movies.get(0)).getAuthor());
//                for (Object x:movies){
//                    text += " Author : "+((Movie)x).getAuthor()+ "\n";
//                    text += " Content :"+((Movie)x).getContent()+"\n";
//                }
//
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//
//            }
//        };
        onResultListenerReviews = new DownloadActivity.OnResultListener() {
            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        };
        try {
            ((MainActivity)getActivity()).fetch(onResultListenerReviews);
        } catch (InterruptedException | MalformedURLException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }


        builtUri= Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendPath("346672")
                .appendPath("videos")
                .appendQueryParameter(APPID_PARAM,"144eefdfe75e0f8cb5d9f9b68d178670")
                .build();
        ((MainActivity)getActivity()).setUrl(builtUri.toString());

        String[] objects2 = {"key","name"};
        jsonParser.setObjects(objects2);
        ((MainActivity)getActivity()).setParser(jsonParser);
        ((MainActivity)getActivity()).setDataModel(Movie.class.getName());
//        onResultListenerVideos = new DownloadActivity.OnResultListener(){
//            @Override
//            public void onSuccess(List<Object> movies) {
//                for (Object x:movies){
//                    Log.v("Test","DF. OnSuccess2 Key :"+ ((Movie)x).getKey());
//                    text += " name : "+((Movie)x).getName() + "\n";
//                    text += " key :"+((Movie)x).getKey() +"\n";
//
//                }
//                title.setText(text);
//            }
//            @Override
//            public void onError(String errorMessage) {
//
//            }
//        };
        onResultListenerVideos = new DownloadActivity.OnResultListener() {
            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        };
        try {
            ((MainActivity)getActivity()).fetch(onResultListenerVideos);
        } catch (InterruptedException | MalformedURLException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }
}

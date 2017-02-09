package com.example.ali.test;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ali.test.data.Movie;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    DownloadActivity.OnResult onResult;
    DownloadActivity.OnResult onResult2;
    TextView title;
    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] objects = {"author","content"};
        JsonParser jsonParser = new JsonParser();
        jsonParser.setObjects(objects);
        ((MainActivity)getActivity()).setParser(jsonParser);
        ((MainActivity)getActivity()).setDataModel(Movie.class.getName());

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

        Log.v("Test","Reviews Url : "+builtUri.toString());
        onResult = new DownloadActivity.OnResult(){
            @Override
            public void onSuccess(List<Object> movies) {
                Log.v("Test","DF. OnSuccess "+ ((Movie)movies.get(0)).getAuthor());

            }

            @Override
            public void onError(String errorMessage) {

            }
        };
        try {
            ((MainActivity)getActivity()).fetch(onResult);
        } catch (InterruptedException | MalformedURLException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
        ((MainActivity)getActivity()).setUrl(builtUri.toString());
        onResult2 = new DownloadActivity.OnResult(){
            @Override
            public void onSuccess(List<Object> movies) {
                Log.v("Test","DF. OnSuccess "+ ((Movie)movies.get(0)).getAuthor());

            }

            @Override
            public void onError(String errorMessage) {

            }
        };
        try {
            ((MainActivity)getActivity()).fetch(onResult2);
        } catch (InterruptedException | MalformedURLException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }
}

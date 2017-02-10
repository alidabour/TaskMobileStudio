package com.example.ali.test.controller.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ali.test.model.Result;
import com.example.ali.test.controller.activity.DownloadActivity;
import com.example.ali.test.controller.activity.MainActivity;
import com.example.ali.test.R;
import java.util.List;


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

        ((MainActivity)getActivity()).setUrl("http://api.themoviedb.org/3/discover/");
        onResultListenerReviews = new DownloadActivity.OnResultListener() {
            @Override
            public void onSuccess(List<Result> response) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        };
        ((MainActivity)getActivity()).fetch(onResultListenerReviews);

        ((MainActivity)getActivity()).setUrl("http://api.themoviedb.o/rg/3/discover/");
        onResultListenerVideos = new DownloadActivity.OnResultListener() {
            @Override
            public void onSuccess(List<Result> response) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        };
        ((MainActivity)getActivity()).fetch(onResultListenerVideos);

    }
}

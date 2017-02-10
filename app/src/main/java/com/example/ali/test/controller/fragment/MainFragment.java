package com.example.ali.test.controller.fragment;


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

import com.example.ali.test.MovieService;
import com.example.ali.test.model.Result;
import com.example.ali.test.controller.activity.DownloadActivity;
import com.example.ali.test.controller.activity.MainActivity;
import com.example.ali.test.R;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    RecyclerView recyclerView;
//    MovieRecycleAdapter movieRecycleAdapter;
    DownloadActivity.OnResultListener onResultListener;

    Button button;
    public MainFragment() {
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

        ((MainActivity)getActivity()).setUrl("http://api.themoviedb.org/3/discover/");
        ((MainActivity)getActivity()).setInterfaceService(MovieService.class.getName());
        onResultListener = new DownloadActivity.OnResultListener() {
            @Override
            public void onSuccess(List<Result> response) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        };
        ((MainActivity)getActivity()).fetch(onResultListener);

    }
}

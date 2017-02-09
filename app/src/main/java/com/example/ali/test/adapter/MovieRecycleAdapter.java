package com.example.ali.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ali.test.Movie;
import com.example.ali.test.R;

import java.util.List;


/**
 * Created by Ali on 2/9/2017.
 */

public class MovieRecycleAdapter extends RecyclerView.Adapter<MovieRecycleAdapter.ViewHolder> {
    Context context;
    List<Movie> movies;
    public MovieRecycleAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies=movies;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.poster_card_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.v("Test","RVA posterURL : "+movies.get(position).getPosterUrl());
        String posterURL = "http://image.tmdb.org/t/p/w185/" + movies.get(position).getPosterUrl();
        String title = movies.get(position).getTitle();
        holder.title.setText(title);
        Glide.with(context).load(posterURL).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movies.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }
    }

}


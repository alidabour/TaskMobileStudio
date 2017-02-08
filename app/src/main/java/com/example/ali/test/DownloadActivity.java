package com.example.ali.test;

import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class DownloadActivity extends AppCompatActivity {
    String[] url;
    public OnResult onResult;
    private String dataDownloaded = null;
    private SuperParser parser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    final protected void fetch(OnResult onResult) throws MalformedURLException, ExecutionException, InterruptedException, JSONException {
        DownloadAsyncTask d = new DownloadAsyncTask();
        URL[] urls =new URL[url.length];
        for (int i=0;i<url.length; i++){
            urls[i]=new URL(url[i]);
        }
        this.dataDownloaded=d.execute(urls).get();
        this.parser.setData(this.dataDownloaded);
        List<String> posterUrls = this.parser.getData();
        List<Movie> movies = new ArrayList<Movie>();
        for (String x:posterUrls){
            Movie m = new Movie();
            m.setPosterUrl(x);
            movies.add(m);
        }
        this.onResult= onResult;
        this.onResult.onSuccess(movies);
        this.onResult.onError("Error");

    }
    void setParser(SuperParser parser){
        this.parser=parser;
    }

    void setUrl(String... urlString){
        this.url = urlString;
    }
    void getParams(){

    }


    public interface OnResult{
        public void onSuccess(List<Movie> movies);
        public void onError(String errorMessage);
    }

}

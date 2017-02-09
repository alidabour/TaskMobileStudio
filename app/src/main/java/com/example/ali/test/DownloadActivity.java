package com.example.ali.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ali.test.data.DataModel;
import com.example.ali.test.data.Movie;

import org.json.JSONException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class DownloadActivity extends AppCompatActivity {
    String[] url;
    public OnResult onResult;
    private String dataDownloaded = null;
    private SuperParser parser;
    private String dataModel;
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
        List<HashMap<String,String>> maps ;
        maps=this.parser.getData();
//        List<String> posterUrls = this.parser.getData();
//        List<Movie> movies = new ArrayList<Movie>();
//        for (HashMap<String,String> x:maps){
//            Movie m = new Movie();
//            m.setPosterUrl(x.get("poster_path"));
//            m.setTitle(x.get("title"));
//            Log.v("Test","DA url :"+m.getPosterUrl());
//            movies.add(m);
//        }
        List<Object> moviesObjects  = new ArrayList<>();
        for (HashMap<String,String> x:maps) {
            String data = "x";
            Log.v("Test","HashMap values : "+x.toString());
            Object object=null;
            Class<?> cla=null;
            try {
                cla = Class.forName(dataModel);
                Log.v("Test","Mission DataModel name:"+DataModel.class);
                Log.v("Test","Mission "+cla.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.v("Test","Mission error :"+e);
            }
            Constructor<?> ctor = null;
            try {
                Log.v("Test","HashMap poster_path : "+x.get("poster_path"));
                ctor = cla.getConstructor(HashMap.class);
//                Method m = cla.getMethod("setData",HashMap.class);
//                Log.v("Test","Method : "+cla.getMethod("setData",HashMap.class));
//                m.invoke(x);
                Log.v("Test","Constructor :"+ctor.getName());
            } catch (NoSuchMethodException e) {
                Log.v("Test","Mission error :"+e);
            }
            try {
                object = ctor.newInstance(x);
                Log.v("Test","PosterUrl: "+((Movie)object).getPosterUrl());
                Log.v("Test","Author: "+((Movie)object).getAuthor());

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                Log.v("Test","Mission error :"+e);

            }

            moviesObjects.add(object);
        }
        this.onResult= onResult;
        this.onResult.onSuccess(moviesObjects);
        this.onResult.onError("Error");

    }
    void setParser(SuperParser parser){
        this.parser=parser;
    }

    void setUrl(String... urlString){
        this.url = urlString;
    }
    void setDataModel(String dataModel){
        this.dataModel=dataModel;
    }


    public interface OnResult{
        public void onSuccess(List<Object> movies);
        public void onError(String errorMessage);
    }

}

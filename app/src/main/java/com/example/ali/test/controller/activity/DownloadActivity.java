package com.example.ali.test.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ali.test.DownloadAsyncTask;
import com.example.ali.test.SuperParser;
import com.example.ali.test.model.Movie;

import org.json.JSONException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class DownloadActivity extends AppCompatActivity {
    String[] url;
    public OnResultListener onResultListener;
    private String dataDownloaded = null;
    private SuperParser parser;
    private String dataModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public final void fetch(OnResultListener onResultListener) throws MalformedURLException, ExecutionException, InterruptedException, JSONException {
        DownloadAsyncTask d = new DownloadAsyncTask();
        URL[] urls =new URL[url.length];
        for (int i=0;i<url.length; i++){
            urls[i]=new URL(url[i]);
        }
        this.dataDownloaded=d.execute(urls).get();
//        this.parser.setData(this.dataDownloaded);
//        List<HashMap<String,String>> maps ;
//        maps=this.parser.getData();
//
//        List<Object> moviesObjects  = new ArrayList<>();
//        for (HashMap<String,String> x:maps) {
//            Log.v("Test","HashMap values : "+x.toString());
//            Object object=null;
//            Class<?> cla=null;
//            try {
//                cla = Class.forName(dataModel);
//                Log.v("Test","Mission DataModel name:"+DataModel.class);
//                Log.v("Test","Mission "+cla.getName());
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//                Log.v("Test","Mission error :"+e);
//            }
//            Constructor<?> ctor = null;
//            try {
//                Log.v("Test","HashMap poster_path : "+x.get("poster_path"));
//                ctor = cla.getConstructor(HashMap.class);
//                Method m = cla.getMethod("setData",HashMap.class);
//                Log.v("Test","Method : "+cla.getMethod("setData",HashMap.class));
//                m.invoke(x);
//                Log.v("Test","Constructor :"+ctor.getName());
//            } catch (NoSuchMethodException e) {
//                Log.v("Test","Mission error :"+e);
//            }
//            try {
//                object = ctor.newInstance(x);

//                Log.v("Test","PosterUrl: "+((Movie)object).getPosterUrl());
//                Log.v("Test","DownloadActivity Author: "+((Movie)object).getAuthor());
//                Log.v("Test","DownloadActivity Key: "+((Movie)object).getKey());
//
//            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
//                Log.v("Test","Mission error :"+e);
//
//            }
//
//            moviesObjects.add(object);
//        }
        this.onResultListener = onResultListener;
        this.onResultListener.onSuccess(this.dataDownloaded);
//        this.onResultListener.onError("Error");

    }
    public void setParser(SuperParser parser){
        this.parser=parser;
    }

    public void setUrl(String... urlString){
        this.url = urlString;
    }
    public void setDataModel(String dataModel){
        this.dataModel=dataModel;
    }


    public interface OnResultListener {
         void onSuccess(String response);
         void onError(String errorMessage);
    }

}

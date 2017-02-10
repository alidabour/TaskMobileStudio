package com.example.ali.test;

import android.util.Log;

import com.example.ali.test.model.Example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;


/**
 * Created by Ali on 2/9/2017.
 */

public class DownloadTask {
    Observable<Example> observable;
    Type type;
    public Observable<Example> getObservable(){
        return this.observable;
    }
    public DownloadTask(String url,String className){
        fun(MovieService.class);
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
        Log.v("Test","Class Name :"+className);
        Class<?> movieServiceClass = null;
        try {
            movieServiceClass = Class.forName(className);
//            Object movieService = retrofit.create(movieServiceClass);
//            ((type)retrofit.create(movieServiceClass))
//            ((MovieService)retrofit.create(movieServiceClass)).getClass().getMethods()[0].invoke("popularity.desc","144eefdfe75e0f8cb5d9f9b68d178670");
            //Result equals -> com.example.ali.test.MovieService
            Log.v("Test","MovieClass name :"+ movieServiceClass.getName());
            //Result equals -> public abstract rx.Observable com.example.ali.test.MovieService.getMovieData(java.lang.String,java.lang.String)
            Log.v("Test","MovieClass method :"+movieServiceClass.getMethods()[0]);
            //Result equals -> Error : Expected receiver of type com.example.ali.test.MovieService, but got java.lang.String
            //What it should lock like -> (MovieService)movieService).getMovieData("popularity.desc","144eefdfe75e0f8cb5d9f9b68d178670");
//            Log.v("Test","MovieClass invo : "+movieServiceClass.getMethods()[0].invoke("popularity.desc","144eefdfe75e0f8cb5d9f9b68d178670") );

        } catch (ClassNotFoundException  e) {
            Log.v("Test","MissionIM , Error :"+e );
        }

        Object movieService = retrofit.create(movieServiceClass);
        Observable<Example> movieData1 = ((MovieService)movieService).getMovieData("popularity.desc","144eefdfe75e0f8cb5d9f9b68d178670");
        this.observable =movieData1;

    }
    void fun (Type i){
        this.type = i;
    }

}

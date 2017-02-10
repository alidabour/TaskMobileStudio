package com.example.ali.test.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ali.test.DownloadTask;
import com.example.ali.test.MovieService;
import com.example.ali.test.model.Example;
import com.example.ali.test.model.Result;
import java.util.List;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class DownloadActivity extends AppCompatActivity {
    String url;
    public OnResultListener onResultListener;
    private List<Result> dataDownloaded = null;
    private String className;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public final void fetch(OnResultListener onResultListener) {
        DownloadTask downloadTask = new DownloadTask(url,className);
        Observable<Example> observable=downloadTask.getObservable();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieData -> {
                    this.dataDownloaded = movieData.getResults();
                    Log.v("Test","Data title:"+ movieData.getResults().get(0).getTitle()
                    );
                });
        this.onResultListener = onResultListener;
        this.onResultListener.onSuccess(this.dataDownloaded);
    }


    public void setUrl(String urlString){
        this.url = urlString;
    }
    public void setInterfaceService (String className){this.className=className;}
    public interface OnResultListener {
         void onSuccess(List<Result> response);
         void onError(String errorMessage);
    }

}

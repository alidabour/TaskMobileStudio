package com.example.ali.test.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by Ali on 2/8/2017.
 */

public class Movie implements Parcelable,DataModel{
    private String posterUrl;
    private String title;
    private String author;
    private String content;

    public Movie(){

    }
    public Movie(HashMap<String,String> data){
        this.posterUrl = data.get("poster_path");
        this.title = data.get("title");
        this.author = data.get("author");
        this.content = data.get("content");
    }
    public Movie(Parcel in) {
        this.posterUrl = in.readString();
        this.title = in.readString();
        this.author = in.readString();
        this.content = in.readString();
    }
    @Override
    public void setData(HashMap<String,String> data){
        this.posterUrl = data.get("poster_path");
        this.title = data.get("title");
        this.author = data.get("author");
        this.content = data.get("content");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterUrl);
        dest.writeString(this.title);
        dest.writeString(this.author);
        dest.writeString(this.content);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

//    public void setPosterUrl(String posterUrl) {
//        this.posterUrl = posterUrl;
//    }
    public String getPosterUrl(){
        return this.posterUrl;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getContent() {
        return content;
    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
}
package com.example.ali.test;

import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Ali on 2/8/2017.
 */

public class Movie implements Parcelable{
    private String posterUrl;
    private String title;

    public Movie(){

    }
    public Movie(Parcel in) {
        this.posterUrl=in.readString();
        this.title=in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterUrl);
        dest.writeString(this.title);

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

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    public String getPosterUrl(){
        return this.posterUrl;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}

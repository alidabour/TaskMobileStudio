package com.example.ali.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ali on 2/8/2017.
 */

public class Movie implements Parcelable{
    private String posterUrl;

    public Movie(){

    }
    public Movie(Parcel in) {
        this.posterUrl=in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterUrl);
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
}

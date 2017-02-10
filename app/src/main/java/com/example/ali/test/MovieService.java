package com.example.ali.test;


import com.example.ali.test.model.Example;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ali on 2/9/2017.
 */

public interface MovieService {
    @GET("movie?")
    Observable<Example> getMovieData(@Query("sort_by")String sortType,
                                     @Query("api_key")String api);
}

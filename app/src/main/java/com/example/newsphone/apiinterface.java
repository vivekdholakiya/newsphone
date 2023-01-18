package com.example.newsphone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiinterface {

    String baseurl="https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<newsmain> getnews(
            @Query("country") String country,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apikey

    );
    @GET("top-headlines")
    Call<newsmain> getcategoriesnews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apikey

    );



}

package com.example.notapp.Service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NoticiasApi {
    @GET("/v2/top-headlines")
    Call<ResponseNoticia> getAllNoticia(@Query("apiKey") String apiKey, @Query("country") String country, @Query("pageSize") int pageSize);
    @GET("/v2/top-headlines")
    Call<ResponseNoticia> getAllNoticia(@Query("apiKey") String apiKey, @Query("country") String country, @Query("pageSize") int pageSize, @Query("category") String category);
}

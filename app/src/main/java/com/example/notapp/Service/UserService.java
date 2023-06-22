package com.example.notapp.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserService {
    private static final String BASE_URL = "https://reqres.in/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static UserAPI userAPI = retrofit.create(UserAPI.class);

    public static UserAPI getUserAPI() {
        return userAPI;
    }
}

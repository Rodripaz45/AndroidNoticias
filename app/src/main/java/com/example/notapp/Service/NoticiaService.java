package com.example.notapp.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticiaService {
    private static final String BASE_URL = "https://newsapi.org/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static NoticiasApi noticiasApi = retrofit.create(NoticiasApi.class);

    public static NoticiasApi getNoticiasApi() {
        return noticiasApi;
    }
}

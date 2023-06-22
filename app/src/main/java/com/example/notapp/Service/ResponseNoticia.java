package com.example.notapp.Service;

import com.example.notapp.Models.Articles;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseNoticia {
    @SerializedName("articles")
    public List<Articles> articles = new ArrayList<>();

    public List<Articles> getArticles() {
        return articles;
    }
}

package com.example.notapp.Models;
import java.util.ArrayList;
import java.util.List;

public class ArticlesSingleton {
    private static ArticlesSingleton instance;
    private List<Articles> articlesList;

    private ArticlesSingleton() {
        articlesList = new ArrayList<>();
    }

    public static ArticlesSingleton getInstance() {
        if (instance == null) {
            instance = new ArticlesSingleton();
        }
        return instance;
    }

    public void addArticle(Articles article) {
        articlesList.add(article);
    }

    public List<Articles> getArticlesList() {
        return articlesList;
    }
}


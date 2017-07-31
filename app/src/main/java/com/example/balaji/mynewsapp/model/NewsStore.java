package com.example.balaji.mynewsapp.model;

import java.util.ArrayList;
import java.util.List;


public class NewsStore {
    private static List<com.example.balaji.mynewsapp.model.Article> article = new ArrayList<>();

    public static List<com.example.balaji.mynewsapp.model.Article> getArticles() {
        return article;
    }

    public static void setArticles(List<com.example.balaji.mynewsapp.model.Article> articles) {
        NewsStore.article = articles;
    }
}

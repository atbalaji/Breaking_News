package com.example.balaji.mynewsapp.newsapi;

import com.example.balaji.mynewsapp.model.ArticleResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;



public class NewsAPI {
    private static final String APIKEY = "b94aa64252d44ee0acf8dfebe0734281";
    private static final String APIPATH = "https://newsapi.org/v1/";

    private static NewsService newsService = null;

    public static NewsService getApi() {
        if (newsService == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIPATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            newsService = retrofit.create(NewsService.class);
        }

        return newsService;
    }

    public interface NewsService {

        @GET("articles?apiKey=" + APIKEY)
        Call<ArticleResponse> getArticles(@Query("source") String source, @Query("sortBy") String sortBy);
    }
}

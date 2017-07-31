package com.example.balaji.mynewsapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.balaji.mynewsapp.adapter.CustomAdapter;
import com.example.balaji.mynewsapp.model.ArticleResponse;
import com.example.balaji.mynewsapp.model.NewsStore;
import com.example.balaji.mynewsapp.newsapi.NewsAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        newsRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerview);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<ArticleResponse> call = NewsAPI.getApi().getArticles("cnbc", "top");
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                ArticleResponse getArticlesResponse = response.body();
                NewsStore.setArticles(getArticlesResponse.getArticles());
                customAdapter = new CustomAdapter(getArticlesResponse.getArticles());
                newsRecyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<ArticleResponse> calls = NewsAPI.getApi().getArticles("cnbc", "top");
                calls.enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        ArticleResponse getArticlesResponse = response.body();
                        NewsStore.setArticles(getArticlesResponse.getArticles());
                        customAdapter = new CustomAdapter(getArticlesResponse.getArticles());
                        newsRecyclerView.setAdapter(customAdapter);
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}

package com.example.balaji.mynewsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private static final String news_index = "news_index";
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.mycolor)));

        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        int received_index = getIntent().getIntExtra(news_index, -1);
        if (received_index != -1) {
            updateNewsDetails(received_index);
        } else {
            Toast.makeText(this, "Sorry incorrect index passed", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateNewsDetails(int index) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SecondActivity.this, "Error in loading webpage", Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl(com.example.balaji.mynewsapp.model.NewsStore.getArticles().get(index).getUrl());
        getSupportActionBar().setTitle(com.example.balaji.mynewsapp.model.NewsStore.getArticles().get(index).getTitle());
    }

    public static void launch(Context context, int index) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(news_index, index);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

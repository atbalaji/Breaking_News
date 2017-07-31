package com.example.balaji.mynewsapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.balaji.mynewsapp.SecondActivity;
import com.example.balaji.mynewsapp.R;
import com.example.balaji.mynewsapp.model.Article;
import com.example.balaji.mynewsapp.activities.DateUtils;

import java.util.List;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Article> articleList;

    public CustomAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Article article = articleList.get(position);

        Glide.with(holder.cardNewsImage.getContext())
                .load(article.getUrlToImage())
                .centerCrop()
                .into(holder.cardNewsImage);
        holder.cardNewsTitle.setText(article.getTitle());
        holder.cardNewsTime.setText(DateUtils.formatNewsApiDate(article.getPublishedAt()));
        holder.cardNewsContent.setText(article.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.launch(v.getContext(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cardNewsImage;
        TextView cardNewsTitle;
        TextView cardNewsTime;
        TextView cardNewsContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardNewsImage = (ImageView) itemView.findViewById(R.id.card_news_image);
            cardNewsTitle = (TextView) itemView.findViewById(R.id.card_news_title);
            cardNewsTime = (TextView) itemView.findViewById(R.id.card_news_time);
            cardNewsContent = (TextView) itemView.findViewById(R.id.card_news_content);
        }
    }
}

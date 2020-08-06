package com.example.finalcloudproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalcloudproject.Models.News;
import com.example.finalcloudproject.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    ArrayList<News> listNews;
    Context context;

    public NewsAdapter(ArrayList<News> listNews, Context context) {
        this.listNews = listNews;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final News news = listNews.get(position);
        holder.title.setText(news.getTitle());
        holder.date.setText(news.getDate());
        holder.description.setText(news.getDescription());
        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(news.getURL()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, description;
        Button url;
        //ImageView image_URL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.description);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date_tv);
            url = itemView.findViewById(R.id.url);
            // image_URL = itemView.findViewById(R.id.imageURL);
        }


    }
}

package com.example.finalcloudproject.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalcloudproject.Models.Advice;
import com.example.finalcloudproject.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.ViewHolder> {
    Context context;
    ArrayList<Advice> advice;

    public AdviceAdapter(Context context, ArrayList advice) {
        this.context = context;
        this.advice = advice;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advice_layout, parent, false);
        return new AdviceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Advice advice_object = advice.get(position);
        holder.advice_tv.setText(advice_object.getadvice_text());
        Uri url = Uri.parse(advice_object.getImage());
        Glide.with(context).load(url).into(holder.advice_image);

    }

    @Override
    public int getItemCount() {
        return advice.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView advice_tv;
        ImageView advice_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            advice_tv = itemView.findViewById(R.id.advice_tv);
            advice_image = itemView.findViewById(R.id.advice_image);
        }
    }
}

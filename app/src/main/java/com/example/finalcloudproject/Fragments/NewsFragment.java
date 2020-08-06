package com.example.finalcloudproject.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalcloudproject.Adapters.NewsAdapter;
import com.example.finalcloudproject.Models.News;
import com.example.finalcloudproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NewsFragment extends Fragment {
    RequestQueue mQueue;
    RecyclerView recyclerNews;
    ArrayList<News> listNews;
    NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        mQueue = Volley.newRequestQueue(getActivity());

        jsonPares();

        recyclerNews = view.findViewById(R.id.recyclerNews);
        recyclerNews.setHasFixedSize(true);
        recyclerNews.setLayoutManager(new LinearLayoutManager(getContext()));
        listNews = new ArrayList<>();
        return view;
    }

    private void jsonPares() {
        String url = "http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=a95e674236bf4dd1840242fc20e42c8b";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("articles");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        News news = new News(jsonObject.get("title").toString(), jsonObject.get("url").toString(),
                                jsonObject.get("description").toString(), jsonObject.get("urlToImage").toString(),
                                jsonObject.get("publishedAt").toString());

                        listNews.add(news);
                        newsAdapter = new NewsAdapter(listNews, getContext());
                        recyclerNews.setAdapter(newsAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Json", error.getMessage());
                Log.d("Json", "GG");
            }
        });
        mQueue.add(request);

    }
    //                        Log.d("Json","Author : " +jsonObject.get("author"));
    //                        Log.d("Json","title : " +);
    //                        Log.d("Json","description : " +);
    //                        Log.d("Json","urlToImage : " +);
    //                        Log.d("Json","publishedAt : " + );
    //                        Log.d("Json","URL : "+ );


}

package com.example.finalcloudproject.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalcloudproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;


public class StateFragment extends Fragment {
    RequestQueue mQueue;
    TextView number, date,death_cases,currently_infected ,recovery_cases;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_state, container, false);
        mQueue = Volley.newRequestQueue(getActivity());
        date = view.findViewById(R.id.date);
        number = view.findViewById(R.id.totalConfirmedCases);
        death_cases=view.findViewById(R.id.death_cases);
        currently_infected=view.findViewById(R.id.currently_infected);
        recovery_cases=view.findViewById(R.id.recovery_cases);

        jsonPares();

        return view;
    }

    private void jsonPares() {
        String url = "https://corona-virus-stats.herokuapp.com/api/v1/cases/general-stats";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("data");


                    number.setText(jsonObject.getString("total_cases"));
                    date.setText(jsonObject.getString("last_update"));
                    recovery_cases.setText(jsonObject.getString("recovery_cases"));
                    death_cases.setText(jsonObject.getString("death_cases"));
                    currently_infected.setText(jsonObject.getString("currently_infected"));


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
}

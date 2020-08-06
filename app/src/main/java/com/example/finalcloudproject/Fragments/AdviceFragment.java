package com.example.finalcloudproject.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalcloudproject.Adapters.AdviceAdapter;
import com.example.finalcloudproject.Models.Advice;
import com.example.finalcloudproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdviceFragment extends Fragment {

    RecyclerView recyclerAdvice;
    FirebaseFirestore db;
    private StorageReference mStorageRef;
    ArrayList<Advice> advice;
    AdviceAdapter adviceAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advice, container, false);
        advice = new ArrayList<>();
        //this method for receive data 
        receiveData();
        //this recycler view for view advice from fire base using fire store
        recyclerAdvice = view.findViewById(R.id.recyclerAdvice);
        recyclerAdvice.setHasFixedSize(true);
        recyclerAdvice.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private void receiveData() {

        db = FirebaseFirestore.getInstance();
        db.collection("Advice")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();
                                advice.add(new Advice((String) data.get("advice_text"), (String) data.get("advice_image")));

//                                String intro = (String) data.get("intro");
//                                String image = (String) data.get("intro_image");
//                                infoText.setText(intro);
//                                image_info.setImageURI(Uri.parse(image));
//                                Log.d("info", document.getId() + " => " + image);
                            }
                            adviceAdapter = new AdviceAdapter(getContext(), advice);
                            recyclerAdvice.setAdapter(adviceAdapter);
                        } else {
                            Log.w("info", "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}

package com.example.finalcloudproject.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalcloudproject.R;
import com.example.finalcloudproject.VideoActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Info_COV_Fragment extends Fragment {

    FirebaseFirestore db;
    ImageView image_info;
    TextView infoText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info__c_o_v_, container, false);
        image_info = view.findViewById(R.id.image_info);
        infoText = view.findViewById(R.id.info_cov);
        //intent to move on video screen
        view.findViewById(R.id.covVideo_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), VideoActivity.class);
                startActivity(intent);
            }
        });
//        method read data for static data
        readData();
        return view;
    }

    public void readData() {
        db = FirebaseFirestore.getInstance();
        db.collection("info")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();
                                String intro = (String) data.get("intro");
                                String image = (String) data.get("intro_image");
                                infoText.setText(intro);
                                Uri url = Uri.parse(image);
                                Glide.with(getContext()).load(url).into(image_info);
                                Log.d("info", document.getId() + " => " + image);
                            }
                        } else {
                            Log.w("info", "Error getting documents.", task.getException());
                        }
                    }
                });
    }


}
//localFile = null;
//        try {
//            localFile = File.createTempFile("intro", "jpg");
//            mStorageRef.getFile(localFile)
//                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                            my_image = BitmapFactory.decodeFile(localFile.getAbsolutePath());
//                            image_info.setImageBitmap(my_image);
//                            Log.d("info", "SSSSSSSSSSSSSSSSSS");
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

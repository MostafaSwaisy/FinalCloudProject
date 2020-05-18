package com.example.finalcloudproject.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalcloudproject.Models.Info;
import com.example.finalcloudproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Info_COV_Fragment extends Fragment {

  FirebaseFirestore db;

    ImageView image_info;
    TextView infoText;
    private StorageReference mStorageRef;

    Bitmap my_image;

    File localFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_info__c_o_v_,container,false);
        image_info = view.findViewById(R.id.image_info);
        infoText = view.findViewById(R.id.info_cov);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("intro/intro.jpg");


        localFile = null;
        try {
            localFile = File.createTempFile("intro", "jpg");
            mStorageRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            my_image = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            image_info.setImageBitmap(my_image);
                            Log.d("info" , "SSSSSSSSSSSSSSSSSS" );
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        db = FirebaseFirestore.getInstance();
        db.collection("info")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              Map<String , Object> data = document.getData();
                             String intro = (String) data.get("intro");
                             String image = (String) data.get("intro_image");
                                infoText.setText(intro);
                                image_info.setImageURI(Uri.parse(image));
                                Log.d("info", document.getId() + " => " + image);
                            }
                        } else {
                            Log.w("info", "Error getting documents.", task.getException());
                        }
                    }
                });




        return view;
    }
}

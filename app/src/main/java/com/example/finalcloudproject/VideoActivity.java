package com.example.finalcloudproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Map;

public class VideoActivity extends AppCompatActivity {
    FirebaseFirestore db;
    VideoView covVideo;
    Uri videoUri ;
    ProgressBar bufferProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.setRequestedOrientation(VideoActivity.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_video);

        db = FirebaseFirestore.getInstance();
        covVideo = findViewById(R.id.covVideo);
        bufferProgress =findViewById(R.id.bufferProgress);
        startVideo();
        findViewById(R.id.backToHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(VideoActivity.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
    private void startVideo() {
        videoUri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/fcmanalyticshomework.appspot.com/o/video%2F6%20Steps%20to%20Prevent%20COVID-19.mp4?alt=media&token=fadc16d5-14c5-47bb-abce-5cfb079cecf4");
        covVideo.setVideoURI(videoUri);
        covVideo.requestFocus();
        covVideo.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {

                if(i == mediaPlayer.MEDIA_INFO_BUFFERING_START){
                    bufferProgress.setVisibility(View.VISIBLE);
                }else {
                    bufferProgress.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
        covVideo.start();
    }


}

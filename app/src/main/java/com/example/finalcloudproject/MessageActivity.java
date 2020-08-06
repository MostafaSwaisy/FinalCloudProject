package com.example.finalcloudproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.finalcloudproject.Adapters.MessageAdapter;
import com.example.finalcloudproject.Models.Chat;
import com.example.finalcloudproject.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    CircleImageView profile_image;
    TextView username;
    FirebaseUser fireBaseUser;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ImageButton btn_send;
    EditText text_send;
    Intent intent;

    MessageAdapter messageAdapter;
    List<Chat> mchats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        //settings for Action Bar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //ui reference
        profile_image=findViewById(R.id.profile_image);
        username=findViewById(R.id.username);
        text_send=findViewById(R.id.text_send);
        btn_send=findViewById(R.id.btn_send);
        //recieve data from MainActivity (Users Fragment)(the action excute in userAdapter --onbind method--)
        intent=getIntent();
        final String  userid=intent.getStringExtra("userid");
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=text_send.getText().toString();
                if(!TextUtils.isEmpty(message)){
                    sendMessage(fireBaseUser.getUid(),userid,message);
                }else {
                    Toast.makeText(MessageActivity.this, "You can't send an empty message?!", Toast.LENGTH_SHORT).show();
                }
                text_send.setText("");
            }
        });
        //make recycler view stand by and give it adapter
        recyclerView=findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout=new LinearLayoutManager(getApplicationContext());
        linearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayout);

        //get user inf from firebase
        fireBaseUser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user =dataSnapshot.getValue(User.class);
                username.setText(user.getUsername());
                profile_image.setImageResource(R.mipmap.ic_launcher);

                readMessages(fireBaseUser.getUid(),userid,"default");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void sendMessage(String sender,String receiver,String message){
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("");
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);
        reference.child("chats").push().setValue(hashMap);

    }
    private void  readMessages(final String myid, final String userid, final String imageUrl){

        mchats=new ArrayList<>();
        reference=FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchats.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Chat chat=snapshot.getValue(Chat.class);
                    if(chat.getReceiver().equals(myid)&&chat.getSender().equals(userid)
                            || chat.getReceiver().equals(userid)&&chat.getSender().equals(myid)){
                        mchats.add(chat);
                    }
                    messageAdapter=new MessageAdapter(MessageActivity.this,mchats,imageUrl);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

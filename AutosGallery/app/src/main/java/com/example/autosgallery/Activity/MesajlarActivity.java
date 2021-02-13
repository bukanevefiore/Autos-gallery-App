package com.example.autosgallery.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.autosgallery.Adapters.MesajlarAdapter;
import com.example.autosgallery.Dialog.OtherId;
import com.example.autosgallery.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MesajlarActivity extends AppCompatActivity {

    List<String> otherIdList;
    String userId;
    SharedPreferences sharedPreferences;
    DatabaseReference reference;
    MesajlarAdapter mesajlarAdapter;
    ListView mesajlarListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesajlar);
        tanimlamalar();
        listele();
    }

    public void tanimlamalar(){
        sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
        userId= sharedPreferences.getString("uye_id",null);
        reference= FirebaseDatabase.getInstance().getReference();
        otherIdList=new ArrayList<>();
        mesajlarAdapter=new MesajlarAdapter(otherIdList,userId,getApplicationContext(),MesajlarActivity.this);
        mesajlarListView=findViewById(R.id.mesajlarListView);
        mesajlarListView.setAdapter(mesajlarAdapter);
    }
    public void listele(){
        reference.child("messages").child(userId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.i("mesajlar",snapshot.getKey());
                otherIdList.add(snapshot.getKey()); // tüm mesajlaşılan kişi id lerini other liste atıyoruz
                mesajlarAdapter.notifyDataSetChanged(); // mesajlar adapter güncelleme
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
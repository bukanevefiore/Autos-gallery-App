package com.example.autosgallery.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.autosgallery.Dialog.OtherId;
import com.example.autosgallery.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    String userId, otherId, usertable, othertable, key;
    SharedPreferences sharedPreferences;
    DatabaseReference reference;
    EditText mesajEditText;
    Button sendMesajButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        tanimlamalar();
        action();
    }
    public void tanimlamalar(){

        mesajEditText=findViewById(R.id.mesajEditText);
        sendMesajButon=findViewById(R.id.sendMesajButon);
        reference= FirebaseDatabase.getInstance().getReference();
        otherId= OtherId.getOtherId();  // otherid mizi OtherId class ımızdan alıyoruz
        // uyeid yi alıyoruz
        sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
        userId= sharedPreferences.getString("uye_id",null);
    }

    public void action(){
        sendMesajButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(mesajEditText.getText().toString(),userId,otherId);
            }
        });
    }

    public void sendMessage(String mesajbody, String usrId,String othId){

        usertable="messages/"+userId+"/"+otherId;
        othertable="messages/"+otherId+"/"+userId;
        key= reference.child("messages").child(usertable).child(othertable).push().getKey();

        Log.i("keyim",key);
        Map map=send(mesajbody,usrId,othId);
        Map map2=new HashMap();
        map2.put(usertable+"/"+key,map);
        map2.put(othertable+"/"+key,map);

        mesajEditText.setText("");  // mesajı gönderdikten sonra edit texti null yaparız
        reference.updateChildren(map2, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

            }
        });


    }
    public Map send(String mesajbody,String userId, String otherId){
        Map msj=new HashMap();
        msj.put("mesaj",mesajbody);
        msj.put("from",userId);
        msj.put("to",otherId);
        return msj;
    }
}
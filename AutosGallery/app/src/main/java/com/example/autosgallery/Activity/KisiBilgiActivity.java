package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.autosgallery.Models.KisiBilgiUpdatePojo;
import com.example.autosgallery.Models.UserPojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KisiBilgiActivity extends AppCompatActivity {

    EditText userbilgi_username,userbilgi_pass;
    Button userbilgi_buton;
    SharedPreferences sharedPreferences;
    String uye_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisi_bilgi);

        tanimlamalar();
        sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
        uye_id=sharedPreferences.getString("uye_id",null);
        istekAt(uye_id);
    }
    public void tanimlamalar(){
        userbilgi_username=findViewById(R.id.userbilgi_username);
        userbilgi_pass=findViewById(R.id.userbilgi_pass);
        userbilgi_buton=findViewById(R.id.userbilgi_buton);
        userbilgi_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kisiUpdate(uye_id,userbilgi_username.getText().toString(),userbilgi_pass.getText().toString());
            }
        });


    }

    public void istekAt(String uye_id){
        Call<UserPojo> request= ManagerAll.getInstance().getInformation(uye_id);
        request.enqueue(new Callback<UserPojo>() {
            @Override
            public void onResponse(Call<UserPojo> call, Response<UserPojo> response) {
                if(response.isSuccessful()){
                    userbilgi_username.setText(response.body().getKadi());
                    userbilgi_pass.setText(response.body().getSifre());
                    Log.i("username",response.body().getKadi());
                }
            }

            @Override
            public void onFailure(Call<UserPojo> call, Throwable t) {

            }
        });
    }

    public void kisiUpdate(String userid,String user,String pass){
        Call<KisiBilgiUpdatePojo> request=ManagerAll.getInstance().kisiUpdate(userid,user,pass);
        request.enqueue(new Callback<KisiBilgiUpdatePojo>() {
            @Override
            public void onResponse(Call<KisiBilgiUpdatePojo> call, Response<KisiBilgiUpdatePojo> response) {
                if(response.body().isTf()){
                    Intent intent=new Intent(KisiBilgiActivity.this,KisiBilgiActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<KisiBilgiUpdatePojo> call, Throwable t) {

            }
        });
    }
}
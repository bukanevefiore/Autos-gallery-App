package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autosgallery.Models.DogrulamaPojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogrulamaActivity extends AppCompatActivity {

    private String email,dogrulama_kodu;
    private EditText eMailView, dogrulamakodu;
    private View mProgressView;
    private View mLoginFormView;
    private Button emailDogrulamaButton;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogrulama);
        tanimlamalar();
        emailDogrulamaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dogrula(email,dogrulama_kodu);
            }
        });
    }

    public void tanimlamalar(){

        eMailView=findViewById(R.id.dogrulama_email);
        dogrulamakodu=findViewById(R.id.dogrulama_kodu);
        mProgressView=findViewById(R.id.dogrulama_progress);
        mLoginFormView=findViewById(R.id.dogrulama_form);
        emailDogrulamaButton=findViewById(R.id.dogrula_button);

        Bundle bundle=getIntent().getExtras();
        dogrulama_kodu=String.valueOf(bundle.getInt("kod"));
        email=bundle.getString("email");
        Toast.makeText(getApplicationContext(),dogrulama_kodu,Toast.LENGTH_LONG).show();
        eMailView.setText(email);

    }
    public void dogrula(String ad, String kod){

        email=eMailView.getText().toString();
        dogrulama_kodu=dogrulamakodu.getText().toString();


        final Call<DogrulamaPojo> request= ManagerAll.getInstance().dogrula(ad, kod);
        request.enqueue(new Callback<DogrulamaPojo>() {
            @Override
            public void onResponse(Call<DogrulamaPojo> call, Response<DogrulamaPojo> response) {

               if(response.body().isTf()==true){

                    String uye_id=response.body().getId().toString();
                    String kullaniciAdi=response.body().getKadi().toString();
                    sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("uye_id",uye_id);
                    editor.putString("uye_KullaniciAdi",kullaniciAdi);
                    editor.commit();


                    Intent intent=new Intent(DogrulamaActivity.this, MainActivity.class);
                    startActivity(intent);


                    //Log.i("sonuccc",uye_id+kullaniciAdi);
               }
                else
                {
                   // Log.d("sonuc",);
                }
            }

            @Override
            public void onFailure(Call<DogrulamaPojo> call, Throwable t) {

                Intent intent=new Intent(DogrulamaActivity.this, MainActivity.class);
                startActivity(intent);

                Log.d("retrofit2",t.getMessage());
                Log.d("hata","HATA VAR");
            }
        });
    }
}
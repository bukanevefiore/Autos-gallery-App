package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.autosgallery.Models.LoginPojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity  {
    private static final int REQUEST_READ_CONTACTS=0;


    // bir kere kayıt olduktan sonra uygulamanın verilerini temizlemediğimiz sürece
    // giriş işlemi istenmemesini sağlar
    SharedPreferences sharedPreferences;

    private static final String[] DUMMY_CREDENTIALS=new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };


    private String email,password;
    private EditText eMailView, mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button emailSignInButton,kontrol;
    private TextView registerButon;
    String uyeid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
        if (sharedPreferences.getString("uye_id",null) != null && sharedPreferences.getString("uye_KullaniciAdi", null) !=null)
        {
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }



        tanimlamalar();
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Giriş");
        progressDialog.setMessage("Yükleniyor..");
        progressDialog.setCancelable(false);

        emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //progressDialog.show();


                login(email,password);
                //progressDialog.cancel();
             /*
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
             */
            }


        });
        registerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    public void tanimlamalar(){

        eMailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        emailSignInButton = findViewById(R.id.sign_in_button);
        mLoginFormView=findViewById(R.id.login_form);
        mProgressView=findViewById(R.id.login_progress);
        registerButon=findViewById(R.id.register);

    }


    public void login(String ad,String sifre){

        email=eMailView.getText().toString();
        password=mPasswordView.getText().toString();

        final Call<LoginPojo> request= ManagerAll.getInstance().login(ad, sifre);
        request.enqueue(new Callback<LoginPojo>() {
            @Override
            public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                if(response.isSuccessful()){
                    if(response.body().getKadi() != null && response.body().getId() != null){

                        String uyeId=response.body().getId().toString();
                        String kullaniciAdi=response.body().getKadi().toString();

                        // bir kere login girişi yaptıktan sonra uygulama ön belleğini
                        // temizlemediğimiz sürece tekrar login girişi istemez(sharedpreferences)
                        sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("uye_id",uyeId);
                        editor.putString("uye_KullaniciAdi",kullaniciAdi);
                        editor.commit();

                        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);


                    }
                }


            }

            @Override
            public void onFailure(Call<LoginPojo> call, Throwable t) {
               // Toast.makeText(getApplicationContext(),"bilgilerinizi kontrol ediniz",Toast.LENGTH_LONG).show();
                Log.d("retrofit2",t.getMessage());
            }
        });
    }



}
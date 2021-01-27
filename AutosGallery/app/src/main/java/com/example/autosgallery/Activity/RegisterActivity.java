package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autosgallery.Models.RegisterPojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private String email,password;
    private EditText eMailView, mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button emailSignUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tanimlamalar();



        emailSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email=eMailView.getText().toString();
                password=mPasswordView.getText().toString();
                register(email,password);





            }


        });
    }

    public void tanimlamalar(){
        eMailView=findViewById(R.id.email);
        mPasswordView=findViewById(R.id.password);
        mProgressView=findViewById(R.id.login_progress);
        mLoginFormView=findViewById(R.id.login_form);
        emailSignUpButton=findViewById(R.id.sign_up_button);
    }

    public void register(String ad, String sifre){
        Call<RegisterPojo> request= ManagerAll.getInstance().register(ad, sifre);
        request.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                Toast.makeText(getApplicationContext(),response.body().getResult(),Toast.LENGTH_LONG).show();
                email=eMailView.getText().toString();
                Intent intent=new Intent(RegisterActivity.this, DogrulamaActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("kod",response.body().getDogrulamaKodu());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {

                Log.d("retrofit2",t.getMessage());

            }
        });
    }


}
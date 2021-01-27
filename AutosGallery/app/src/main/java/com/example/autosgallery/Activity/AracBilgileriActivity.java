package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autosgallery.Models.IlanVerPojo;
import com.example.autosgallery.R;

public class AracBilgileriActivity extends AppCompatActivity {

    EditText markaBilgiEditText,seriBilgiEditText,modelBilgiEditText,yilBilgiEditText,kmBilgiEditText;
    Button aracBilgisiButon,aracBilgisiButonGeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_bilgileri);
        tanimlamalar();
    }

    public void tanimlamalar(){
        markaBilgiEditText=findViewById(R.id.markaBilgiEditText);
        seriBilgiEditText=findViewById(R.id.seriBilgiEditText);
        modelBilgiEditText=findViewById(R.id.modelBilgiEditText);
        yilBilgiEditText=findViewById(R.id.yilBilgiEditText);
        kmBilgiEditText=findViewById(R.id.kmBilgiEditText);
        //editText lerin boş set işlemleri
        markaBilgiEditText.setText(IlanVerPojo.getMarka());
        seriBilgiEditText.setText(IlanVerPojo.getSeri());
        modelBilgiEditText.setText(IlanVerPojo.getModel());
        yilBilgiEditText.setText(IlanVerPojo.getYil());
        kmBilgiEditText.setText(IlanVerPojo.getKm());
        // buton tıklama işlemleri
        aracBilgisiButon=findViewById(R.id.aracBilgisiButon);
        aracBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!markaBilgiEditText.getText().toString().equals("") && !seriBilgiEditText.getText().toString().equals("") && !modelBilgiEditText.getText().toString().equals("") && !yilBilgiEditText.getText().toString().equals("") && !kmBilgiEditText.getText().toString().equals("")) {
                    //edittext lerin dolu set işlemleri
                    IlanVerPojo.setMarka(markaBilgiEditText.getText().toString());
                    IlanVerPojo.setSeri(seriBilgiEditText.getText().toString());
                    IlanVerPojo.setModel(modelBilgiEditText.getText().toString());
                    IlanVerPojo.setYil(yilBilgiEditText.getText().toString());
                    IlanVerPojo.setKm(kmBilgiEditText.getText().toString());

                    Intent intent = new Intent(AracBilgileriActivity.this, MotorPerformansActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Tüm bilgileri girin",Toast.LENGTH_LONG).show();
                }
            }
        });
        aracBilgisiButonGeri=findViewById(R.id.aracBilgisiButonGeri);
        aracBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AracBilgileriActivity.this,AdresBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });

    }
}
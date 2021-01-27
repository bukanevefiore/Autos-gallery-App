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

public class AdresBilgileriActivity extends AppCompatActivity {

    Button adresBilgisiButon,adresBilgisiButonGeri;
    EditText sehirBilgiEditText,ilceBilgiEditText,mahalleBilgiEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adres_bilgileri);
        tanimlamalar();
    }

    public void tanimlamalar(){
        sehirBilgiEditText=findViewById(R.id.sehirBilgiEditText);
        ilceBilgiEditText=findViewById(R.id.ilceBilgiEditText);
        mahalleBilgiEditText=findViewById(R.id.mahalleBilgiEditText);
        // editText lerin boş işlemleri
        sehirBilgiEditText.setText(IlanVerPojo.getSehir());
        ilceBilgiEditText.setText(IlanVerPojo.getIlce());
        mahalleBilgiEditText.setText(IlanVerPojo.getMahalle());

        adresBilgisiButon=findViewById(R.id.adresBilgisiButon);
        adresBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sehirBilgiEditText.getText().toString().equals("") && !ilceBilgiEditText.getText().toString().equals("") && !mahalleBilgiEditText.getText().toString().equals("")) {
                   // edittext dolu set işlemleri
                    IlanVerPojo.setSehir(sehirBilgiEditText.getText().toString());
                    IlanVerPojo.setIlce(ilceBilgiEditText.getText().toString());
                    IlanVerPojo.setMahalle(mahalleBilgiEditText.getText().toString());

                    Intent intent = new Intent(AdresBilgileriActivity.this, AracBilgileriActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Tüm bilgileri girin",Toast.LENGTH_LONG).show();
                }
            }
        });
        adresBilgisiButonGeri=findViewById(R.id.adresBilgisiButonGeri);
        adresBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdresBilgileriActivity.this,IlanTuruActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });

    }
}
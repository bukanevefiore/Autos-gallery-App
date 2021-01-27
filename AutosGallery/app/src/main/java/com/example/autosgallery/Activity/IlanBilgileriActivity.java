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

public class IlanBilgileriActivity extends AppCompatActivity {

    EditText ilanAciklamaEditText,ilanBaslikEditText,ilanFiyatEditText;
    Button ilanBilgisiButon,ilanBilgisiButonGeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_bilgileri);
        tanimlamalar();
    }

    public void tanimlamalar(){
        ilanAciklamaEditText=findViewById(R.id.ilanAciklamaEditText);
        ilanBaslikEditText=findViewById(R.id.ilanBaslikEditText);
        ilanBilgisiButon=findViewById(R.id.ilanBilgisiButon);
        ilanFiyatEditText=findViewById(R.id.ilanFiyatEditText);

        // Bir sonraki adıma gittikten sonra tekrar bu activity e geldiğimizde doldurduğumuz kısımların silinmemesi için
          // boş set işlemi
        ilanAciklamaEditText.setText(IlanVerPojo.getAciklama());
        ilanBaslikEditText.setText(IlanVerPojo.getBaslik());
        ilanFiyatEditText.setText(IlanVerPojo.getUcret());

        ilanBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ilanAciklamaEditText.getText().toString().equals("") && !ilanBaslikEditText.getText().toString().equals("") && !ilanFiyatEditText.getText().toString().equals("")) {
                    // alınan verileri edittextlerde kalsın diye dolu set ediyoruz
                    // dolu set
                    IlanVerPojo.setAciklama(ilanAciklamaEditText.getText().toString());
                    IlanVerPojo.setBaslik(ilanBaslikEditText.getText().toString());
                    IlanVerPojo.setUcret(ilanFiyatEditText.getText().toString());

                    Intent intent = new Intent(IlanBilgileriActivity.this, IlanTuruActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Tüm bilgileri girin",Toast.LENGTH_LONG).show();
                }
            }
        });
        ilanBilgisiButonGeri=findViewById(R.id.ilanBilgisiButonGeri);
        ilanBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IlanBilgileriActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });

    }
}
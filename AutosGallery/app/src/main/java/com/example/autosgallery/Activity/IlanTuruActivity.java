package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.autosgallery.Models.IlanVerPojo;
import com.example.autosgallery.R;

import java.util.ArrayList;
import java.util.List;

public class IlanTuruActivity extends AppCompatActivity {

    Button ilanTuruButon,ilanTuruButonGeri;
    Spinner ilanturuSpinner,kimdenSpinner;
    List<String> turList;
    List<String> sahipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_turu);
        listeDoldur();
        tanimlamalar();
    }
    public void tanimlamalar(){
        ilanturuSpinner=findViewById(R.id.ilanturuSpinner);
        kimdenSpinner=findViewById(R.id.kimdenSpinner);

        // spinner kullanımı için adapterlar, spinner da işaretlenen seçeneğin alınması
        ArrayAdapter<String> turListAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,turList);
        turListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilanturuSpinner.setAdapter(turListAdapter);

        ArrayAdapter<String> sahipListAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sahipList);
        sahipListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kimdenSpinner.setAdapter(sahipListAdapter);

        // buton tıklanmaları
        ilanTuruButon=findViewById(R.id.ilanTuruButon);
        ilanTuruButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // spinner ların textlerini alma set işlemi
                IlanVerPojo.setKimden(kimdenSpinner.getSelectedItem().toString());
                IlanVerPojo.setIlantipi(ilanturuSpinner.getSelectedItem().toString());
                Intent intent=new Intent(IlanTuruActivity.this,AdresBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                finish();
            }
        });
        ilanTuruButonGeri=findViewById(R.id.ilanTuruButonGeri);
        ilanTuruButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IlanTuruActivity.this,IlanBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });
    }
    public void listeDoldur(){
        turList=new ArrayList<>();
        sahipList=new ArrayList<>();
        turList.add("Satılık");
        turList.add("Kiralık");
        sahipList.add("Sahibinden");
        sahipList.add("Galeriden");
    }


}
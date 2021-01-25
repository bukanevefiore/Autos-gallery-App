package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.autosgallery.Models.ResimEklePojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanResimlerActivity extends AppCompatActivity {

    Button resimSecButon,resimEkleButon,ilanYayinlaButon;
    ImageView secilenIlanResmiImageView;
    Bitmap bitmap;
    String uye_id,ilan_id,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_resimler);
        tanimlamalar();

        // activity arası gönderilen verileri aılmak için bundle oluşturuyoruz
        Bundle bundle=getIntent().getExtras();
        uye_id= String.valueOf(bundle.getInt("uye_id"));
        ilan_id=String.valueOf(bundle.getInt("ilan_id"));
    }
    public void tanimlamalar(){
        resimSecButon=findViewById(R.id.resimSecButon);
        resimEkleButon=findViewById(R.id.resimEkleButon);
        ilanYayinlaButon=findViewById(R.id.ilanYayinlaButon);
        secilenIlanResmiImageView=findViewById(R.id.secilenIlanResmiImageView);

        resimSecButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resimGoster();
            }
        });

        resimEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yukle();

            }
        });
    }

    // galeriyi açma methodu
    public void resimGoster(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction((Intent.ACTION_GET_CONTENT));
        startActivityForResult(intent,777);
    }

    public void yukle(){

        image=imageToString();
        Call<ResimEklePojo> request= ManagerAll.getInstance().resimEkle(uye_id,ilan_id,image);
        request.enqueue(new Callback<ResimEklePojo>() {
            @Override
            public void onResponse(Call<ResimEklePojo> call, Response<ResimEklePojo> response) {
                if(response.body().isTf()){
                    Toast.makeText(getApplicationContext(),response.body().getSonuc(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),response.body().getSonuc(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResimEklePojo> call, Throwable t) {

            }
        });
    }

    // seçilen resmi bitmap e çevirip bu bitmap i image e atıp imageview de gösterilmesi işlemi
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==777 && resultCode==RESULT_OK && data != null){
            Uri path = data.getData();
            try{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                secilenIlanResmiImageView.setImageBitmap(bitmap);
                secilenIlanResmiImageView.setVisibility(View.VISIBLE);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    // resmin base64 stringe çevrilmesi
    public String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] byt=byteArrayOutputStream.toByteArray();
        String imageToString= Base64.encodeToString(byt,Base64.DEFAULT);
        return imageToString;
    }
}
package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.autosgallery.Adapters.IlanlarAdapter;
import com.example.autosgallery.Models.IlanlarPojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanlarActivity extends AppCompatActivity {

    ListView listView;
    List<IlanlarPojo> ilanlarPojoList;
    IlanlarAdapter ilanlarAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlar);

        tanimlamalar();
        ilanlarGoruntule();

        // listeden herhangi bir ilana tıklandığında detay actvivty açılması için intentle paremetre gönderme
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(IlanlarActivity.this,IlanDetayActivity.class);
                intent.putExtra("ilanid",ilanlarPojoList.get(position).getIlanid());
                startActivity(intent);
            }
        });
    }

    public void tanimlamalar(){
        listView=findViewById(R.id.ilanlarListView);


    }

    public void ilanlarGoruntule(){

        // progress dialog
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Tüm İlanlar");
        progressDialog.setMessage("İlanlar yükleniyor..Lütfen bekleyin..");
        progressDialog.setCancelable(false); // iptal edilebilirliği kapatıyor işlem bitince kendi kapanıyor
        progressDialog.show();

        // istek
       Call<List<IlanlarPojo>> request=ManagerAll.getInstance().ilanlar();
       request.enqueue(new Callback<List<IlanlarPojo>>() {
           @Override
           public void onResponse(Call<List<IlanlarPojo>> call, Response<List<IlanlarPojo>> response) {
               if(response.isSuccessful()){
                   if(response.body().get(0).isTf()){
                       ilanlarPojoList=response.body();
                       ilanlarAdapter=new IlanlarAdapter(ilanlarPojoList,getApplicationContext());
                       listView.setAdapter(ilanlarAdapter);

                       // listenin 0. item ı üzerinde sayi değişkenine ulaşarak kişiye ait ilan sayısını alıyoruz
                       Toast.makeText(getApplicationContext(), response.body().get(0).getSayi() + "tane ilan bulunmaktadır.", Toast.LENGTH_LONG).show();
                       // progress dialogu işlem sonunda kapatma
                       progressDialog.cancel();
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(),"ilan Bulunmamaktadır.", Toast.LENGTH_LONG).show();
                       // progress dialogu işlem sonunda kapatma
                       progressDialog.cancel();

                   }
               }
           }

           @Override
           public void onFailure(Call<List<IlanlarPojo>> call, Throwable t) {

           }
       });
    }
}
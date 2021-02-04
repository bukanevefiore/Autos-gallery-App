package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.autosgallery.Adapters.IlanlarimAdapter;
import com.example.autosgallery.Dialog.AlertDialogClass;
import com.example.autosgallery.Models.IlanlarimPojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanlarimActivity extends AppCompatActivity {

    ListView listView;
    IlanlarimAdapter ilanlarimAdapter;
    List<IlanlarimPojo> ilanlarimPojos;
    SharedPreferences sharedPreferences;
    String uye_id;
    AlertDialogClass alertDialogClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);
        tanimlamalar();
        ilanlarimiGoruntule();

        // listview item ına a tıklandığında ilan silme için alertdialog çıkması
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               alertDialogClass=new AlertDialogClass(); // alertdialog tanimlama
               alertDialogClass.ilanlarimAlertDialog(IlanlarimActivity.this,ilanlarimPojos.get(position).getIlanid()); // alertdialog acma
           }
       });
    }
    public void tanimlamalar(){
        listView=findViewById(R.id.ilanlarimListView);
        // uyeid yi almak için
        sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
        uye_id=sharedPreferences.getString("uye_id",null);
    }
    public void ilanlarimiGoruntule(){

        // progress diyolog
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("İlanlarım");
        progressDialog.setMessage("İlanlarınız Yükleniyor, Lütfen Bekleyin..");
        progressDialog.setCancelable(false); // iptal edilebilirliği kapatıyor işlem bitince kendi kapanıyor
        progressDialog.show();


        ilanlarimPojos=new ArrayList<>();
        Call<List<IlanlarimPojo>> request= ManagerAll.getInstance().ilanlarim(uye_id);
        request.enqueue(new Callback<List<IlanlarimPojo>>() {
            @Override
            public void onResponse(Call<List<IlanlarimPojo>> call, Response<List<IlanlarimPojo>> response) {

                if(response.isSuccessful()) {
                    // list olarak dönmesi için respapi de ve managerall da ilanlarımpojoyu list yaptık
                    ilanlarimPojos = response.body();
                    ilanlarimAdapter=new IlanlarimAdapter(ilanlarimPojos,getApplicationContext(),IlanlarimActivity.this);
                    listView.setAdapter(ilanlarimAdapter);
                    // listenin 0. item ı üzerinde sayi değişkenine ulaşarak kişiye ait ilan sayısını alıyoruz
                    Toast.makeText(getApplicationContext(),response.body().get(0).getSayi()+"tane ilanınız bulunmaktadır.",Toast.LENGTH_LONG).show();

                    // progress dialogu işlem sonunda kapatma
                    progressDialog.cancel();

                }
            }

            @Override
            public void onFailure(Call<List<IlanlarimPojo>> call, Throwable t) {

            }
        });

    }
}
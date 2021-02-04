package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.autosgallery.Adapters.IlanlarimAdapter;
import com.example.autosgallery.Dialog.AlertDialogClass;
import com.example.autosgallery.Models.IlanlarimPojo;
import com.example.autosgallery.Models.IlanlarimSilPojo;
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
              ilanlarimAlertDialog(IlanlarimActivity.this,ilanlarimPojos.get(position).getIlanid()); // alertdialog acma
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

                    //ilan listeleme işleminde ilan hiç yoksa ilan bulunmamaktatdır toast mesajı için if else
                    if(response.body().get(0).isTf())
                    {
                        ilanlarimAdapter = new IlanlarimAdapter(ilanlarimPojos, getApplicationContext(), IlanlarimActivity.this);
                        listView.setAdapter(ilanlarimAdapter);
                        // listenin 0. item ı üzerinde sayi değişkenine ulaşarak kişiye ait ilan sayısını alıyoruz
                        Toast.makeText(getApplicationContext(), response.body().get(0).getSayi() + "tane ilanınız bulunmaktadır.", Toast.LENGTH_LONG).show();
                        // progress dialogu işlem sonunda kapatma
                        progressDialog.cancel();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"ilanınız Bulunmamaktadır.", Toast.LENGTH_LONG).show();
                        // progress dialogu işlem sonunda kapatma
                        progressDialog.cancel();

                    }


                }
            }

            @Override
            public void onFailure(Call<List<IlanlarimPojo>> call, Throwable t) {

            }
        });

    }

    // ilan_id üzerinden ilan silme işlemi için method
    // method paremetresinde activity olmasının sebebi oluşturduğumuz alertlayout tasarımını kullanabilmek amaaçlı
    public void ilanlarimAlertDialog(Activity activity, final String ilan_id){
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertlayout,null);

        Button buttonsil=view.findViewById(R.id.butonsil);
        Button buttonCik=view.findViewById(R.id.butonCik);

        AlertDialog.Builder alert=new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog=alert.create();

        buttonCik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        buttonsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilanSil(ilan_id);
                dialog.cancel();
            }
        });
        dialog.show();
    }

    // ilan silem servisine istek ilan silme işlemi
    public void ilanSil(String ilanId){

        Call<IlanlarimSilPojo> request= ManagerAll.getInstance().ilanlarimSil(ilanId);
        request.enqueue(new Callback<IlanlarimSilPojo>() {
            @Override
            public void onResponse(Call<IlanlarimSilPojo> call, Response<IlanlarimSilPojo> response) {
                // silme işlemi gerçekleştikten sonra ilanlisteleme servisine istek atarak ilanların güncel halini listeleme
                if(response.body().isTf()){
                    ilanlarimiGoruntule();
                }

            }

            @Override
            public void onFailure(Call<IlanlarimSilPojo> call, Throwable t) {

                Log.d("ilansilkontrol",t.getMessage());
            }
        });

    }
}
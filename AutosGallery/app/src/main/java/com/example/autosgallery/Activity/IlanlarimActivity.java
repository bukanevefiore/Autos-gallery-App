package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.autosgallery.Adapters.IlanlarimAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);
        tanimlamalar();
        ilanlarimiGoruntule();
    }
    public void tanimlamalar(){
        listView=findViewById(R.id.ilanlarimListView);
        // uyeid yi almak için
        sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
        uye_id=sharedPreferences.getString("uye_id",null);
    }
    public void ilanlarimiGoruntule(){

        ilanlarimPojos=new ArrayList<>();
        Call<List<IlanlarimPojo>> request= ManagerAll.getInstance().ilanlarim(uye_id);
        request.enqueue(new Callback<List<IlanlarimPojo>>() {
            @Override
            public void onResponse(Call<List<IlanlarimPojo>> call, Response<List<IlanlarimPojo>> response) {

                if(response.isSuccessful()) {
                    // list olarak dönmesi için respapi de ve managerall da ilanlarımpojoyu list yaptık
                    ilanlarimPojos = response.body();
                    ilanlarimAdapter=new IlanlarimAdapter(ilanlarimPojos,getApplicationContext());
                    listView.setAdapter(ilanlarimAdapter);

                }else{

                }
            }

            @Override
            public void onFailure(Call<List<IlanlarimPojo>> call, Throwable t) {

            }
        });

    }
}
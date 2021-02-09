package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autosgallery.Adapters.SliderAdapter;
import com.example.autosgallery.Models.FavoriIslemPojo;
import com.example.autosgallery.Models.FavoriKontrolPojo;
import com.example.autosgallery.Models.IlanDetayPojo;
import com.example.autosgallery.Models.SliderPojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanDetayActivity extends AppCompatActivity {

    private TextView ilanDetayBaslik,ilanDetayFiyat,ilanDetayMarka,ilanDetayModel,ilanDetaySeri,ilanDetayYil,ilanDetayIlanTipi,
            ilanDetayKimden,ilanDetayMotorTipi,ilanDetayMotorHacmi,ilanDetaySurat,ilanDetayYakitTipi,ilanDetayOrtYakit,
            ilanDetayDepoHacmi,ilanDetayKm;
    private Button ilanDetayAciklamaButon,ilanDetayFavoriEkleButon;
    private ViewPager ilanDetaySlider;
    String ilanId;
    List<SliderPojo> list;
    SliderAdapter sliderAdapter;   // resimlere hareket özelliği
    CircleIndicator circleIndicator;  // resimleri sağa sola kaydırma noktaları
    SharedPreferences sharedPreferences;
    String uye_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_detay);
        // ilanlar activity dedn gönderilen ilanid yi bundle ile alma
        Bundle bundle=getIntent().getExtras();
        ilanId=bundle.getString("ilanid");
        sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
        uye_id= sharedPreferences.getString("uye_id",null);


        tanimlamalar();
        getIlanDetay();
        getResim();
        getfavoriButonText();
        action();
    }

    public void tanimlamalar(){
        ilanDetayBaslik=findViewById(R.id.ilanDetayBaslik);
        ilanDetayFiyat=findViewById(R.id.ilanDetayFiyat);
        ilanDetayMarka=findViewById(R.id.ilanDetayMarka);
        ilanDetayModel=findViewById(R.id.ilanDetayModel);
        ilanDetaySeri=findViewById(R.id.ilanDetaySeri);
        ilanDetayYil=findViewById(R.id.ilanDetayYil);
        ilanDetayIlanTipi=findViewById(R.id.ilanDetayIlanTipi);
        ilanDetayKimden=findViewById(R.id.ilanDetayKimden);
        ilanDetayMotorTipi=findViewById(R.id.ilanDetayMotorTipi);
        ilanDetayMotorHacmi=findViewById(R.id.ilanDetayMotorHacmi);
        ilanDetaySurat=findViewById(R.id.ilanDetaySurat);
        ilanDetayOrtYakit=findViewById(R.id.ilanDetayOrtYakit);
        ilanDetayYakitTipi=findViewById(R.id.ilanDetayYakitTipi);
        ilanDetayDepoHacmi=findViewById(R.id.ilanDetayDepoHacmi);
        ilanDetayKm=findViewById(R.id.ilanDetayKm);

        ilanDetayAciklamaButon=findViewById(R.id.ilanDetayAciklamaButon);
        ilanDetayFavoriEkleButon=findViewById(R.id.ilanDetayFavoriEkleButon);
        ilanDetaySlider=findViewById(R.id.ilanDetaySlider);

        circleIndicator=findViewById(R.id.sliderNokta);




    }

    public void getIlanDetay(){

        Call<IlanDetayPojo> request= ManagerAll.getInstance().ilanDetay(ilanId);
        request.enqueue(new Callback<IlanDetayPojo>() {
            @Override
            public void onResponse(Call<IlanDetayPojo> call, Response<IlanDetayPojo> response) {

                // set işlemleri
                ilanDetayBaslik.setText(response.body().getBaslik());
                ilanDetayFiyat.setText(response.body().getUcret());
                ilanDetayMarka.setText(response.body().getMarka());
                ilanDetayModel.setText(response.body().getModel());
                ilanDetaySeri.setText(response.body().getSeri());
                ilanDetayYil.setText(response.body().getYil());
                ilanDetayIlanTipi.setText(response.body().getIlantipi());
                ilanDetayKimden.setText(response.body().getKimden());
                ilanDetayMotorTipi.setText(response.body().getMotortipi());
                ilanDetayMotorHacmi.setText(response.body().getMotorhacmi());
                ilanDetaySurat.setText(response.body().getSurat());
                ilanDetayOrtYakit.setText(response.body().getOrtalamayakit());
                ilanDetayYakitTipi.setText(response.body().getYakittipi());
                ilanDetayDepoHacmi.setText(response.body().getDepohacmi());
                ilanDetayKm.setText(response.body().getKm());


                //Log.i("aaaaaaaaaa",uye_id);

            }

            @Override
            public void onFailure(Call<IlanDetayPojo> call, Throwable t) {

                Log.d("ilandetayhata",t.getMessage());
            }
        });
    }

    public void getResim(){
        Call<List<SliderPojo>> request=ManagerAll.getInstance().ilanResimleri(ilanId);
        request.enqueue(new Callback<List<SliderPojo>>() {
            @Override
            public void onResponse(Call<List<SliderPojo>> call, Response<List<SliderPojo>> response) {

                list=response.body();
                sliderAdapter=new SliderAdapter(list,getApplicationContext());
                ilanDetaySlider.setAdapter(sliderAdapter);
                circleIndicator.setViewPager(ilanDetaySlider);  // viewpage i noktalara set etme
                circleIndicator.bringToFront();  // noktaları resmin önine getirme



            }

            @Override
            public void onFailure(Call<List<SliderPojo>> call, Throwable t) {

                Log.d("detayresimhata",t.getMessage());
            }
        });
    }

    public void getfavoriButonText(){

       //ilanDetayFavoriEkleButon.setText(uye_id);
        Call<FavoriKontrolPojo> request=ManagerAll.getInstance().getFavoriButonText(uye_id,ilanId);

        request.enqueue(new Callback<FavoriKontrolPojo>() {
            @Override
            public void onResponse(Call<FavoriKontrolPojo> call, Response<FavoriKontrolPojo> response) {

                if (response.body().isTf())  // istf=true ise
                {
                    ilanDetayFavoriEkleButon.setText(response.body().getText());
                   // ilanDetayFavoriEkleButon.setText("favoriden çıkar");


                } else {
                    ilanDetayFavoriEkleButon.setText(response.body().getText());
                }


            }

            @Override
            public void onFailure(Call<FavoriKontrolPojo> call, Throwable t) {
                Log.d("ilandetayfavorihataa",t.getMessage());
            }
        });


    }

    public void action(){
        ilanDetayFavoriEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<FavoriIslemPojo> request=ManagerAll.getInstance().favoriIslem(uye_id,ilanId);
                request.enqueue(new Callback<FavoriIslemPojo>() {
                    @Override
                    public void onResponse(Call<FavoriIslemPojo> call, Response<FavoriIslemPojo> response) {
                        if(response.body().isTf()){
                            Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                            getfavoriButonText();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                            getfavoriButonText();
                        }

                    }

                    @Override
                    public void onFailure(Call<FavoriIslemPojo> call, Throwable t) {

                        Log.d("favoriyeeklemehata",t.getMessage());
                    }
                });
            }
        });
    }
}
package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.autosgallery.Models.IlanDetayPojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;

import java.util.List;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_detay);
        // ilanlar activity dedn gönderilen ilanid yi bundle ile alma
        Bundle bundle=getIntent().getExtras();
        ilanId=bundle.getString("ilanid");

        tanimlamalar();

        getIlanDetay();



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
//        ilanDetaySlider=findViewById(R.id.ilanDetaySlider);



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


                Log.i("gecikme","geckmeeeeeeeeeeeeeeeeeee");

            }

            @Override
            public void onFailure(Call<IlanDetayPojo> call, Throwable t) {

                Log.d("ilandetayhata",t.getMessage());
            }
        });

    }
}
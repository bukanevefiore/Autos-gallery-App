package com.example.autosgallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autosgallery.Models.IlanSonucPojo;
import com.example.autosgallery.Models.IlanVerPojo;
import com.example.autosgallery.R;
import com.example.autosgallery.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YakitActivity extends AppCompatActivity {
    Button yakitBilgisiButon,yakitBilgisiButonGeri;
    EditText yakitTipiEditText,ortyakitBilgiEditText,depoHacmiEditText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yakit);
        tanimlamalar();
          }
    public void tanimlamalar(){
        // uye_id yi ilanver tablosu kaydı için set etme
        sharedPreferences=getApplicationContext().getSharedPreferences("giriş",0);
        IlanVerPojo.setUye_id(sharedPreferences.getString("uye_id",null));

        yakitTipiEditText=findViewById(R.id.yakitTipiEditText);
        ortyakitBilgiEditText=findViewById(R.id.ortyakitBilgiEditText);
        depoHacmiEditText=findViewById(R.id.depoHacmiEditText);

        yakitTipiEditText.setText(IlanVerPojo.getYakittipi());
        ortyakitBilgiEditText.setText(IlanVerPojo.getOrtalamayakit());
        depoHacmiEditText.setText(IlanVerPojo.getDepohacmi());

        yakitBilgisiButon=findViewById(R.id.yakitBilgisiButon);
        yakitBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!yakitTipiEditText.getText().toString().equals("") && !ortyakitBilgiEditText.getText().toString().equals("") && !depoHacmiEditText.getText().toString().equals("")) {
                    IlanVerPojo.setYakittipi(yakitTipiEditText.getText().toString());
                    IlanVerPojo.setOrtalamayakit(ortyakitBilgiEditText.getText().toString());
                    IlanVerPojo.setDepohacmi(depoHacmiEditText.getText().toString());

                    // ilanı yayınlama method
                    ilanıYayinla(IlanVerPojo.getUye_id(),IlanVerPojo.getSehir(),IlanVerPojo.getIlce(),IlanVerPojo.getMahalle(),IlanVerPojo.getMarka(),
                            IlanVerPojo.getSeri(),IlanVerPojo.getModel(),IlanVerPojo.getYil(),IlanVerPojo.getIlantipi(),IlanVerPojo.getKimden(),
                            IlanVerPojo.getBaslik(),IlanVerPojo.getAciklama(),IlanVerPojo.getMotortipi(),IlanVerPojo.getMotorhacmi(),
                            IlanVerPojo.getSurat(),IlanVerPojo.getYakittipi(),IlanVerPojo.getOrtalamayakit(),IlanVerPojo.getDepohacmi(),IlanVerPojo.getKm(),IlanVerPojo.getUcret());
                }else{
                    Toast.makeText(getApplicationContext(),"Tüm bilgileri girin",Toast.LENGTH_LONG).show();
                }
            }
        });
        yakitBilgisiButonGeri=findViewById(R.id.yakitBilgisiButonGeri);
        yakitBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(YakitActivity.this,MotorPerformansActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });

    }
    public void ilanıYayinla(String uye_id, String sehir,String ilce, String mahalle,String marka, String seri,
                             String model, String yil,String ilantipi, String kimden,String baslik, String aciklama,
                             String motortipi, String motorhacmi,String surat, String yakittipi,String ortalamayakit, String depohacmi,
                             String km, String ucret){

        Call<IlanSonucPojo> request= ManagerAll.getInstance().ilanVer(uye_id,sehir,ilce,mahalle,marka,seri,model,yil,ilantipi,kimden,baslik,aciklama,motortipi,
                motorhacmi,surat,yakittipi,ortalamayakit,depohacmi,km, ucret);
        request.enqueue(new Callback<IlanSonucPojo>() {
            @Override
            public void onResponse(Call<IlanSonucPojo> call, Response<IlanSonucPojo> response) {
                if(response.body().isTf()) {
                    Toast.makeText(getApplicationContext(),"Ilanınız yayınlanmıştır",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(YakitActivity.this, IlanResimlerActivity.class);
                    intent.putExtra("ilan_id",response.body().getIlanid());  // id yi gçnderme veri tabanına kaydetmek için
                    intent.putExtra("uye_id",response.body().getUyeid());
                    // uyeid ve ilanid resimactivitye göderme kontrolü
                    Log.i("yakitputExtraislemi",response.body().getIlanid() + "///" + response.body().getUyeid());
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<IlanSonucPojo> call, Throwable t) {
                Log.d("Ilanvermekontrol",t.getMessage());
            }
        });
    }
}
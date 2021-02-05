package com.example.autosgallery.RestApi;

import com.example.autosgallery.Models.DogrulamaPojo;
import com.example.autosgallery.Models.IlanSonucPojo;
import com.example.autosgallery.Models.IlanlarPojo;
import com.example.autosgallery.Models.IlanlarimPojo;
import com.example.autosgallery.Models.IlanlarimSilPojo;
import com.example.autosgallery.Models.LoginPojo;
import com.example.autosgallery.Models.RegisterPojo;
import com.example.autosgallery.Models.ResimEklePojo;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance=new ManagerAll();
    public static synchronized ManagerAll getInstance(){return ourInstance;}

    public Call<LoginPojo> login(String ad, String sifre)
    {
        Call<LoginPojo> x= getRestApi().control(ad,sifre);
        return x;
    }

    public Call<RegisterPojo> register(String ad, String sifre)
    {
        Call<RegisterPojo> x= getRestApi().kayitol(ad,sifre);
        return x;
    }

    public Call<DogrulamaPojo> dogrula(String ad, String kod)
    {
        Call<DogrulamaPojo> x= getRestApi().dogrulama(ad,kod);
        return x;
    }

    public Call<IlanSonucPojo> ilanVer(String uye_id, String sehir,String ilce, String mahalle,String marka, String seri,
                                      String model, String yil,String ilantipi, String kimden,String baslik, String aciklama,
                                      String motortipi, String motorhacmi,String surat, String yakittipi,String ortalamayakit, String depohacmi,
                                      String km, String ucret)
    {
        Call<IlanSonucPojo> x= getRestApi().ilanVer(uye_id,sehir,ilce,mahalle,marka,seri,model,yil,ilantipi,kimden,baslik,aciklama,motortipi,
                motorhacmi,surat,yakittipi,ortalamayakit,depohacmi,km, ucret);
        return x;
    }

    public Call<ResimEklePojo> resimEkle(String uye_id, String ilan_id,String image)
    {
        Call<ResimEklePojo> x= getRestApi().resimYukle(uye_id,ilan_id,image);
        return x;
    }

    public Call<List<IlanlarimPojo>> ilanlarim(String uyeid)
    {
        Call<List<IlanlarimPojo>> x= getRestApi().ilanlarim(uyeid);
        return x;
    }

    public Call<IlanlarimSilPojo> ilanlarimSil(String ilanid)
    {
        Call<IlanlarimSilPojo> x= getRestApi().ilanlarimSil(ilanid);
        return x;
    }

    public Call<IlanlarPojo> ilanlar()
    {
        Call<IlanlarPojo> x= getRestApi().ilanlar();
        return x;
    }
}

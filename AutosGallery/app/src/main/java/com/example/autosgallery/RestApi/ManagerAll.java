package com.example.autosgallery.RestApi;

import com.example.autosgallery.Models.DogrulamaPojo;
import com.example.autosgallery.Models.FavoriIslemPojo;
import com.example.autosgallery.Models.FavoriKontrolPojo;
import com.example.autosgallery.Models.FavoriSliderPojo;
import com.example.autosgallery.Models.IlanDetayPojo;
import com.example.autosgallery.Models.IlanSonucPojo;
import com.example.autosgallery.Models.IlanlarPojo;
import com.example.autosgallery.Models.IlanlarimPojo;
import com.example.autosgallery.Models.IlanlarimSilPojo;
import com.example.autosgallery.Models.KisiBilgiUpdatePojo;
import com.example.autosgallery.Models.LoginPojo;
import com.example.autosgallery.Models.RegisterPojo;
import com.example.autosgallery.Models.ResimEklePojo;
import com.example.autosgallery.Models.SliderPojo;
import com.example.autosgallery.Models.UserPojo;

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

    public Call<List<IlanlarPojo>> ilanlar()
    {
        Call<List<IlanlarPojo>> x= getRestApi().ilanlar();
        return x;
    }

    public Call<IlanDetayPojo> ilanDetay(String ilanid)
    {
        Call<IlanDetayPojo> x= getRestApi().ilanDetay(ilanid);
        return x;
    }

    public Call<List<SliderPojo>> ilanResimleri(String ilanid)
    {
        Call<List<SliderPojo>> x= getRestApi().ilanResimleri(ilanid);
        return x;
    }

    public Call<FavoriKontrolPojo> getFavoriButonText(String uyeid, String ilanid)
    {
        Call<FavoriKontrolPojo> x= getRestApi().getFavoriButonText(uyeid,ilanid);
        return x;
    }

    public Call<FavoriIslemPojo> favoriIslem(String uyeid, String ilanid)
    {
        Call<FavoriIslemPojo> x= getRestApi().favoriIslem(uyeid,ilanid);
        return x;
    }

    public Call<List<FavoriSliderPojo>> mainSetSlider(String uyeid)
    {
        Call<List<FavoriSliderPojo>> x= getRestApi().mainSetSlider(uyeid);
        return x;
    }

    public Call<UserPojo> getInformation(String uyeid)
    {
        Call<UserPojo> x= getRestApi().getInformation(uyeid);
        return x;
    }

    public Call<KisiBilgiUpdatePojo> kisiUpdate(String uyeid,String user,String pass)
    {
        Call<KisiBilgiUpdatePojo> x= getRestApi().kisiUpdate(uyeid,user,pass);
        return x;
    }
}

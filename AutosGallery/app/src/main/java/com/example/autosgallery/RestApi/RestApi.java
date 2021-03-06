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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RestApi {

    @FormUrlEncoded
    @POST("/autogallery/login.php")
    Call<LoginPojo> control(@Field("kad") String ad, @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/autogallery/register.php")
    Call<RegisterPojo> kayitol(@Field("kadi") String ad, @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/autogallery/dogrulama.php")
    Call<DogrulamaPojo> dogrulama(@Field("kadi") String ad, @Field("kod") String kod);

    @FormUrlEncoded
    @POST("/autogallery/ilanver.php")
    Call<IlanSonucPojo> ilanVer(@Field("uye_id") String uye_id, @Field("sehir") String sehir, @Field("ilce") String ilce, @Field("mahalle") String mahalle,
                                @Field("marka") String marka, @Field("seri") String seri, @Field("model") String model, @Field("yil") String yil,
                                @Field("ilantipi") String ilantipi, @Field("kimden") String kimden, @Field("baslik") String baslik, @Field("aciklama") String aciklama,
                                @Field("motortipi") String motortipi, @Field("motorhacmi") String motorhacmi, @Field("surat") String surat, @Field("yakittipi") String yakittipi,
                                @Field("ortalamayakit") String ortalamayakit, @Field("depohacmi") String depohacmi, @Field("km") String km, @Field("ucret") String ucret);

    @FormUrlEncoded
    @POST("/autogallery/ilanresimekle.php")
    Call<ResimEklePojo> resimYukle(@Field("uye_id") String uye_id, @Field("ilan_id") String ilan_id, @Field("resim") String base64StringResim);

    @GET("/autogallery/ilanlarim.php")
    Call<List<IlanlarimPojo>> ilanlarim(@Query("uyeid") String uyeid);

    @GET("/autogallery/ilanlarimdansil.php")
    Call<IlanlarimSilPojo> ilanlarimSil(@Query("ilan_id") String ilanid);

    @GET("/autogallery/ilanlar.php")
    Call<List<IlanlarPojo>> ilanlar();

    @GET("/autogallery/ilandetay.php")
    Call<IlanDetayPojo> ilanDetay(@Query("ilanid") String ilanid);

    @GET("/autogallery/ilanresimleri.php")
    Call<List<SliderPojo>> ilanResimleri(@Query("ilanid") String ilanid);

    @GET("/autogallery/favori.php")
    Call<FavoriKontrolPojo> getFavoriButonText(@Query("uye_id") String uyeid, @Query("ilan_id") String ilanid);

    @GET("/autogallery/favoriislem.php")
    Call<FavoriIslemPojo> favoriIslem(@Query("uye_id") String uyeid, @Query("ilan_id") String ilanid);

    @GET("/autogallery/favoriilanslider.php")
    Call<List<FavoriSliderPojo>> mainSetSlider(@Query("uye_id") String uyeid);

    @GET("/autogallery/bilgiler.php")
    Call<UserPojo> getInformation(@Query("uyeid") String uyeid);

    @GET("/autogallery/guncelle.php")
    Call<KisiBilgiUpdatePojo> kisiUpdate(@Query("uyeid") String uyeid, @Query("user") String user, @Query("pass") String pass);

}

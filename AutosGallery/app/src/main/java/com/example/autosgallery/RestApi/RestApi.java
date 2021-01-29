package com.example.autosgallery.RestApi;

import com.example.autosgallery.Models.DogrulamaPojo;
import com.example.autosgallery.Models.IlanSonucPojo;
import com.example.autosgallery.Models.IlanlarimPojo;
import com.example.autosgallery.Models.LoginPojo;
import com.example.autosgallery.Models.RegisterPojo;
import com.example.autosgallery.Models.ResimEklePojo;

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

}

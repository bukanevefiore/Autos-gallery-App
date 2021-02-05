package com.example.autosgallery.Models;

public class IlanlarPojo {

    public String ilanid;
    public String uyeid;
    public String fiyat;
    public String resim;
    public String baslik;
    public boolean tf;
    public Object result;
    public int sayi;
    public String il;
    public String ilce;
    public String mahalle;
    public String aciklama;

    public String getIlanid() {
        return ilanid;
    }

    public void setIlanid(String ilanid) {
        this.ilanid = ilanid;
    }

    public String getUyeid() {
        return uyeid;
    }

    public void setUyeid(String uyeid) {
        this.uyeid = uyeid;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public boolean isTf() {
        return tf;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public int getSayi() {
        return sayi;
    }

    public void setSayi(int sayi) {
        this.sayi = sayi;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getMahalle() {
        return mahalle;
    }

    public void setMahalle(String mahalle) {
        this.mahalle = mahalle;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    @Override
    public String toString() {
        return "IlanlarPojo{" +
                "ilanid='" + ilanid + '\'' +
                ", uyeid='" + uyeid + '\'' +
                ", fiyat='" + fiyat + '\'' +
                ", resim='" + resim + '\'' +
                ", baslik='" + baslik + '\'' +
                ", tf=" + tf +
                ", result=" + result +
                ", sayi=" + sayi +
                ", il='" + il + '\'' +
                ", ilce='" + ilce + '\'' +
                ", mahalle='" + mahalle + '\'' +
                ", aciklama='" + aciklama + '\'' +
                '}';
    }
}

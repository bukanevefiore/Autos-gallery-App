package com.example.autosgallery.Models;

public class FavoriSliderPojo {
    private Object resimyolu;
    private boolean tf;
    private Object ilanid;

    public Object getResimyolu() {
        return resimyolu;
    }

    public void setResimyolu(Object resimyolu) {
        this.resimyolu = resimyolu;
    }

    public boolean isTf() {
        return tf;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }

    public Object getIlanid() {
        return ilanid;
    }

    public void setIlanid(Object ilanid) {
        this.ilanid = ilanid;
    }

    @Override
    public String toString() {
        return "FavoriSliderPojo{" +
                "resimyolu=" + resimyolu +
                ", tf=" + tf +
                ", ilanid=" + ilanid +
                '}';
    }
}

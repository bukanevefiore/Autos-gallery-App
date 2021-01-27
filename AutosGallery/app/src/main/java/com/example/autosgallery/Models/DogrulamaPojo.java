package com.example.autosgallery.Models;

public class DogrulamaPojo{
    private String result;
    private boolean tf;
    private Object kadi;
    private Object id;

    public void setResult(String result){
        this.result = result;
    }

    public String getResult(){
        return result;
    }

    public void setTf(boolean tf){
        this.tf = tf;
    }

    public boolean isTf(){
        return tf;
    }

    public void setKadi(Object kadi){
        this.kadi = kadi;
    }

    public Object getKadi(){
        return kadi;
    }

    public void setId(Object id){
        this.id = id;
    }

    public Object getId(){
        return id;
    }

    @Override
     public String toString(){
        return 
            "DogrulamaPojo{" + 
            "result = '" + result + '\'' + 
            ",tf = '" + tf + '\'' + 
            ",kadi = '" + kadi + '\'' + 
            ",id = '" + id + '\'' + 
            "}";
        }
}

package com.example.saglik;

public class HedefModel {
    private String kalori,adim,su;
    public HedefModel (String kalori, String adim,String su){
        this.kalori = kalori;
        this.adim = adim;
        this.su = su;


    }
    public String getKalori(){
        return kalori;
    }
    public void setKalori(String kalori){
        this.kalori = kalori;
    }
    public String getAdim(){
        return adim;
    }
    public void setAdim(String adim){
        this.adim = adim;
    }
    public String getSu(){
        return su;
    }
    public void setSu(String su){
        this.su = su;
    }




}

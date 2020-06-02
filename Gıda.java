package com.example.saglik;

public class Gıda {
    public String kalori,name;
    public Gıda(String kalori,String name){
        this.kalori = kalori;
        this.name = name;
    }
    public String getKalori(){
        return kalori;
    }
    public void setKalori(String kalori){
        this.kalori = kalori;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name= name;
    }


}

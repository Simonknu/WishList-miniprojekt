package com.example.wishlist.models;

//import java.util.ArrayList;
import java.util.List;

public class OnskeListe {
    private String name;
    private List<Onske> Onsker;


    //-------------------------------CONSTRUCTORS --------------------------------------------------------------------
    public OnskeListe (String name, List<Onske> Onsker){
        this.name = name;
        this.Onsker = Onsker;
    }

    //--------------------------------GETTER METHODS--------------------------------------------------------------------
    public String getName(){
        return name;
    }

    public List<Onske> getOnsker() {
        return Onsker;
    }
    //--------------------------------SETTER METHODS--------------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setOnsker(List<Onske> onsker) {
        Onsker = onsker;
    }
}

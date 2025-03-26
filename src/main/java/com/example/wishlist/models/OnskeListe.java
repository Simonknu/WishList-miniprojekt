package com.example.wishlist.models;

import java.util.ArrayList;
import java.util.List;

public class OnskeListe {
    private String name;
    private List<Onske> Onsker;



    public OnskeListe (String name, List Onsker){
        this.name = name;
        this.Onsker = Onsker;
    }

    public OnskeListe (String name){
        this.name = name;
        this.Onsker = new ArrayList<>();
    }
}

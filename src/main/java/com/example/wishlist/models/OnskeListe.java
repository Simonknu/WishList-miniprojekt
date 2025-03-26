package com.example.wishlist.models;

import java.util.ArrayList;
import java.util.List;

public class OnskeListe {
    private String name;
    private List<Onske> onsker;

    public OnskeListe(String name, List<Onske> onsker){
        this.name = name;
        this.onsker = onsker;
    }

    public String getName() {
        return name;
    }

    public List<Onske> getOnsker() {
        return onsker;
    }

    public void deleteWish(String wishID) {
        onsker.removeIf(wish -> wish.getName().equals(wishID));
    }
}

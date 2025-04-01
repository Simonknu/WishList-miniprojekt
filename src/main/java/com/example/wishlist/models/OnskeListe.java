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

    public void addWish(Onske onske)
    {
        onsker.add(onske);
    }

    public Onske updateWish(Onske onske, Onske gammelOnske) {
        onsker.remove(gammelOnske) ;
        onsker.add(onske);
        return onske;
    }

    public Onske getOnske(String wishID) {
        for (Onske o: onsker)
        {
            if (o.getName().equals(wishID)) return o;
        }
        return null;
    }
}

package com.example.wishlist.repository;

import com.example.wishlist.models.Onske;
import com.example.wishlist.models.OnskeListe;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OnskeListeRepository {

    private List<OnskeListe> a = new ArrayList<OnskeListe>();

    public OnskeListeRepository(){
        List<Onske> e = new ArrayList<Onske>();
        e.add(new Onske("goat","it's a goat","goat.com"));
        e.add(new Onske("sheep","it's a sheep","sheep.com"));
        e.add(new Onske("doll","doll","doll.com"));

        a.add(new OnskeListe("bigG", e));
    }
    public OnskeListe searchLists(String name)
    {
        for (OnskeListe onskeListe : a )
        {
            if(onskeListe.getName().equals(name));
            {
                return onskeListe;
            }
        }
        return null;
    }

    public Onske addWishTo(String listName, Onske onske) {
        searchLists(listName).addWish(onske);
        return onske;
    }

    public Onske updateWish(Onske onske, Onske gammelOnske, String listID) {
        return searchLists(listID).updateWish(onske, gammelOnske);
    }
}

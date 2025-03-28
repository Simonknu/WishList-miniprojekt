package com.example.wishlist.service;

import com.example.wishlist.models.Onske;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.repository.OnskeListeRepository;
import org.springframework.stereotype.Service;

@Service
public class OnskelisteService {

    private final OnskeListeRepository repo = new OnskeListeRepository();


    public Onske addOnske(Onske onske, String listName) {
        repo.addWishTo(listName, onske);
        return onske;
    }

    public OnskeListe searchForList(String listID) {
        return repo.searchLists(listID);
    }

    public Onske updOnske(Onske onske, String listID) {
        return
                repo.addWishTo(listID,onske);
    }
}

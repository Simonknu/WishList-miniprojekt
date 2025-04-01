package com.example.wishlist.service;

import com.example.wishlist.models.Bruger;
import com.example.wishlist.models.Onske;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.repository.BrugerRepository;
import com.example.wishlist.repository.OnskeListeRepository;
import org.springframework.stereotype.Service;


@Service
public class BaseService {


    private final BrugerRepository brugerRepository;

    private final OnskeListeRepository onskeListeRepository;

    public BaseService(BrugerRepository brugerRepository, OnskeListeRepository onskeListeRepository, OnskeListeRepository){
        this.brugerRepository = brugerRepository;
        this.onskeListeRepository = onskeListeRepository;
    }


    public void gemBruger(Bruger bruger) {
        brugerRepository.gemBruger(bruger);
    }

    public boolean logInd(String userName, String password) {
        return brugerRepository.logInd(userName,password);
    }



    public Onske addOnske(Onske onske, String listName) {
        onskeListeRepository.addWishTo(listName, onske);
        return onske;
    }

    public OnskeListe searchForList(String listID) {
        return onskeListeRepository.searchLists(listID);
    }

    public Onske updOnske(Onske onske, String listID) {
        return
                onskeListeRepository.addWishTo(listID,onske);
    }
}

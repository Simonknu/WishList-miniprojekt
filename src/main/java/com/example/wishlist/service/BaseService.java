package com.example.wishlist.service;

import com.example.wishlist.models.Bruger;
import com.example.wishlist.models.Onske;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.repository.BrugerRepository;
import com.example.wishlist.repository.OnskeListeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BaseService {


    private final BrugerRepository brugerRepository;
    private final OnskeListeRepository onskeListeRepository;

    public BaseService(BrugerRepository brugerRepository, OnskeListeRepository onskeListeRepository){
        this.brugerRepository = brugerRepository;
        this.onskeListeRepository = onskeListeRepository;
    }

//-----------------------------BRUGER METHODS--------------------------------------------------------------
    public void gemBruger(Bruger bruger) {
        brugerRepository.gemBruger(bruger);
    }

    public boolean logInd(String userName, String password) {
        return brugerRepository.logInd(userName,password);
    }

//-----------------------------ONSKE LISTE METHODS---------------------------------------------


    public void opretOnskeListe(String name,String username){
        onskeListeRepository.createOnskeListe(name, username);
    }

    public List<OnskeListe> showAllOnskeliste(){
        return onskeListeRepository.faAllOnskeListe();
    }

    public List<OnskeListe> showAllOnskeListeByUser(String username){
        return onskeListeRepository.faOnskeListeFraBruger(username);
    }
}

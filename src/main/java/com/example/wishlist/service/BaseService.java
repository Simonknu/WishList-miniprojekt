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

    public void opdaterProfil(Bruger gammelBruger, Bruger opdateretBruger) {
        brugerRepository.opdaterProfil(gammelBruger,opdateretBruger);
    }

    public void sletBruger(String userName) {
        brugerRepository.sletBruger(userName);
    }

    public boolean tjekUsernameDup(String username){
        return brugerRepository.tjekUsernameDup(username);
    }

//-----------------------------ONSKE LISTE METHODS---------------------------------------------


    public void opretOnskeListe(String name,String username){
        onskeListeRepository.opretOnskeListe(name, username);
    }

    public void sletOnskeListe(String name){
        onskeListeRepository.sletOnskeListe(name);
    }

    public void redigerOnskeListe(String oldName, String newName){
        onskeListeRepository.redigerOnskeListe(oldName, newName);
    }

    public List<OnskeListe> showAllOnskeliste(){
        return onskeListeRepository.faAllOnskeListe();
    }

    public List<OnskeListe> showAllOnskeListeByUser(String username){
        return onskeListeRepository.faOnskeListeFraBruger(username);
    }

    public OnskeListe faOnskeListeMedNavn(String name){
        return onskeListeRepository.findOnskeListeMedNavn(name);
    }

    public boolean gentagetNavn(String name){
         return onskeListeRepository.gentagetNavn(name);
    }
}

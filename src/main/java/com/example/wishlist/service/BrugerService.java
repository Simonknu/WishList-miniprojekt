package com.example.wishlist.service;

import com.example.wishlist.models.Bruger;
import com.example.wishlist.repository.BrugerRepository;
import org.springframework.stereotype.Service;

@Service
public class BrugerService {

    private final BrugerRepository brugerRepository;

    public BrugerService(BrugerRepository brugerRepository){
        this.brugerRepository = brugerRepository;
    }

    public void gemBruger(Bruger bruger) {
        brugerRepository.gemBruger(bruger);
    }

    public boolean logInd(String userName, String password) {
        return brugerRepository.logInd(userName,password);
    }

    public void opdaterProfil(Bruger bruger) {
        brugerRepository.opdaterProfil(bruger);
    }
}

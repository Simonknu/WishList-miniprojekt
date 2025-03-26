package com.example.wishlist.service;

import com.example.wishlist.models.Bruger;
import com.example.wishlist.repository.BrugerRepository;
import org.springframework.stereotype.Service;

@Service
public class BrugerService {

    private BrugerRepository brugerRepository;

    public BrugerService(BrugerRepository brugerRepository){
        this.brugerRepository = brugerRepository;
    }

    public void saveBruger(Bruger bruger) {
        brugerRepository.saveBruger(bruger);
    }
}

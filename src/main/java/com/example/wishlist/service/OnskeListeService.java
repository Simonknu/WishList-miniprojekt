package com.example.wishlist.service;


import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.repository.OnskeListeRepository;
import org.springframework.stereotype.Service;


@Service
public class OnskeListeService {

    private final OnskeListeRepository repository;


    public OnskeListeService(OnskeListeRepository repository) {
        this.repository = repository;
    }
}
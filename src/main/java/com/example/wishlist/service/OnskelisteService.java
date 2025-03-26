package com.example.wishlist.service;

import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.repository.OnskeListeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnskelisteService {

    private final OnskeListeRepository repo = new OnskeListeRepository();

    public OnskeListe searchForList(String listID) {
        return repo.searchLists(listID);
    }
}

package com.example.wishlist.controller;

import com.example.wishlist.service.BrugerService;
import org.springframework.stereotype.Controller;

@Controller
public class BrugerController {


    private BrugerService brugerService;

    public BrugerController(BrugerService brugerService){
        this.brugerService = brugerService;
    }

}

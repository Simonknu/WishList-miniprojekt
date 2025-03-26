package com.example.wishlist.controller;

import com.example.wishlist.models.Bruger;
import com.example.wishlist.service.BrugerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class BrugerController {

    private BrugerService brugerService;

    public BrugerController(BrugerService brugerService){
        this.brugerService = brugerService;
    }

    @GetMapping("/signup")
    public String signUpBruger(Model model){
        model.addAttribute("bruger",new Bruger());
        return "signup-form";
    }

    @PostMapping("/save")
    public String saveBruger(@ModelAttribute Bruger bruger){
        brugerService.saveBruger(bruger);
        return "bruger-profil";
    }





}

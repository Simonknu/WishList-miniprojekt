package com.example.wishlist.controller;


import com.example.wishlist.models.Bruger;
import com.example.wishlist.service.OnskeListeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OnskeListeController {

    private final OnskeListeService service;



    public OnskeListeController(OnskeListeService service){
        this.service = service;
    }


    @GetMapping("/showAllOnskeliste")
    public String showAllOnskeListe(Bruger bruger){
return "index";
    }

    @GetMapping("/getToTest")
    public String test(){
        return "testForOnskeListe";
    }
}

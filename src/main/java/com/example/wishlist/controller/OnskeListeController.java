package com.example.wishlist.controller;


import com.example.wishlist.models.Bruger;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.service.OnskeListeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("onskeListe")
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

    @GetMapping("/opretteOnskeListe")
    public String opretOnskeliste(){
        return "opretteOnskeListe";
    }

    @PostMapping("/gemOnskeListe")
    public String gemOpretOnskeListe(String name){
       service.opretOnskeListe(name);

return "redirect:/testForOnskeListe";
    }
}

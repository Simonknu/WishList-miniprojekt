package com.example.wishlist.controller;


import com.example.wishlist.models.Bruger;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/onskeListe")
public class OnskeListeController {

    private final BaseService service;



    public OnskeListeController(BaseService service){
        this.service = service;
    }


    @GetMapping("/showAllOnskelisteByUser")
    public String showAllOnskeListeByUser(Model model){
        List<OnskeListe> wishLists = service.showAllOnskeListeByUser("alice");
        model.addAttribute("wishLists", wishLists);
return "showListsByUser";
    }

    @GetMapping("/showAllOnskeListe")
    public String showAllOnskeListe(Model model){
        List<OnskeListe> wishLists = service.showAllOnskeliste();
        model.addAttribute("wishLists", wishLists);
        return "showAllLists";
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
       service.opretOnskeListe(name, "alice");

return "redirect:testForOnskeListe";
    }
}

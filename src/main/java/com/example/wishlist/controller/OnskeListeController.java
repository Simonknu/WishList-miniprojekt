package com.example.wishlist.controller;


import com.example.wishlist.models.Bruger;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{listID}")
    public String getAttractionByName(@PathVariable String listID, Model model) {
        OnskeListe wishList = service.faOnskeListeMedNavn(listID);
        if (wishList != null) {
            model.addAttribute("wishlist",wishList);
            model.addAttribute("wishes", wishList.getOnsker());
            return "OnskeListe-Display";
        }
        return "index";
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

      return "testForOnskeListe";
    }


    @GetMapping("/getSpecificWishList")
        public String faOnskeListeMedNavn(String name, Model model){

        OnskeListe onskeListe = service.faOnskeListeMedNavn(name);
        model.addAttribute("wishList", onskeListe);


        return "showSpecificWishList";

        }

        @PostMapping("/{name}/sletOnskeListe")
    public String sletOnskeListe(String name){
        service.sletOnskeListe(name);
return "redirect:/onskeListe/showAllOnskeListe";
        }

        @GetMapping("/{name}/redigerOnskeListe")
    public String redigerOnskeListe(String name){
        return "redirect:/onskeListe/getToTest";
        }

}

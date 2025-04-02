package com.example.wishlist.controller;


import com.example.wishlist.models.Bruger;
import com.example.wishlist.models.Onske;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.service.BaseService;
import jakarta.servlet.http.HttpSession;
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
    public String showAllOnskeListeByUser(String name, Model model){
        List<OnskeListe> wishLists = service.showAllOnskeListeByUser(name);
        model.addAttribute("wishLists", wishLists);
return "brugersOnskeListe";
    }

    @GetMapping("/showAllOnskeListe")
    public String showAllOnskeListe(Model model){
        List<OnskeListe> wishLists = service.showAllOnskeliste();
        model.addAttribute("wishLists", wishLists);
        return "showAllLists";
    }


    @GetMapping("/{name}/opretteOnskeListe")
    public String opretOnskeliste(String name, HttpSession session, Model model){
        Bruger bruger = (Bruger) session.getAttribute("bruger");
        model.addAttribute("brugernavn", bruger.getUserName());
        return "opretteOnskeListe";
    }

    @PostMapping("/{username}/gemOnskeListe")
    public String gemOpretOnskeListe(String name, String username){
       service.opretOnskeListe(name, username);

      return "redirect:/bruger/profil";
    }


    @GetMapping("/{name}/getSpecificWishList")
        public String faOnskeListeMedNavn(String name, Model model){

        OnskeListe onskeListe = service.faOnskeListeMedNavn(name);
        model.addAttribute("wishList", onskeListe);


        return "brugersOnskeListe";

        }

        @PostMapping("/{name}/sletOnskeListe")
    public String sletOnskeListe(String name){
        service.sletOnskeListe(name);
return "redirect:/bruger/profil";
        }

        @GetMapping("/{name}/redigerOnskeListe")
    public String redigerOnskeListe(String name, Model model){
            OnskeListe onskeListe = service.faOnskeListeMedNavn(name);
        model.addAttribute("wishList", onskeListe);
        return "redigerOnskeListe";
        }

}

package com.example.wishlist.controller;

import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.service.OnskelisteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wishlist")
public class OnskelisteController {

    private OnskelisteService service;
    public OnskelisteController(OnskelisteService onskelisteService){
        this.service = onskelisteService;
    }

    @GetMapping("/{listID}")
    public String getAttractionByName(@PathVariable String listID, Model model) {
        OnskeListe wishList = service.searchForList(listID);
        if (wishList != null) {
            model.addAttribute("wishlist",wishList);
            model.addAttribute("wishes", wishList.getOnsker());
            return "OnskeListe-Display";
        }
        return "index";

    }

}

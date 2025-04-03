package com.example.wishlist.controller;

import com.example.wishlist.models.Onske;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/wish")
public class OnskeController {

    private BaseService service;
    public OnskeController(BaseService onskelisteService){
        this.service = onskelisteService;
    }

    @GetMapping("/{listID}")
    public String displayWishes(@PathVariable String listID, Model model) {
        OnskeListe wishList = service.faOnskeListeMedNavn(listID);
        if (wishList != null) {
            model.addAttribute("wishlist",wishList);
            model.addAttribute("wishes", wishList.getOnsker());
            return "OnskeListe-Display";
        }
        return "index";
    }
    @GetMapping("/{listID}/delete/{wishID}")
    public String deleteWishFromList(@PathVariable String listID, @PathVariable int wishID)
    {
        service.sletOnske(wishID);
        return "redirect:/wishlist/" + listID;
    }

    @GetMapping("/{listID}/newWish")
    public String makeNewWish(@PathVariable String listID,  Model model) {
        model.addAttribute("onske", new Onske());
        model.addAttribute("wishlistName",listID);
        return "OnskeListe-Tilføj";
    }

    @PostMapping("/{listID}/addNewWish")
    public String addNewWish(@ModelAttribute("onske") Onske onske, @PathVariable int listID) {
        service.addOnske(onske, listID);
        return "redirect:/wishlist/"+ listID;
    }

    @GetMapping("/{listID}/edit/{wishID}")
    public String editWish(@PathVariable String listID,  Model model, @PathVariable int wishID )
    {
        Onske o = service.getOnske(wishID);
        model.addAttribute("onske", o);
        model.addAttribute("wishlistID",listID);
        model.addAttribute("wishID",wishID);
        return "OnskeListe-Edit";
    }

    @PostMapping("/{listID}/{wishID}/replace")
    public String replaceWish(@ModelAttribute("onske") Onske onske, @PathVariable String listID, @PathVariable int wishID )
    {
        service.redigerOnske(wishID, onske);

        return "redirect:/wishlist/"+ listID;
    }



}
package com.example.wishlist.controller;

import com.example.wishlist.models.Onske;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.service.OnskelisteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
    @GetMapping("/{listID}/delete/{wishID}")
    public String deleteWishFromList(@PathVariable String listID, @PathVariable String wishID)
    {
        OnskeListe wishList = service.searchForList(listID);
        System.out.println(listID+wishID);
        wishList.deleteWish(wishID);
        return "redirect:/wishlist/" + listID;
    }

    @GetMapping("/{listID}/newWish")
    public String makeNewWish(@PathVariable String listID,  Model model) {
        model.addAttribute("onske", new Onske());
        model.addAttribute("wishlistName",listID);
        return "OnskeListe-Tilføj";
    }

    @PostMapping("/{listID}/addNewWish")
    public String addNewWish(@ModelAttribute("onske") Onske onske, @PathVariable String listID) {
        Onske result = service.addOnske(onske, listID);
        System.out.println(result.getName());
        return "redirect:/wishlist/"+ listID;
    }

    @GetMapping("/{listID}/edit/{wishID}")
    public String editWish(@PathVariable String listID,  Model model, @PathVariable String wishID )
    {
        Onske o = service.searchForList(listID).getOnske(wishID);
        model.addAttribute("onske", o);
        model.addAttribute("wishlistID",listID);
        model.addAttribute("wishID",wishID);
        return "OnskeListe-Edit";
    }

    @PostMapping("/{listID}/{wishID}/replace")
    public String replaceWish(@ModelAttribute("onske") Onske onske, @PathVariable String listID, @PathVariable String wishID )
    {
        OnskeListe list = service.searchForList(listID);
        list.deleteWish(wishID);
        list.addWish(onske);

        return "redirect:/wishlist/"+ listID;
    }



}

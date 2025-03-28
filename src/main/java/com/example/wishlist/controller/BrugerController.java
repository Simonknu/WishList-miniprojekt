package com.example.wishlist.controller;

import com.example.wishlist.models.Bruger;
import com.example.wishlist.service.BrugerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bruger")
@Controller
public class BrugerController {

    private BrugerService brugerService;

    public BrugerController(BrugerService brugerService) {
        this.brugerService = brugerService;
    }


    @GetMapping("/opret")
    public String opretBruger(Model model) {
        model.addAttribute("bruger", new Bruger());
        return "opret-brugerform";
    }

    @PostMapping("/gem")
    public String gemBruger(@ModelAttribute Bruger bruger) {
        brugerService.gemBruger(bruger);
        return "redirect:/";
    }

    @GetMapping("/logind")
    public String visLogInd() {
        return "log-ind";
    }

    @PostMapping("/logind")
    public String logInd(@RequestParam("userName") String userName, @RequestParam("password") String password,
                         HttpSession session,
                         Model model) {
        if (brugerService.logInd(userName, password)) {
            session.setAttribute("bruger", new Bruger(userName, password));
            session.setMaxInactiveInterval(1800);
            return "redirect:/bruger/profil";

        } else {
            model.addAttribute("forkertInput", true);
            return "log-ind";
        }
    }
    @GetMapping("/profil")
    public String brugerProfil(HttpSession session, Model model) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");

        if (bruger != null) {
            model.addAttribute("brugernavn", bruger.getUserName());  // Henter korrekt navn
            return "bruger-profil";
        } else {
            return "log-ind";
        }
    }


    @GetMapping("/minprofil")
    public String minProfil(HttpSession session, Model model) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");

        if (bruger != null) {
            model.addAttribute("brugernavn", bruger.getUserName());  // Henter korrekt navn

            // Viser password som prikker med samme længde
            String skjultPassword = "•".repeat(bruger.getPassword().length());
            model.addAttribute("skjultPassword", skjultPassword);

            return "min-profil";
        } else {
            return "log-ind";
        }
    }


    // rediger og slet profil

    @GetMapping("/redigerprofil")
    public String redigerProfil (HttpSession session, Model model){
        Bruger bruger = (Bruger) session.getAttribute("bruger");

        if (bruger != null) {
            model.addAttribute("bruger", bruger);  // Henter korrekt navn
            return "profil-indstillinger";
        } else {
            return "log-ind";
        }
    }

    @PostMapping("/opdaterprofil")
    public String opdaterProfil(Model model, @ModelAttribute Bruger bruger){
        brugerService.opdaterProfil(bruger);
        model.addAttribute("opdateretProfil", bruger);
        return "redirect:/profil";


    }






}

package com.example.wishlist.controller;

import com.example.wishlist.models.Bruger;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.service.BaseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bruger")
@Controller
public class BrugerController {

    private final BaseService service;

    public BrugerController(BaseService service) {
        this.service = service;
    }


    @GetMapping("/opret")
    public String opretBruger(Model model) {
        model.addAttribute("bruger", new Bruger());
        return "opret-brugerform";
    }

    @PostMapping("/gem")
    public String gemBruger(@ModelAttribute Bruger bruger, Model model) {
        if (service.tjekUsernameDup(bruger.getUserName())){
            model.addAttribute("duplicate",true);
            return "opret-brugerform";
        } else {
            service.gemBruger(bruger);
            return "redirect:/";
        }
    }

    @GetMapping("/logind")
    public String visLogInd() {
        return "log-ind";
    }

    @PostMapping("/logind")
    public String logInd(@RequestParam("userName") String userName, @RequestParam("password") String password,
                         HttpSession session,
                         Model model) {
        if (service.logInd(userName, password)) {
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
            model.addAttribute("brugernavn", bruger.getUserName()); // Henter korrekt navn
            List<OnskeListe> wishLists = service.showAllOnskeListeByUser(bruger.getUserName());
            model.addAttribute("wishLists", wishLists);//                                        // henter Onske Liste
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
    public String redigerProfil(HttpSession session, Model model) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");

        if (bruger != null) {
            model.addAttribute("bruger", bruger);  // Henter korrekt navn
            return "profil-indstillinger";
        } else {
            return "log-ind";
        }
    }

    @PostMapping("/opdaterprofil")
    public String opdaterProfil(HttpSession session, @ModelAttribute Bruger opdateretBruger) {
        Bruger gammelBruger = (Bruger) session.getAttribute("bruger");

        service.opdaterProfil(gammelBruger, opdateretBruger);
        session.invalidate();

        return "redirect:/";


    }

    @PostMapping("/sletprofil")
    public String sletProfil(HttpSession session) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");
        service.sletBruger(bruger.getUserName());
        session.invalidate(); // Log brugeren ud efter sletning
        return "redirect:/";
    }


}

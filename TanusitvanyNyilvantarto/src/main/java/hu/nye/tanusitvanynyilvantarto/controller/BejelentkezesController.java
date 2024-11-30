package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


//Webes vezérlés beállítása
@Controller


public class BejelentkezesController {
    @GetMapping("/bejelentkezes")
    public String bejelentkezesFormMegnyitasa (Model model) {
        model.addAttribute("bejelentkezesFormMegnyitasa", new Felhasznalok());
        return "bejelentkezes";
    }
}

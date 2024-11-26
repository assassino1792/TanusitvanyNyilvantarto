package hu.nye.tanusitvanynyilvantarto.configuration;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

//Webes vezérlés beállítása
@Controller

//Weboldalak elérhetőségének beállításai

public class BejelentkezesController {
    @GetMapping("/")
    public String bejelentkezesFormMegnyitasa (Model model) {
        model.addAttribute("bejelentkezesFormMegnyitasa", new Felhasznalok());
        return "bejelentkezes";
    }
}

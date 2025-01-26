package hu.nye.tanusitvanynyilvantarto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BejelentkezesController {

    @GetMapping("/bejelentkezes")
    public String bejelentkezesFormMegnyitasa() {
        return "bejelentkezes";
    }
}

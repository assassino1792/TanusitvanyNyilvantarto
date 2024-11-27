package hu.nye.tanusitvanynyilvantarto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KezdooldalController {
    @GetMapping("/kezdooldal")
    public String kezdooldalFormMegnyitasa (Model model) {
        model.addAttribute("kezdooldalFormMegnyitasa");
        return "kezdooldal";
    }
}

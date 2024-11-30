package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TanusitvanyController {
    @GetMapping("/tanusitvanyok")
    public String tanusitvanyFormMegnyitasa (Model model) {
        model.addAttribute("tanusitvanyFormMegnyitasa" ,new Tanusitvanyok());
        return "tanusitvanyok";
    }
}

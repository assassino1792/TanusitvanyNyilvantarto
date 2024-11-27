package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.model.FelhasznalokModel;
import hu.nye.tanusitvanynyilvantarto.service.FelhasznalokService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/felhasznalok")
public class FelhasznalokController {

    private final FelhasznalokService felhasznalokService;

    public FelhasznalokController(FelhasznalokService felhasznalokService) {
        this.felhasznalokService = felhasznalokService;
    }

    @GetMapping
    public String felhasznalok(Model model) {
        List<FelhasznalokModel> felhasznalok = felhasznalokService.findAllModels();
        model.addAttribute("felhasznalok", felhasznalok);
        return "felhasznalok :: content";
    }


    @PostMapping("/add")
    public String addUser(@ModelAttribute FelhasznalokModel felhasznalo) {
        felhasznalokService.hozzaad(felhasznalo);
        return "redirect:/felhasznalok";
    }
}

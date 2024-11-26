package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.repository.FelhasznalokRepository;
import hu.nye.tanusitvanynyilvantarto.service.FelhasznalokService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class KezdooldalController {

    private final FelhasznalokService felhasznalokService;

    public KezdooldalController(FelhasznalokService felhasznalokService) {
        this.felhasznalokService = felhasznalokService;
    }
    @GetMapping("/kezdooldal")
    public String kezdooldal(Model model) {
        model.addAttribute("content", "kezdooldal :: content");
        return "kezdooldal";
    }
}

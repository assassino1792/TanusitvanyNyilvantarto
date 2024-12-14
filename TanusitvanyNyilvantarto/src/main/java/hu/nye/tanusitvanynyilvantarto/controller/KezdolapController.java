package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.service.FelhasznalokService;
import hu.nye.tanusitvanynyilvantarto.service.RiasztasService;
import hu.nye.tanusitvanynyilvantarto.service.TanusitvanyokService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class KezdolapController {

    private final FelhasznalokService felhasznalokService;
    private final TanusitvanyokService tanusitvanyokService;
    private final RiasztasService riasztasService;

    public KezdolapController(FelhasznalokService felhasznalokService, TanusitvanyokService tanusitvanyokService, RiasztasService riasztasService) {
        this.felhasznalokService = felhasznalokService;
        this.tanusitvanyokService = tanusitvanyokService;
        this.riasztasService = riasztasService;
    }

    @GetMapping("/")
    public String kezdooldal(Model model) {
        Map<String, Long> statusData = tanusitvanyokService.getActiveAndInactive();
        model.addAttribute("active", statusData.get("active"));
        model.addAttribute("inactive", statusData.get("inactive"));

        boolean shouldBlink = riasztasService.shouldBlink(); // Ellenőrzés a riasztásokhoz
        model.addAttribute("shouldBlink", shouldBlink);

        return "kezdolap";
    }

    @GetMapping("/kezdolap")
    public String kezdolap(Model model) {
        Map<String, Long> statusData = tanusitvanyokService.getActiveAndInactive();
        model.addAttribute("active", statusData.get("active"));
        model.addAttribute("inactive", statusData.get("inactive"));

        boolean shouldBlink = riasztasService.shouldBlink();
        model.addAttribute("shouldBlink", shouldBlink);

        return "kezdolap";
    }
}

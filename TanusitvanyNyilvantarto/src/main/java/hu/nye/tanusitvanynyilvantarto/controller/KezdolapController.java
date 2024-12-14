package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.service.FelhasznalokService;
import hu.nye.tanusitvanynyilvantarto.service.TanusitvanyokService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
public class KezdolapController {

    private final FelhasznalokService felhasznalokService;
    private final TanusitvanyokService tanusitvanyokService;

    public KezdolapController(FelhasznalokService felhasznalokService, TanusitvanyokService tanusitvanyokService) {
        this.felhasznalokService = felhasznalokService;
        this.tanusitvanyokService = tanusitvanyokService;
    }
    @GetMapping("/")
    public String kezdooldal(Model model) {
        model.addAttribute("content", "kezdolap :: content");
        Map<String, Long> statusData = tanusitvanyokService.getActiveAndInactive();
        model.addAttribute("active", statusData.get("active"));
        model.addAttribute("inactive", statusData.get("inactive"));
        return "kezdolap";
    }

    @GetMapping("/kezdolap")
    public String kezdolap(Model model) {
        Map<String, Long> statusData = tanusitvanyokService.getActiveAndInactive();
        model.addAttribute("active", statusData.get("active"));
        model.addAttribute("inactive", statusData.get("inactive"));
        return "kezdolap";
    }
}

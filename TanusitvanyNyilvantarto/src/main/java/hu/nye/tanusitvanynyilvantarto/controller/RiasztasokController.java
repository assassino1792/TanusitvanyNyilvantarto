package hu.nye.tanusitvanynyilvantarto.controller;

import ch.qos.logback.core.model.Model;
import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import hu.nye.tanusitvanynyilvantarto.model.TanusitvanyModel;
import hu.nye.tanusitvanynyilvantarto.service.RiasztasokService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/riasztasok")
public class RiasztasokController {
    private final RiasztasokService riasztasokService;

    public RiasztasokController(RiasztasokService riasztasokService) {
        this.riasztasokService = riasztasokService;
    }

    @GetMapping
    public String RiasztasbanLevok(org.springframework.ui.Model model) {
        List<Tanusitvanyok> riasztasTanusitvanyok = riasztasokService.WarningStatus();
        model.addAttribute("tanusitvanyok", riasztasTanusitvanyok);
        return "riasztasok";
    }
}

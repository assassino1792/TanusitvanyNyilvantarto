package hu.nye.tanusitvanynyilvantarto.controller;


import hu.nye.tanusitvanynyilvantarto.model.RiasztasokModel;
import hu.nye.tanusitvanynyilvantarto.model.UzenetTipus;
import hu.nye.tanusitvanynyilvantarto.service.RiasztasService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/riasztasok")
public class RiasztasokController {

    private final RiasztasService riasztasService;

    public RiasztasokController(RiasztasService riasztasService) {
        this.riasztasService = riasztasService;
    }

    @GetMapping
    public String getRiasztasok(org.springframework.ui.Model model) {
        List<RiasztasokModel> riasztasok = riasztasService.getAktivTanusitvanyok().stream()
                .map(tanusitvany -> {
                    UzenetTipus riasztasTipus = riasztasService.szamoljRiasztasTipust(tanusitvany);
                    return new RiasztasokModel(
                            tanusitvany.getId(),
                            tanusitvany.getSzerverNev(),
                            riasztasTipus != null ? riasztasTipus.name() : "OK",
                            riasztasTipus != null ? riasztasTipus.getMessage() : "Nincs riasztás"
                    );
                })
                .filter(riasztas -> riasztas.getRiasztasTipus().equals("Figyelmeztetés") ||
                        riasztas.getRiasztasTipus().equals("Kritikus"))
                .collect(Collectors.toList());

        model.addAttribute("riasztasok", riasztasok);
        boolean shouldBlink = riasztasService.shouldBlink();
        model.addAttribute("shouldBlink", shouldBlink);
        return "riasztasok";
    }


}


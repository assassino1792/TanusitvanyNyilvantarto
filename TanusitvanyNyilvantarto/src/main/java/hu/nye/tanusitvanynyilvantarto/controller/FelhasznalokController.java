package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.model.FelhasznalokModel;
import hu.nye.tanusitvanynyilvantarto.service.FelhasznalokService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "felhasznalok :: felhasznalokcontent";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute FelhasznalokModel felhasznalo) {
        felhasznalokService.hozzaad(felhasznalo);
        return "redirect:/kezdooldal";
    }

    @PostMapping("/edit")
    @ResponseBody
    public String updateUserAjax(@RequestBody FelhasznalokModel felhasznalo) {
        felhasznalokService.update(felhasznalo.getId(), felhasznalo);
        return "Sikeres mentés";
    }



    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        felhasznalokService.deleteById(id);
        return "redirect:/kezdooldal";
    }

}

package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.model.FelhasznalokModel;
import hu.nye.tanusitvanynyilvantarto.service.FelhasznalokService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "felhasznalok";
    }

    @PostMapping("/add")
    public String hozzaad(FelhasznalokModel model, RedirectAttributes redirectAttributes) {
        try {
            felhasznalokService.hozzaad(model);
            redirectAttributes.addFlashAttribute("successMessage", "Felhasználó sikeresen hozzáadva!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/felhasznalok";
    }

    @PostMapping("/edit")
    @ResponseBody
    public String updateUserAjax(@RequestBody FelhasznalokModel felhasznalo) {
        felhasznalokService.update(felhasznalo.getId(), felhasznalo);
        return "Sikeres mentés";
    }

    @PostMapping("/updatepw/{id}")
    public String updatePassword(@PathVariable("id") Long id, @RequestParam("jelszo") String jelszo, RedirectAttributes redirectAttributes) {
        try {
            felhasznalokService.updateJelszo(id, jelszo);
            redirectAttributes.addFlashAttribute("successMessage", "A jelszó sikeresen frissítve.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/felhasznalok";
    }


    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        felhasznalokService.deleteById(id);
        return "redirect:/felhasznalok";
    }

}

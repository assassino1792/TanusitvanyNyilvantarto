package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.model.FelhasznalokModel;
import hu.nye.tanusitvanynyilvantarto.service.FelhasznalokService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

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
        // Ha a felhasznalo modell nincs inicializálva, inicializáljuk
        if (!model.containsAttribute("felhasznalo")) {
            model.addAttribute("felhasznalo", new FelhasznalokModel());
        }
        return "felhasznalok";
    }

    @PostMapping("/add")
    public String hozzaad(@Valid @ModelAttribute("felhasznalo") FelhasznalokModel model,
                          BindingResult bindingResult, Model uiModel, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("felhasznalo", model);
            uiModel.addAttribute("errorMessage", "Hibás felhasználónév, email cím vagy jelszó.");
            return "felhasznalok";
        }
        try {
            felhasznalokService.hozzaad(model);
            redirectAttributes.addFlashAttribute("successMessage", "Felhasználó sikeresen hozzáadva!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/felhasznalok";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<FelhasznalokModel> getUserById(@PathVariable("id") Long id) {
        FelhasznalokModel user = felhasznalokService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute FelhasznalokModel felhasznalo, RedirectAttributes redirectAttributes) {
        try {
            felhasznalokService.update(id, felhasznalo);
            redirectAttributes.addFlashAttribute("successEditMessage", "A felhasználói adatok módosultak.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/felhasznalok";
    }

    @PostMapping("/updatepw/{id}")
    public String updatePassword(@PathVariable("id") Long id, @RequestParam("jelszo") String jelszo, RedirectAttributes redirectAttributes) {
        try {
            felhasznalokService.updateJelszo(id, jelszo);
            redirectAttributes.addFlashAttribute("successMessage", "A jelszó sikeresen frissítve.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "A jelszónak tartalmaznia kell " +
                    "kis-és nagybetűt, számot, valamint különleges karaktert!");
        }
        return "redirect:/felhasznalok";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            felhasznalokService.deleteById(id);
            redirectAttributes.addFlashAttribute("successDeletedMessage", "A felhasználó sikeresen eltávolítva.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/felhasznalok";
    }

}

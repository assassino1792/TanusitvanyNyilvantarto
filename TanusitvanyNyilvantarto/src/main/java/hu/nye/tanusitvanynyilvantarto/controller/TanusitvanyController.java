package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import hu.nye.tanusitvanynyilvantarto.model.FelhasznalokModel;
import hu.nye.tanusitvanynyilvantarto.model.TanusitvanyModel;
import hu.nye.tanusitvanynyilvantarto.service.TanusitvanyokService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping ("tanusitvanyok")
public class TanusitvanyController {

    private final TanusitvanyokService tanusitvanyokService;

    public TanusitvanyController(TanusitvanyokService tanusitvanyokService){
        this.tanusitvanyokService = tanusitvanyokService;
    }

    @GetMapping
    public String tanusitvanyok (Model model) {
        List<TanusitvanyModel> tanusitvanyok = tanusitvanyokService.findAll();
        model.addAttribute("tanusitvanyok" , tanusitvanyok);
        if (!model.containsAttribute("tanusitvany")) {
            model.addAttribute("tanusitvany", new FelhasznalokModel());
        }
        return "tanusitvanyok";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("tanusitvany") TanusitvanyModel model, RedirectAttributes redirectAttributes){
        try {
            tanusitvanyokService.save(model);
            redirectAttributes.addFlashAttribute("successMessage","Az új bejegyzés rögzítésre került.");
        } catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/tanusitvanyok";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try {
            tanusitvanyokService.delete(id);
            redirectAttributes.addFlashAttribute("successDeletedMessage",  "A tanúsítvány törlésre került" );
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorDeletedMessage", e.getMessage());
        }
        return "redirect:/tanusitvanyok";
    }

}


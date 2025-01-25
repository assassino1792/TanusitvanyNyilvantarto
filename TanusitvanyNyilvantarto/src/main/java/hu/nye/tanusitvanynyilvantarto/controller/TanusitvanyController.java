package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.model.FelhasznalokModel;
import hu.nye.tanusitvanynyilvantarto.model.TanusitvanyModel;
import hu.nye.tanusitvanynyilvantarto.service.RiasztasService;
import hu.nye.tanusitvanynyilvantarto.service.TanusitvanyokService;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping ("tanusitvanyok")
public class TanusitvanyController {

    private final TanusitvanyokService tanusitvanyokService;
    private final RiasztasService riasztasService;

    public TanusitvanyController(TanusitvanyokService tanusitvanyokService, RiasztasService riasztasService){
        this.tanusitvanyokService = tanusitvanyokService;
        this.riasztasService = riasztasService;
    }

    @GetMapping
    public String tanusitvanyok (Model model) {
        List<TanusitvanyModel> tanusitvanyok = tanusitvanyokService.findAll();
        model.addAttribute("tanusitvanyok" , tanusitvanyok);
        if (!model.containsAttribute("tanusitvany")) {
            model.addAttribute("tanusitvany", new FelhasznalokModel());
        }
        boolean shouldBlink = riasztasService.shouldBlink(); // Ellenőrzés a riasztásokhoz
        model.addAttribute("shouldBlink", shouldBlink);
        return "tanusitvanyok";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("tanusitvany") TanusitvanyModel model, RedirectAttributes redirectAttributes){
        try {
            tanusitvanyokService.save(model);
            redirectAttributes.addFlashAttribute("successMessage","Az új bejegyzés rögzítésre került!");
        } catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/tanusitvanyok";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try {
            tanusitvanyokService.delete(id);
            redirectAttributes.addFlashAttribute("successDeletedMessage",  "A tanúsítvány törlésre került!" );
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorDeletedMessage", e.getMessage());
        }
        return "redirect:/tanusitvanyok";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute @Valid TanusitvanyModel tanusitvany, RedirectAttributes redirectAttributes) {
        try {
            tanusitvanyokService.update(id, tanusitvany);
            redirectAttributes.addFlashAttribute("successEditMessage", "Tanúsítvány adatok módosultak.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorEditMessage", e.getMessage());
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorEditMessage",  e.getMessage());
        }
        return "redirect:/tanusitvanyok";
    }


    @GetMapping("/{id}")
    @ResponseBody
    public TanusitvanyModel getById(@PathVariable("id") Long id) {
        return tanusitvanyokService.findById(id);
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportTanusitvanyok() {
        ByteArrayInputStream csvData = tanusitvanyokService.exportTanusitvanyokToCSV();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tanusitvanyok.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(csvData));
    }

}


package hu.nye.tanusitvanynyilvantarto.controller;

import hu.nye.tanusitvanynyilvantarto.repository.TanusitvanyokRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutokitoltoController {

    @Autowired
    private TanusitvanyokRepository repository;

    @GetMapping("/autocomplete")
    public List<String> autocomplete(@RequestParam("query") String query) {
        // Hívja a repository megfelelő metódusát
        return repository.findMatchingSzerverNev(query);
    }
}

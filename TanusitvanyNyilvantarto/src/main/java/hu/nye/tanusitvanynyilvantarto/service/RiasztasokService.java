package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.Riasztasok;
import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import hu.nye.tanusitvanynyilvantarto.repository.RiasztasokRepository;
import hu.nye.tanusitvanynyilvantarto.repository.TanusitvanyokRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiasztasokService {
    private final RiasztasokRepository riasztasokRepository;
    private final TanusitvanyokRepository tanusitvanyokRepository;

    public RiasztasokService(RiasztasokRepository riasztasokRepository, TanusitvanyokRepository tanusitvanyokRepository) {
        this.riasztasokRepository = riasztasokRepository;
        this.tanusitvanyokRepository = tanusitvanyokRepository;
    }

    public List<Tanusitvanyok> WarningStatus() {
        List<Tanusitvanyok> aktivTanusitvanyok = tanusitvanyokRepository.findByStatusz("Aktív");
        LocalDate warning = LocalDate.now().plusWeeks(2);

        List<Tanusitvanyok> riasztottTanusitvanyok = aktivTanusitvanyok.stream()
                .filter(tanusitvany -> tanusitvany.getLejaratiIdo().isBefore(warning))
                .collect(Collectors.toList());

        for (Tanusitvanyok tanusitvany : riasztottTanusitvanyok) {
            Riasztasok riasztas = new Riasztasok();
            riasztas.setTanusitvany_id(tanusitvany);
            riasztas.setRiasztasTipus("A tanúsítvány hamarosan lejár" + "nap múlva." );
            riasztas.setRiasztasIdeje(LocalDate.now());
            riasztas.setRiasztasStatusz(true);

            // Riasztás mentése az adatbázisba
            riasztasokRepository.save(riasztas);
        }

        return riasztottTanusitvanyok;
    }
}

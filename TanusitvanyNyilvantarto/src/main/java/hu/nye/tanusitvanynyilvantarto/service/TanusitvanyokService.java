package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import hu.nye.tanusitvanynyilvantarto.model.TanusitvanyModel;
import hu.nye.tanusitvanynyilvantarto.model.UzenetTipus;
import hu.nye.tanusitvanynyilvantarto.repository.TanusitvanyokRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TanusitvanyokService {
    private final TanusitvanyokRepository tanusitvanyokRepository;

    public TanusitvanyokService(TanusitvanyokRepository tanusitvanyokRepository) {
        this.tanusitvanyokRepository = tanusitvanyokRepository;
    }

    public List<TanusitvanyModel> findAll() {
        return tanusitvanyokRepository.findAll().stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public TanusitvanyModel findById(Long id) {
        return tanusitvanyokRepository.findById(id)
                .map(this::convertToModel)
                .orElseThrow(() -> new RuntimeException("Tanúsítvány nem található!"));
    }

    public void save(TanusitvanyModel model) {
        if (model.getKezdetiIdo().isAfter(model.getLejaratiIdo())) {
            throw new IllegalArgumentException("A tanúsítvány lejárati ideje nem lehet kisebb, mint tanúsítvány kezdeti ideje!");
        }
        tanusitvanyokRepository.save(convertToEntity(model));
    }

    public void delete(Long id) {
        if (!tanusitvanyokRepository.existsById(id)) {
            throw new RuntimeException("Tanúsítvány nem található!");
        }
        tanusitvanyokRepository.deleteById(id);
    }

    public void update(Long id, TanusitvanyModel updateModel) {
        if (updateModel.getKezdetiIdo().isAfter(updateModel.getLejaratiIdo())) {
            throw new IllegalArgumentException("A tanúsítvány lejárati ideje nem lehet kisebb, mint tanúsítvány kezdete!");
        }

        Tanusitvanyok existingEntity = tanusitvanyokRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " id nem található!"));

        existingEntity.setSzerverNev(updateModel.getSzerverNev());
        existingEntity.setTanusitvanyTipus(updateModel.getTanusitvanyTipus());
        existingEntity.setKezdetiIdo(updateModel.getKezdetiIdo());
        existingEntity.setLejaratiIdo(updateModel.getLejaratiIdo());
        existingEntity.setStatusz(updateModel.getStatusz());
        existingEntity.setKiallitoNeve(updateModel.getKiallitoNeve());
        existingEntity.setReszletek(updateModel.getReszletek());

        tanusitvanyokRepository.save(existingEntity);
    }

    public Map<String, Long> getActiveAndInactive() {
        long activeCount = tanusitvanyokRepository.countByStatusz("Aktív");
        long inactiveCount = tanusitvanyokRepository.countByStatusz("Inaktív");

        Map<String, Long> result = new HashMap<>();
        result.put("active", activeCount);
        result.put("inactive", inactiveCount);
        return result;
    }

    private TanusitvanyModel convertToModel(Tanusitvanyok entity) {
        return new TanusitvanyModel(
                entity.getId(),
                entity.getSzerverNev(),
                entity.getTanusitvanyTipus(),
                entity.getKezdetiIdo(),
                entity.getLejaratiIdo(),
                entity.getStatusz(),
                entity.getKiallitoNeve(),
                entity.getReszletek(),
                entity.getLetrehozva(),
                entity.getRiasztasTipus(),
                entity.isAktiv(),
                entity.isExpiredEmailSent()
        );
    }

    private Tanusitvanyok convertToEntity(TanusitvanyModel model) {
        return new Tanusitvanyok(
                model.getId(),
                model.getSzerverNev(),
                model.getTanusitvanyTipus(),
                model.getKezdetiIdo(),
                model.getLejaratiIdo(),
                model.getStatusz(),
                model.getKiallitoNeve(),
                model.getReszletek(),
                model.getLetrehozva(),
                model.isExpiredEmailSent()
        );
    }

    public ByteArrayInputStream exportTanusitvanyokToCSV() {
        List<Tanusitvanyok> tanusitvanyok = tanusitvanyokRepository.findAll();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            // BOM hozzáadása az UTF-8 kódoláshoz
            out.write(0xEF);
            out.write(0xBB);
            out.write(0xBF);

            try (PrintWriter writer = new PrintWriter(out, true, StandardCharsets.UTF_8)) {
                // Fejléc létrehozása
                writer.println("ID,Szerver neve,Tanúsítvány típusa,Kezdeti idő,Lejárati idő,Státusz,Kiállító neve,Részletek");

                // Sorok exportálása
                for (Tanusitvanyok tanusitvany : tanusitvanyok) {
                    String reszletekNoLineBreak = tanusitvany.getReszletek().replaceAll("\\r?\\n", " "); // Sortörések cseréje szóközre

                    writer.printf("%d,%s,%s,%s,%s,%s,%s,%s%n",
                            tanusitvany.getId(),
                            tanusitvany.getSzerverNev(),
                            tanusitvany.getTanusitvanyTipus(),
                            tanusitvany.getKezdetiIdo(),
                            tanusitvany.getLejaratiIdo(),
                            tanusitvany.getStatusz(),
                            tanusitvany.getKiallitoNeve(),
                            reszletekNoLineBreak); // Formázott "részletek" mező
                }

                writer.flush();
            }

            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Hiba a CSV exportálás során", e);
        }
    }



}

package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.model.FelhasznalokModel;
import hu.nye.tanusitvanynyilvantarto.repository.FelhasznalokRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FelhasznalokService {

    private final FelhasznalokRepository felhasznalokRepository;

    public FelhasznalokService(FelhasznalokRepository felhasznalokRepository) {
        this.felhasznalokRepository = felhasznalokRepository;
    }

    public List<FelhasznalokModel> findAll() {
        return felhasznalokRepository.findAll().stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    public FelhasznalokModel findById(Long id) {
        return felhasznalokRepository.findById(id)
                .map(this::convertToModel)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található: " + id));
    }

    public void hozzaad(FelhasznalokModel model) {
        if (felhasznalokRepository.existsByFelhasznaloNev(model.getFelhasznalonev())) {
            throw new RuntimeException("A megadott felhasználónév már létezik!");
        }
        if (felhasznalokRepository.existsByEmail(model.getEmail())) {
            throw new RuntimeException("A megadott email cím már létezik!");
        }
        felhasznalokRepository.save(convertToEntity(model));
    }

    public void deleteById(Long id) {
        if (!felhasznalokRepository.existsById(id)) {
            throw new RuntimeException("Felhasználó nem található: " + id);
        }
        felhasznalokRepository.deleteById(id);
    }
    public void update(Long id, FelhasznalokModel updatedModel) {
        Felhasznalok existingUser = felhasznalokRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található: " + id));

        Optional<Felhasznalok> userWithSameUsername = felhasznalokRepository.findByFelhasznaloNev(updatedModel.getFelhasznalonev());
        if (userWithSameUsername.isPresent() && !userWithSameUsername.get().getId().equals(id)) {
            throw new RuntimeException("A felhasználónév már létezik: " + updatedModel.getFelhasznalonev());
        }
        existingUser.setFelhasznaloNev(updatedModel.getFelhasznalonev());
        existingUser.setVezetekNev(updatedModel.getVezeteknev());
        existingUser.setKeresztNev(updatedModel.getKeresztnev());
        existingUser.setEmail(updatedModel.getEmail());

        felhasznalokRepository.save(existingUser);
    }

    public void updateJelszo(Long id, String ujJelszo) {
        Felhasznalok existingUser = felhasznalokRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található: " + id));
        existingUser.setJelszo(ujJelszo);
        felhasznalokRepository.save(existingUser);
    }

    //Modelre kovertálás
    private FelhasznalokModel convertToModel(Felhasznalok entity) {
        return new FelhasznalokModel(
                entity.getId(),
                entity.getFelhasznaloNev(),
                entity.getVezetekNev(),
                entity.getKeresztNev(),
                entity.getEmail(),
                entity.getJelszo(),
                entity.getLetrehozva()

        );
    }
    // Entitásra konvertálás
    private Felhasznalok convertToEntity(FelhasznalokModel model){
        return new Felhasznalok(
                model.getId(),
                model.getFelhasznalonev(),
                model.getVezeteknev(),
                model.getKeresztnev(),
                model.getEmail(),
                model.getJelszo(),
                model.getLetrehozva()
        );
    }

}

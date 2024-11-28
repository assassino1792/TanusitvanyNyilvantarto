package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.model.FelhasznalokModel;
import hu.nye.tanusitvanynyilvantarto.repository.FelhasznalokRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FelhasznalokService {

    private final FelhasznalokRepository felhasznalokRepository;

    public FelhasznalokService(FelhasznalokRepository felhasznalokRepository) {
        this.felhasznalokRepository = felhasznalokRepository;
    }

    public List<FelhasznalokModel> findAllModels() {
        return felhasznalokRepository.findAll().stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }
/*
    public FelhasznalokModel findById(Long id) {
        return felhasznalokRepository.findById(id)
                .map(this::convertToModel)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található id: " + id));
    }
*/
    public void hozzaad(FelhasznalokModel model) {
        felhasznalokRepository.save(convertToEntity(model));
    }
    public List<Felhasznalok> getAllFelhasznalok() {
        return felhasznalokRepository.findAll();
    }


   //Modelre kovertálás
    private FelhasznalokModel convertToModel(Felhasznalok entity) {
        return new FelhasznalokModel(
                entity.getId(),
                entity.getFelhasznaloNev(),
                entity.getVezetekNev(),
                entity.getKeresztNev(),
                entity.getLetrehozva(),
                entity.getFormazottDatum()

        );
    }
// Entitásra konvertálás
    private Felhasznalok convertToEntity(FelhasznalokModel model){
        return new Felhasznalok(
                model.getId(),
                model.getFelhasznalonev(),
                model.getVezeteknev(),
                model.getKeresztnev(),
                model.getLetrehozva(),
                model.getFormazottDatum()
        );
    }

}

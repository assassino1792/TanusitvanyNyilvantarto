package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import hu.nye.tanusitvanynyilvantarto.model.FelhasznalokModel;
import hu.nye.tanusitvanynyilvantarto.model.TanusitvanyModel;
import hu.nye.tanusitvanynyilvantarto.repository.TanusitvanyokRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .orElseThrow(() -> new RuntimeException("Tanúsítvány nem található:" + id));
    }

    public void save(TanusitvanyModel model) {
        tanusitvanyokRepository.save(convertToEntity(model));
    }

    public void delete(Long id){
        if(!tanusitvanyokRepository.existsById(id)) {
            throw new RuntimeException("Tanúsítvány nem található:" +id);
        }
        tanusitvanyokRepository.deleteById(id);
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
                entity.getLetrehozva(),
                entity.getModositva()
        );
    }

    private Tanusitvanyok convertToEntity(TanusitvanyModel model){
        return new Tanusitvanyok(
                model.getId(),
                model.getSzerverNev(),
                model.getTanusitvanyTipus(),
                model.getKezdetiIdo(),
                model.getLejaratiIdo(),
                model.getStatusz(),
                model.getKiallitoNeve(),
                model.getLetrehozva(),
                model.getModositva()
        );
    }

}

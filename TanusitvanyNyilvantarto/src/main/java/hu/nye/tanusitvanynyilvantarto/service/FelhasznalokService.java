package hu.nye.tanusitvanynyilvantarto.service;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.entity.Jogosultsag;
import hu.nye.tanusitvanynyilvantarto.model.FelhasznalokModel;
import hu.nye.tanusitvanynyilvantarto.model.Szerepkor;
import hu.nye.tanusitvanynyilvantarto.repository.FelhasznalokRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FelhasznalokService {

    private final FelhasznalokRepository felhasznalokRepository;
    private final PasswordEncoder passwordEncoder;

    public FelhasznalokService(FelhasznalokRepository felhasznalokRepository, PasswordEncoder passwordEncoder) {
        this.felhasznalokRepository = felhasznalokRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<FelhasznalokModel> findAll() {
        return felhasznalokRepository.findAll().stream()
                .map(user -> {
                    FelhasznalokModel model = convertToModel(user);

                    // Szerepkör betöltése
                    String roles = user.getJogosultsagok().stream()
                            .map(jogosultsag -> jogosultsag.getAuthority().name()) // Szerepkör neve (USER, ADMIN)
                            .collect(Collectors.joining(", "));
                    model.setSzerepkor(roles);

                    return model;
                })
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
        String hashedPassword = passwordEncoder.encode(ujJelszo);
        existingUser.setJelszo(hashedPassword);
        felhasznalokRepository.save(existingUser);
    }
    public boolean isCurrentUserAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            return authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        }
        return false;
    }

    //Modelre kovertálás
    private FelhasznalokModel convertToModel(Felhasznalok entity) {
        String roles = entity.getJogosultsagok().stream()
                .map(jogosultsag -> jogosultsag.getAuthority().name()) // Enum névként (USER, ADMIN)
                .collect(Collectors.joining(", "));

        return new FelhasznalokModel(
                entity.getId(),
                entity.getFelhasznaloNev(),
                entity.getVezetekNev(),
                entity.getKeresztNev(),
                entity.getEmail(),
                entity.getJelszo(),
                roles,
                entity.getLetrehozva()
        );
    }
    // Entitásra konvertálás
    private Felhasznalok convertToEntity(FelhasznalokModel model) {
        Felhasznalok entity = new Felhasznalok(
                model.getId(),
                model.getFelhasznalonev(),
                model.getVezeteknev(),
                model.getKeresztnev(),
                model.getEmail(),
                model.getJelszo(),
                new HashSet<>(),
                model.getLetrehozva()
        );

        // Szerepkörök hozzáadása
        if (model.getSzerepkor() != null) {
            Jogosultsag jogosultsag = new Jogosultsag();
            jogosultsag.setAuthority(Szerepkor.valueOf(model.getSzerepkor())); // Enum konverzió
            jogosultsag.setFelhasznalo(entity);
            entity.getJogosultsagok().add(jogosultsag);
        }

        return entity;
    }


}

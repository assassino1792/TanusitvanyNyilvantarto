package hu.nye.tanusitvanynyilvantarto.repository;

import hu.nye.tanusitvanynyilvantarto.entity.Jogosultsag;
import hu.nye.tanusitvanynyilvantarto.model.Szerepkor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JogosultsagRepository extends JpaRepository<Jogosultsag, Long> {
    Optional<Jogosultsag> findByAuthority(Szerepkor authority);
}

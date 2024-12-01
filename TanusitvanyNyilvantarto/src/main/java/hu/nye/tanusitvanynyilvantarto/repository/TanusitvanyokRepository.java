package hu.nye.tanusitvanynyilvantarto.repository;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TanusitvanyokRepository extends JpaRepository<Tanusitvanyok, Long> {
}

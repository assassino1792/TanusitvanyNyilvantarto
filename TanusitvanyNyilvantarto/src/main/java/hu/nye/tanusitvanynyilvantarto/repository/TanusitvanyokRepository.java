package hu.nye.tanusitvanynyilvantarto.repository;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TanusitvanyokRepository extends JpaRepository<Tanusitvanyok, Long> {
    List<Tanusitvanyok> findByStatusz(String statusz);
}

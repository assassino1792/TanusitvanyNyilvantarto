package hu.nye.tanusitvanynyilvantarto.repository;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TanusitvanyokRepository extends JpaRepository<Tanusitvanyok, Long> {
    List<Tanusitvanyok> findByStatusz(String statusz);
    long countByStatusz(String statusz);
    boolean existsBySzerverNev(String szerverNev);
}

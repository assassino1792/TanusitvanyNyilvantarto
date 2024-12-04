package hu.nye.tanusitvanynyilvantarto.repository;

import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import hu.nye.tanusitvanynyilvantarto.entity.UzenetTipusok;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UzenetTipusokRepository extends JpaRepository<UzenetTipusok, Long> {
}

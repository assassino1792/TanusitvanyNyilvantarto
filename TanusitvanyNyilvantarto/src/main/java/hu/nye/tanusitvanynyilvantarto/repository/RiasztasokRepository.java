package hu.nye.tanusitvanynyilvantarto.repository;

import hu.nye.tanusitvanynyilvantarto.entity.Riasztasok;
import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RiasztasokRepository extends JpaRepository <Riasztasok, Long>  {
}

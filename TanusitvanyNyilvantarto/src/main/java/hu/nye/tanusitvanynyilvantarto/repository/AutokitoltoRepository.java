package hu.nye.tanusitvanynyilvantarto.repository;

import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutokitoltoRepository extends JpaRepository<Tanusitvanyok, Long> {
    @Query("SELECT t.szerverNev FROM Tanusitvanyok t WHERE LOWER(t.szerverNev) LIKE LOWER(CONCAT(:query, '%'))")
    List<String> findMatchingSzerverNev(@Param("query") String query);
}

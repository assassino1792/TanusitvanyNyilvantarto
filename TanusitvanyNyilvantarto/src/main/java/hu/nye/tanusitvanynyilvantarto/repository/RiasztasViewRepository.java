package hu.nye.tanusitvanynyilvantarto.repository;

import hu.nye.tanusitvanynyilvantarto.model.RiasztasViewModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiasztasViewRepository extends CrudRepository<RiasztasViewModel, Long> {

    @Query(value = "SELECT * FROM riasztasok_view", nativeQuery = true)
    List<RiasztasViewModel> findAllRiasztasok();
}

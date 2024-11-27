package hu.nye.tanusitvanynyilvantarto.repository;

import hu.nye.tanusitvanynyilvantarto.entity.Felhasznalok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
Spring Data JPA repository interfész, amely az alapvető adatbázis-műveletek (CRUD – Create, Read, Update, Delete)
előre definiált implementációját biztosítja. A Spring automatikusan létrehozza a háttérben,
findAll(), save(), deleteById() vagy findById() metódusok implementációját.
 */
@Repository
public interface FelhasznalokRepository extends JpaRepository<Felhasznalok,Long> {

}

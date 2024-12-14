package hu.nye.tanusitvanynyilvantarto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RiasztasViewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tanusitvanyId;
    private String riasztasTipus;

    public RiasztasViewModel(Long tanusitvanyId, String riasztasTipus) {
        this.tanusitvanyId = tanusitvanyId;
        this.riasztasTipus = riasztasTipus;
    }
 }







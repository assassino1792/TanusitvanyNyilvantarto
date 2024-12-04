package hu.nye.tanusitvanynyilvantarto.model;

import hu.nye.tanusitvanynyilvantarto.entity.Tanusitvanyok;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public class RiasztasokModel {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter

    public class Riasztasok {
        private Long id;
        private Tanusitvanyok tanusitvany_id;
        private String riasztasTipus;
        private LocalDate riasztasIdeje;
        private Boolean riasztasStatusz;
    }
}

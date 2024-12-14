package hu.nye.tanusitvanynyilvantarto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RiasztasokModel {
    private Long id;
    private String szerverNev;
    private String riasztasTipus;
    private String uzenet;
}

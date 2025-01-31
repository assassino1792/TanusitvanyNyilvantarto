package hu.nye.tanusitvanynyilvantarto.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TanusitvanyModel {

    private Long id;

    @NotNull
    @Size(max = 30)
    private String szerverNev;

    @NotNull
    private String tanusitvanyTipus;

    @NotNull
    private LocalDate kezdetiIdo;

    @NotNull
    private LocalDate lejaratiIdo;

    @NotNull
    private String statusz;

    @NotNull
    private String kiallitoNeve;

    private String reszletek;

    private LocalDateTime letrehozva;

    private String riasztasTipus;

    private boolean aktiv;

    private boolean expiredEmailSent;


}

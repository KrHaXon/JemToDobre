package com.JemToDobre.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Pozycje_Zamowienia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Pozycja_Zamowienia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Zamowienia", nullable = false)
    private Zamowienia zamowienia;

    private Integer ID_Pozycji;
    private Double Cena;

    public Pozycje_Zamowienia(Zamowienia zamowienia, Integer ID_Pozycji, Double cena) {
        this.zamowienia = zamowienia;
        this.ID_Pozycji = ID_Pozycji;
        this.Cena = cena;
    }
}

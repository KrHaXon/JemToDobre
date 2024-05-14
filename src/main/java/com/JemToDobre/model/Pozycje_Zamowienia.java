package com.JemToDobre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Pozycje_Zamowienia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Pozycja_Zamowienia;
    private Integer ID_Zamowienia;
    private Integer ID_Pozycji;
    private Integer Ilosc;
    private Double Cena;
}

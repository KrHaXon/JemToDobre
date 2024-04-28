package com.JemToDobre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
public class Pozycje_Menu {
    @Id
    @GeneratedValue
    private Long ID_Pozycja_Menu;
    private Integer ID_Kategoria;
    private Integer ID_Alergen;
    private String Nazwa_Pozycji;
    private String Opis;
    private Double Cena;
    private String Skladniki;
}

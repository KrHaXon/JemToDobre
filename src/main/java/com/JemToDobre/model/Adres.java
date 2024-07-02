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
@Getter
@Setter
@Entity

public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_Adres;
    private String Ulica;
    private Integer Nr_Domu;
    private Integer Nr_Mieszkania;
    private String Miejscowosc;
    private String Kod_Pocztowy;

    public Adres(String ulica, Integer nr_Domu, Integer nr_Mieszkania, String miejscowosc, String kod_Pocztowy) {
        this.Ulica = ulica;
        this.Nr_Domu = nr_Domu;
        this.Nr_Mieszkania = nr_Mieszkania;
        this.Miejscowosc = miejscowosc;
        this.Kod_Pocztowy = kod_Pocztowy;
    }
}

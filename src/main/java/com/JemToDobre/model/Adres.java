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

public class Adres {
    @Id
    @GeneratedValue
    private Long ID_Adres;
    private String Ulica;
    private Integer Nr_Domu;
    private Integer Nr_Mieszkania;
    private String Miejscowosc;
    private String Kod_Pocztowy;
}

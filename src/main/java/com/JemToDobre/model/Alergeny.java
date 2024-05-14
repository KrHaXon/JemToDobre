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

public class Alergeny {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID_Alergen;
    private String Nazwa_Alergenu;
    private String Opis_Alergenu;
    private String Typ_Alergenu;
    private String Zrodlo_Alergenu;
}

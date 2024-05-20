package com.JemToDobre.model;

import jakarta.persistence.*;
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
@Table(name="Alergeny")

public class Alergeny {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID_Alergen;
    private String Nazwa_Alergenu;
    private String Opis_Alergenu;
    private String Typ_Alergenu;
    private String Zrodlo_Alergenu;

    public Alergeny( String Nazwa_Alergenu,String Opis_Alergenu, String Typ_Alergenu, String Zrodlo_Alergenu  )
    {
        this.Nazwa_Alergenu = Nazwa_Alergenu;
        this.Opis_Alergenu = Opis_Alergenu;
        this.Typ_Alergenu = Typ_Alergenu;
        this.Zrodlo_Alergenu = Zrodlo_Alergenu;
    }
}

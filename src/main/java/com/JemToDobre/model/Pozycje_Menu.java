package com.JemToDobre.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="pozycje_menu")

public class Pozycje_Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_Pozycja_Menu;

    private String Nazwa_Pozycji;
    private String Opis;
    private Double Cena;
    private String Skladniki;

    @ManyToOne
    @JoinColumn(name = "ID_Kategoria", referencedColumnName = "ID_Kategoria")
    private Kategoria_Menu kategoria;

    @ManyToOne
    @JoinColumn(name = "ID_Alergen", referencedColumnName = "ID_Alergen")
    private Alergeny alergen;

    public Pozycje_Menu(String nazwa, String opis, Double cena, String skladniki, Kategoria_Menu kategoria, Alergeny alergen) {
        this.Nazwa_Pozycji = nazwa;
        this.Opis = opis;
        this.Cena = cena;
        this.Skladniki = skladniki;
        this.kategoria = kategoria;
        this.alergen = alergen;
    }

    @Override
    public String toString() {
        return "Pozycje_Menu{" +
                "ID_Pozycja_Menu=" + ID_Pozycja_Menu +
                ", kategoria=" + kategoria.getNazwa_Kategorii() +
                ", alergen=" + alergen.getNazwa_Alergenu() +
                ", Nazwa_Pozycji='" + Nazwa_Pozycji + '\'' +
                ", Opis='" + Opis + '\'' +
                ", Cena=" + Cena +
                ", Skladniki='" + Skladniki + '\'' +
                '}';
    }
}

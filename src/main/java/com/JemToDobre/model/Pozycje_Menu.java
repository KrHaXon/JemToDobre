package com.JemToDobre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Pozycje_Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_Pozycja_Menu;
    private Integer ID_Kategoria;
    private Integer ID_Alergen;
    private String Nazwa_Pozycji;
    private String Opis;
    private Double Cena;
    private String Skladniki;

    public Pozycje_Menu(Integer ID_Kategoria, Integer ID_Alergen, String nazwa_Pozycji, String opis, Double cena, String skladniki) {
        this.ID_Kategoria = ID_Kategoria;
        this.ID_Alergen = ID_Alergen;
        this.Nazwa_Pozycji = nazwa_Pozycji;
        this.Opis = opis;
        this.Cena = cena;
        this.Skladniki = skladniki;
    }

    @Override
    public String toString() {
        return "Pozycje_Menu{" +
                "ID_Pozycja_Menu=" + ID_Pozycja_Menu +
                ", ID_Kategoria=" + ID_Kategoria +
                ", ID_Alergen=" + ID_Alergen +
                ", Nazwa_Pozycji='" + Nazwa_Pozycji + '\'' +
                ", Opis='" + Opis + '\'' +
                ", Cena=" + Cena +
                ", Skladniki='" + Skladniki + '\'' +
                '}';
    }
}

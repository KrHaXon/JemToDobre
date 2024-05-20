package com.JemToDobre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Kategoria_Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_Kategoria;
    private String Nazwa_Kategorii;

    public Kategoria_Menu(String nazwa_Kategorii) {
        Nazwa_Kategorii = nazwa_Kategorii;
    }

    @Override
    public String toString() {
        return "Kategoria_Menu{" +
                "ID_Kategoria=" + ID_Kategoria +
                ", Nazwa_Kategorii='" + Nazwa_Kategorii + '\'' +
                '}';
    }
}

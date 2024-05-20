package com.JemToDobre.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Zamowienia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Zamowienia;
    private Integer ID_Uzytkownik;
    private Integer Adres;
    private String Data_Zamowienia;
    private String Status_Zamowienia;
    private String Dodatkowe_Informacje;
    private String Nr_Faktury;
    private String Data_Realizacji;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_Zamowienia")
    private List<Pozycje_Zamowienia> pozycjeZamowienia;

    private Double lacznaCena;

    public void addPozycjaZamowienia(Pozycje_Zamowienia pozycjaZamowienia) {
        this.pozycjeZamowienia.add(pozycjaZamowienia);
    }

    public void setLacznaCena(Double lacznaCena) {
        this.lacznaCena = lacznaCena;
    }

    public Double getLacznaCena() {
        return lacznaCena;
    }
}

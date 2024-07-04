package com.JemToDobre.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Zamowienia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_Zamowienia;
    private Integer ID_Uzytkownik;
    private Integer ID_Adres;
    private LocalDateTime Data_Zamowienia;
    private String Status_Zamowienia;
    private String Dodatkowe_Informacje;
    private String Nr_Faktury;
    private LocalDateTime Data_Realizacji;

    @OneToMany(mappedBy = "zamowienia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pozycje_Zamowienia> pozycjeZamowienia;

    private Double lacznaCena;
    private String Opcje_Zamówienia;
    private String Metoda_Płatności;

    public void addPozycjaZamowienia(Pozycje_Zamowienia pozycjaZamowienia) {
        pozycjaZamowienia.setZamowienia(this);
        this.pozycjeZamowienia.add(pozycjaZamowienia);
    }

    public void setLacznaCena(Double lacznaCena) {
        this.lacznaCena = lacznaCena;
    }

    public Double getLacznaCena() {
        return lacznaCena;
    }

    public Zamowienia(Integer ID_Uzytkownik, Integer ID_Adres, LocalDateTime data_Zamowienia, String status_Zamowienia, String dodatkowe_Informacje, String nr_Faktury, LocalDateTime data_Realizacji, List<Pozycje_Zamowienia> pozycjeZamowienia, Double lacznaCena, String opcje_Zamówienia, String metoda_Płatności) {
        this.ID_Uzytkownik = ID_Uzytkownik;
        this.ID_Adres = ID_Adres;
        this.Data_Zamowienia = data_Zamowienia;
        this.Status_Zamowienia = status_Zamowienia;
        this.Dodatkowe_Informacje = dodatkowe_Informacje;
        this.Nr_Faktury = nr_Faktury;
        this.Data_Realizacji = data_Realizacji;
        this.pozycjeZamowienia = pozycjeZamowienia;
        this.lacznaCena = lacznaCena;
        this.Opcje_Zamówienia = opcje_Zamówienia;
        this.Metoda_Płatności = metoda_Płatności;
    }
    public Zamowienia()
    {

    }
}

package com.JemToDobre.model;

import com.JemToDobre.model.toenum.StanZamowienia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import com.JemToDobre.model.toenum.StanZamowienia;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder

public class Zamowienia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID_Zamowienia;
    private Integer ID_Uzytkownik;
    private Integer Adres;
    private String Data_Zamowienia;
    private StanZamowienia Status_Zamowienia;
    private String Dodatkowe_Informacje;
    private String Nr_Faktury;
    private String Data_Realizacji;
}

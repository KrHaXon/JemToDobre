package com.JemToDobre.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.JemToDobre.model.toenum.RodzajUzytkownika;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Uzytkownicy")
public class Uzytkownicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Uzytkownik;
    private String Nazwa_Uzytkownika;
    private String Email;
    private String Haslo;
    private String Telefon;
    private RodzajUzytkownika Typ_Uzytkownika;
    public Uzytkownicy(String name, String email, String password)
    {
        this.Nazwa_Uzytkownika = name;
        this.Email = email;
        this.Haslo = password;
    }
}

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
    private Integer ID_Uzytkownik;
    private String Nazwa_Uzytkownika;
    @Column(name = "email")
    private String Email;
    @Column(name = "haslo")
    private String Password;
    private String Telefon;
    private RodzajUzytkownika Typ_Uzytkownika;
    public Uzytkownicy(String name, String email, String password, String phone)
    {
        this.Nazwa_Uzytkownika = name;
        this.Email = email;
        this.Password = password;
        this.Telefon = phone;
    }

    public Uzytkownicy(String login, String password) {
        Nazwa_Uzytkownika = login;
        Password = password;
    }

    public RodzajUzytkownika getTyp_Uzytkownika() {
        return Typ_Uzytkownika;
    }

    public void setTyp_Uzytkownika(RodzajUzytkownika typ_Uzytkownika) {
        Typ_Uzytkownika = typ_Uzytkownika;
    }

    public String getEmail() {
        return this.Email;
    }
    public String getPassword() {
        return this.Password;
    }

    @Override
    public String toString() {
        return "Uzytkownicy{" +
                "ID_Uzytkownik=" + ID_Uzytkownik +
                ", Nazwa_Uzytkownika='" + Nazwa_Uzytkownika + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Telefon='" + Telefon + '\'' +
                ", Typ_Uzytkownika=" + Typ_Uzytkownika +
                '}';
    }
}
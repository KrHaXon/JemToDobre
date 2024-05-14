package com.JemToDobre.Controller;

import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.repository.UzytkownicyRepository;
import com.JemToDobre.service.UzytkownicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class RegistrationController {
    private final UzytkownicyRepository uzytkownicyRepository;

    public RegistrationController(UzytkownicyRepository uzytkownicyRepository) {
        this.uzytkownicyRepository = uzytkownicyRepository;
    }
    @PostMapping("/register-submit")
    public String register(@RequestParam("nick") String nick,
                           @RequestParam("email") String email,
                           @RequestParam("haslo1") String haslo1,
                           @RequestParam("haslo2") String haslo2,
                           @RequestParam(value = "regulamin", required = false) String regulamin,
                           Model model) {
//TO TRZEBA USUNAC BO NIE DZIAŁA
        boolean wszystko_OK = true;
        // Sprawdzenie długości nicka
        if (nick.length() < 3 || nick.length() > 20) {
            wszystko_OK = false;
            model.addAttribute("e_nick", "Nick MUST HAVE BETWEEN 3 AND 20 LETTERS");
        }
        if (!nick.matches("[a-zA-Z0-9]+")) {
            wszystko_OK = false;
            model.addAttribute("e_nick", "ONLY LETTERS OR NUMBERS, NO POLISH SYMBOLS");
        }
        // Sprawdzenie poprawności adresu email
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            wszystko_OK = false;
            model.addAttribute("e_email", "PLEASE, ENTER CORRECT EMAIL");
        }
        // Sprawdzenie poprawności hasła
        if (haslo1.length() < 8 || haslo1.length() > 20) {
            wszystko_OK = false;
            model.addAttribute("e_haslo", "PASSWORD MUST HAVE 8-20 CHARACTERS");
        }
        if (!haslo1.equals(haslo2)) {
            wszystko_OK = false;
            model.addAttribute("e_haslo", "PASSWORDS ARE NOT THE SAME!");
        }
        // Czy zaakceptowano regulamin?
        if (regulamin == null) {
            wszystko_OK = false;
            model.addAttribute("e_regulamin", "ACCEPT");
        }
        // Zapisanie wprowadzonych danych do sesji
        model.addAttribute("fr_nick", nick);
        model.addAttribute("fr_email", email);
        model.addAttribute("fr_haslo1", haslo1);
        model.addAttribute("fr_haslo2", haslo2);
        model.addAttribute("fr_regulamin", regulamin != null);
        Uzytkownicy us = new Uzytkownicy(nick, email, haslo1);
        uzytkownicyRepository.save(us);
        return "redirect:/";
    }
}


package com.JemToDobre.Controller;

import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.repository.UzytkownicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class RegistrationController {

    private final UzytkownicyRepository uzytkownicyRepository;
    @Autowired
    public RegistrationController(UzytkownicyRepository uzytkownicyRepository) {
        this.uzytkownicyRepository = uzytkownicyRepository;
    }
    @PostMapping("/register-submit")
    public String register(@RequestParam("nick") String nick,
                           @RequestParam("email") String email,
                           @RequestParam("haslo1") String haslo1,
                           @RequestParam("haslo2") String haslo2,
                           @RequestParam("phone") String phone,
                           @RequestParam(value = "regulamin", required = false) String regulamin,
                           Model model) {
        // Zapisanie wprowadzonych danych do sesji
        model.addAttribute("fr_nick", nick);
        model.addAttribute("fr_email", email);
        model.addAttribute("fr_haslo1", haslo1);
        model.addAttribute("fr_haslo2", haslo2);
        model.addAttribute("fr_phone", phone);
        model.addAttribute("fr_regulamin", regulamin != null);
        Uzytkownicy us = new Uzytkownicy(nick, email, haslo1, phone);
        uzytkownicyRepository.save(us);
        return "redirect:/";
    }
}
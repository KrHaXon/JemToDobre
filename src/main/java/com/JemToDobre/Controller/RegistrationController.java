package com.JemToDobre.Controller;

import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.repository.UzytkownicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class RegistrationController {

    private final UzytkownicyRepository uzytkownicyRepository;
    PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationController(UzytkownicyRepository uzytkownicyRepository, PasswordEncoder passwordEncoder) {
        this.uzytkownicyRepository = uzytkownicyRepository;
        this.passwordEncoder = passwordEncoder;
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
        if(nick.length() < 3)
        {
            model.addAttribute("error", "Za krótki login!");
           return "register";
        }
        if (email == null || !email.contains("@")) {
            model.addAttribute("error", "Nieprawidłowy adres email!");
            return "register";
        }
        if(haslo1.length() < 8)
        {
            model.addAttribute("error", "Za krótkie hasło!");
            return "register";
        }
        if(phone.length() != 9)
        {
            model.addAttribute("error", "Telefon musi mieć 9 znaków!");
            return "register";
        }
        if (!haslo1.equals(haslo2)) {
            model.addAttribute("error", "Hasła nie są zgodne!");
            return "register";
        }
        if(regulamin == null)
        {
            model.addAttribute("error", "Trzeba akceptować regulamin!");
            return "register";
        }
        String hashedPassword = passwordEncoder.encode(haslo1);
        Uzytkownicy us = new Uzytkownicy(nick, email, hashedPassword, phone);
        uzytkownicyRepository.save(us);
        return "redirect:/";
    }
}
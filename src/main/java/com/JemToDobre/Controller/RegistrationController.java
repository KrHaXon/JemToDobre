package com.JemToDobre.Controller;

import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.repository.UzytkownicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> register(@RequestParam("nick") String nick,
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
        Map<String, Object> response = new HashMap<>();
        List<String> errors = new ArrayList<>();
        if(nick.length() < 3)
        {
            model.addAttribute("error", "Za krótki login!");
            errors.add("Za krótki login!");
        }
        if (email == null || !email.contains("@")) {
            model.addAttribute("error", "Nieprawidłowy adres email!");
            errors.add("Nieprawidłowy adres email!");
        }
        if(haslo1.length() < 8)
        {
            model.addAttribute("error", "Za krótkie hasło!");
            errors.add("Za krótkie hasło! Musi posiadać przynajmniej 8 znaków!");
        }
        if(phone.length() != 9)
        {
            model.addAttribute("error", "Telefon musi mieć 9 znaków!");
            errors.add("Telefon musi mieć 9 znaków!");
        }
        if (!haslo1.equals(haslo2)) {
            model.addAttribute("error", "Hasła nie są zgodne!");
            errors.add("Hasła nie są zgodne!");
        }
        if(regulamin == null) {
            model.addAttribute("error", "Trzeba akceptować regulamin!");
            errors.add("Trzeba akceptować regulamin!");
        }
        if (!errors.isEmpty()) {
            response.put("status", "error");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }
        String hashedPassword = passwordEncoder.encode(haslo1);
        Uzytkownicy us = new Uzytkownicy(nick, email, hashedPassword, phone);
        uzytkownicyRepository.save(us);
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
}
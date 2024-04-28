package com.JemToDobre.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"fr_nick", "fr_email", "fr_haslo1", "fr_haslo2", "fr_regulamin"})
public class RegistrationController {

    @PostMapping("/register")
    public String register(@RequestParam("nick") String nick,
                           @RequestParam("email") String email,
                           @RequestParam("haslo1") String haslo1,
                           @RequestParam("haslo2") String haslo2,
                           @RequestParam(value = "regulamin", required = false) String regulamin,
                           Model model) {

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

        if (wszystko_OK) {
            try {
                // Tutaj umieść kod związan z połączeniem z bazą danych i zapisaniem użytkownika

                return "redirect:/home";
            } catch (Exception e) {
                model.addAttribute("error", "SERVER ERROR! TRY AGAIN LATER");
                model.addAttribute("info", e.getMessage());
            }
        }
        return "register"; // Zwrócenie widoku rejestracji z ewentualnymi błędami
    }
}

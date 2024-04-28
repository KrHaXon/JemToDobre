package com.JemToDobre.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"zalogowany", "id", "user", "email", "role", "blad"})
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("haslo") String haslo,
                        Model model) {
        try {
            // Tutaj umieść kod związan z połączeniem z bazą danych i weryfikacją użytkownika

            // Jeśli weryfikacja jest udana, ustaw atrybuty sesji
            // W tym przykładzie zakładamy, że weryfikacja jest udana, więc ustawiamy atrybuty sesji
            model.addAttribute("zalogowany", true);
            model.addAttribute("id", 1); // Przykładowe ID użytkownika
            model.addAttribute("user", login); // Przykładowy login użytkownika
            model.addAttribute("email", "example@example.com"); // Przykładowy email użytkownika
            model.addAttribute("role", "USER"); // Przykładowa rola użytkownika
            model.addAttribute("blad", null); // Wyczyść ewentualny atrybut błędu
            return "redirect:/home"; // Przekierowanie do strony głównej po udanym zalogowaniu
        } catch (Exception e) {
            // Obsługa błędów połączenia z bazą danych
            model.addAttribute("blad", "Błąd serwera! Spróbuj ponownie później.");
            return "redirect:/login"; // Przekierowanie do strony logowania w przypadku błędu
        }
    }
}

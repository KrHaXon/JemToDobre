package com.JemToDobre.Controller;


import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.repository.UzytkownicyRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@Controller
public class LoginController {
    @Autowired
    private UzytkownicyRepository uzytkownicyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /*public LoginController(UzytkownicyRepository uzytkownicyRepository, PasswordEncoder passwordEncoder)
    {
        this.uzytkownicyRepository = uzytkownicyRepository;
        this.passwordEncoder = passwordEncoder;
    }*/
    /*@PostMapping("/login-submit")
    public String login(@RequestBody LoginDto loginDto)
    {
        //LoginResponse loginMessage = uzytkownicyService.Login(loginDto);
        //System.out.println(loginMessage);
        System.out.println(loginDto.getEmail());
        System.out.println(loginDto.getPassword());
        return "redirect:/";
    }*/

    @PostMapping("/login-submit")
    public ResponseEntity<Map<String, String>> login(@RequestParam("login") String username,
                                                     @RequestParam("password") String password,
                                                     HttpSession session, Model model)
    {
        Map<String, String> response = new HashMap<>();

        //boolean isLoggedIn = false;
       Uzytkownicy user = uzytkownicyRepository.findUserByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            response.put("status", "error");
            response.put("message", "Nieprawidłowy login lub hasło");
            return ResponseEntity.badRequest().body(response);
        }
        // Zapisanie użytkownika w sesji
        session.setAttribute("loggedInUser", user);

        // Przekierowanie na stronę główną lub inny widok po zalogowaniu
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/logout")
    public String logout(HttpSession session, Model model)
    {
        session.removeAttribute("loggedInUser");
        //session.invalidate();
        //session.removeAttribute("role");
        model.addAttribute("isLoggedIn", false);
        return "redirect:/";
    }
    /*private boolean passwordMatches(String inputPassword, String hashedPassword) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(inputPassword, hashedPassword);
    }*/
    /*@PostMapping("/login")
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
    }*/

    //@PostMapping("/register")
    //public String rejestracja() {
    //return "register";
    // }

}

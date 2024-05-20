package com.JemToDobre.Controller;


import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.repository.UzytkownicyRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class LoginController {
    private final UzytkownicyRepository uzytkownicyRepository;

    public LoginController(UzytkownicyRepository uzytkownicyRepository)
    {
        this.uzytkownicyRepository = uzytkownicyRepository;
    }
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
    public String login(@RequestParam("login") String username,
                        @RequestParam("password") String password,
                        HttpSession session, Model model)
    {
        //boolean isLoggedIn = false;
        Optional<Uzytkownicy> optionalUser = uzytkownicyRepository.findUserByUsername(username);
        if (optionalUser.isPresent())
        {
           // isLoggedIn = true;
            Uzytkownicy uzytkownicy = optionalUser.get();
            //String hashedPassword = uzytkownicy.getPassword();
            //if (passwordMatches(password, hashedPassword)) {
            session.setAttribute("loggedInUser", uzytkownicy);
            //session.setAttribute("role", uzytkownicy.getTyp_Uzytkownika().name());
            System.out.println("Logged in user: " + session.getAttribute("loggedInUser"));
            System.out.println(password);
            model.addAttribute("Uzytkownicy", uzytkownicy);
            model.addAttribute("isLoggedIn", true);
            return "redirect:/";
        }
        else {
            model.addAttribute("error", "Nieprawidłowa nazwa użytkownika lub hasło!");
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("loggedInUser");
        //session.removeAttribute("role");
        return "home";
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

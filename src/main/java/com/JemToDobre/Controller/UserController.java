package com.JemToDobre.Controller;

import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder)
    {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/edit")
    public String showEditUserForm(HttpSession session, Model model) {
        Uzytkownicy loggedInUser = (Uzytkownicy) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
            return "user/user_edit_form"; // Poprawiona ścieżka do szablonu
        } else {
            return "redirect:/login";
        }
    }


    @PostMapping("/edit")
    public ResponseEntity<Map<String, Object>> editUser(@RequestParam("email") String email,
                                                        @RequestParam("password") String password,
                                                        @RequestParam("password2") String password2,
                                                        @RequestParam("telefon") String telefon,
                                                        HttpSession session, Model model) {
        Map<String, Object> response = new HashMap<>();
        List<String> errors = new ArrayList<>();
        Uzytkownicy loggedInUser = (Uzytkownicy) session.getAttribute("loggedInUser");
        if (email == null || !email.contains("@")) {
            model.addAttribute("error", "Nieprawidłowy adres email!");
            errors.add("Nieprawidłowy adres email!");
        }
        if(password.length() < 8)
        {
            model.addAttribute("error", "Za krótkie hasło!");
            errors.add("Za krótkie hasło! Musi posiadać przynajmniej 8 znaków!");
        }
        if(telefon.length() != 9)
        {
            model.addAttribute("error", "Telefon musi mieć 9 znaków!");
            errors.add("Telefon musi mieć 9 znaków!");
        }
        if (!password.equals(password2)) {
            model.addAttribute("error", "Hasła nie są zgodne!");
            errors.add("Hasła nie są zgodne!");
        }
        if (!errors.isEmpty()) {
            response.put("status", "error");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }
        String hashedPassword = passwordEncoder.encode(password);
        if (loggedInUser != null) {
            loggedInUser.setEmail(email);
            loggedInUser.setPassword(hashedPassword);
            loggedInUser.setTelefon(telefon);
            userService.saveUser(loggedInUser);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public String viewUserProfile(HttpSession session, Model model) {
        Uzytkownicy loggedInUser = (Uzytkownicy) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
            return "user/user_profile"; // Poprawiona ścieżka do szablonu
        } else {
            return "redirect:/login";
        }
    }
}

package com.JemToDobre.Controller;

import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
    public String editUser(@RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("telefon") String telefon,
                           HttpSession session) {
        Uzytkownicy loggedInUser = (Uzytkownicy) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            loggedInUser.setEmail(email);
            loggedInUser.setPassword(password);
            loggedInUser.setTelefon(telefon);
            userService.saveUser(loggedInUser);

            return "redirect:/user/profile";
        } else {
            return "redirect:/login";
        }
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

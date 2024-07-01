package com.JemToDobre.Controller;


import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.repository.UzytkownicyRepository;
import com.JemToDobre.service.MyUserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
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
    @Autowired
    private RememberMeServices rememberMeServices;

    @PostMapping("/login-submit")
    public ResponseEntity<Map<String, String>> login(@RequestParam("login") String username,
                                                     @RequestParam("password") String password,
                                                     @RequestParam(value = "remember-me", defaultValue = "false") boolean rememberMe,
                                                     HttpSession session, HttpServletRequest request,
                                                     HttpServletResponse response, Model model)
    {
        Map<String, String> responseMap = new HashMap<>();

        //boolean isLoggedIn = false;
       Uzytkownicy user = uzytkownicyRepository.findUserByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            responseMap.put("status", "error");
            responseMap.put("message", "Nieprawidłowy login lub hasło");
            return ResponseEntity.badRequest().body(responseMap);
        }
        MyUserPrincipal test = new MyUserPrincipal(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (rememberMe) {
            rememberMeServices.loginSuccess(request, response, authentication);
            rememberMeServices.autoLogin(request, response);
            System.out.println("Remember-me cookie set successfully.");
        }
        // Zapisanie użytkownika w sesji
        session.setAttribute("loggedInUser", user);
        // Przekierowanie na stronę główną lub inny widok po zalogowaniu
        responseMap.put("status", "success");
        return ResponseEntity.ok(responseMap);
    }
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model)
    {
        session.removeAttribute("loggedInUser");
        //session.invalidate();
        //session.removeAttribute("role");
        model.addAttribute("isLoggedIn", false);
        SecurityContextHolder.clearContext();
        session.invalidate();
        return "redirect:/";
    }
}

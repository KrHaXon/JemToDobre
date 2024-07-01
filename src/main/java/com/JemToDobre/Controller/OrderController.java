package com.JemToDobre.Controller;

import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.service.PozycjeMenuService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private PozycjeMenuService pozycjeMenuService;
    @GetMapping("/orders")
    public String showOrders(Model model, HttpSession session) {
        List<Pozycje_Menu> cart = (List<Pozycje_Menu>) session.getAttribute("cart");
        if (cart == null) {
            return "redirect:/";
        }
        double totalPrice = cart.stream().mapToDouble(Pozycje_Menu::getCena).sum();
        totalPrice = Double.parseDouble(String.format("%.2f", totalPrice).replace(",", "."));
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", totalPrice);
        session.removeAttribute("cart"); // Usunięcie koszyka z sesji po złożeniu zamówienia
        return "orders";
    }
    @PostMapping("/orders")
    public String accept(Model model, HttpSession session)
    {
        return "redirect:/";
    }
}

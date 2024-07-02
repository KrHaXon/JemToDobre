package com.JemToDobre.Controller;

import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.model.Pozycje_Zamowienia;
import com.JemToDobre.repository.PozycjeZamowieniaRepository;
import com.JemToDobre.service.PozycjeMenuService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.JemToDobre.model.Kategoria_Menu;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CartController {

    @Autowired
    private PozycjeMenuService pozycjeMenuService;
    @Autowired
    private PozycjeZamowieniaRepository pozycjeZamowieniaRepository;
    List<Pozycje_Menu> cart = new ArrayList<>();
    @GetMapping("/menu")
    public String menu(Model model) {
        List<Pozycje_Menu> allItems = pozycjeMenuService.findAll();

        List<Kategoria_Menu> kategorie = allItems.stream()
                .map(Pozycje_Menu::getKategoria)
                .distinct()
                .sorted(Comparator.comparing(Kategoria_Menu::getNazwa_Kategorii).reversed())
                .collect(Collectors.toList());

        Map<Kategoria_Menu, List<Pozycje_Menu>> pozycjeMenuMap = new LinkedHashMap<>();

        for (Kategoria_Menu kategoria : kategorie) {
            List<Pozycje_Menu> pozycjeMenu = allItems.stream()
                    .filter(item -> item.getKategoria().equals(kategoria))
                    .collect(Collectors.toList());
            pozycjeMenuMap.put(kategoria, pozycjeMenu);
        }

        model.addAttribute("pozycjeMenuMap", pozycjeMenuMap);
        return "menu2";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        cart = (List<Pozycje_Menu>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        double totalPrice = cart.stream().mapToDouble(Pozycje_Menu::getCena).sum();
        totalPrice = Double.parseDouble(String.format("%.2f", totalPrice).replace(",", "."));
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Integer id, HttpSession session) {
        Pozycje_Menu item = pozycjeMenuService.findById(id);
        cart = (List<Pozycje_Menu>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(item);
        Pozycje_Zamowienia pozycjaZamowienia = new Pozycje_Zamowienia();
        pozycjaZamowienia.setID_Pozycji(item.getID_Pozycja_Menu());
        pozycjaZamowienia.setIlosc(cart.size());
        pozycjaZamowienia.setCena(item.getCena());
        pozycjeZamowieniaRepository.save(pozycjaZamowienia);
        session.setAttribute("cart", cart);
        return "redirect:/menu2";
    }

    @PostMapping("/cart/remove/{index}")
    public String removeFromCart(@PathVariable int index, HttpSession session) {
        List<Pozycje_Menu> cart = (List<Pozycje_Menu>) session.getAttribute("cart");
        if (cart != null && index >= 0 && index < cart.size()) {
            cart.remove(index);
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

}
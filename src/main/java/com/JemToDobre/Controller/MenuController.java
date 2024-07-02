package com.JemToDobre.Controller;

import com.JemToDobre.model.Kategoria_Menu;
import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.model.Pozycje_Zamowienia;
import com.JemToDobre.model.Zamowienia;
import com.JemToDobre.repository.PozycjeZamowieniaRepository;
import com.JemToDobre.service.KategoriaMenuService;
import com.JemToDobre.service.MenuItemService;
import com.JemToDobre.service.PozycjeMenuService;
import com.JemToDobre.service.ZamowieniaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/menu2")
@SessionAttributes("zamowienie")
public class MenuController {
    private final MenuItemService menuItemService;
    private final ZamowieniaService zamowieniaService;
    @Autowired
    private PozycjeZamowieniaRepository pozycjeZamowieniaRepository;

    List<Pozycje_Menu> lista = new ArrayList<>();
    @Autowired
    PozycjeMenuService pozycjeMenuService;

    @Autowired
    KategoriaMenuService kategoriaMenuService;

    public MenuController(MenuItemService menuItemService, ZamowieniaService zamowieniaService) {
        this.menuItemService = menuItemService;
        this.zamowieniaService = zamowieniaService;
    }

    @ModelAttribute("zamowienie")
    public Zamowienia zamowienie() {
        return zamowieniaService.createOrder();
    }

    @GetMapping
    public String showMenu(Model model, HttpSession session) {
        //model.addAttribute("menuItems", menuItemService.findAll());
        Boolean isLoggedIn = session.getAttribute("loggedInUser") != null;
        model.addAttribute("isLoggedIn", isLoggedIn);

        List<Kategoria_Menu> kategorie = kategoriaMenuService.findAll();

        kategorie.sort(Comparator.comparing(Kategoria_Menu::getNazwa_Kategorii).reversed());

        model.addAttribute("kategorie", kategorie);

        Map<Kategoria_Menu, List<Pozycje_Menu>> pozycjeMenuMap = new LinkedHashMap<>();

        for (Kategoria_Menu kategoria : kategorie) {
            List<Pozycje_Menu> pozycjeMenu = pozycjeMenuService.findByKategoria(kategoria);
            pozycjeMenuMap.put(kategoria, pozycjeMenu);
        }

        model.addAttribute("pozycjeMenuMap", pozycjeMenuMap);
        //model.addAttribute("pozycje", pozycjeMenuService.findAll());
        //model.addAttribute("kategorie", kategoriaMenuService.findAll());
        return "menu2";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long menuItemId, @RequestParam int quantity, @ModelAttribute Zamowienia zamowienie, HttpSession session) {
        Pozycje_Menu menuItem = menuItemService.findById(menuItemId);
        lista.add(menuItem);
        if (menuItem != null) {
            Pozycje_Zamowienia pozycjaZamowienia = new Pozycje_Zamowienia();
            pozycjaZamowienia.setID_Pozycji(menuItem.getID_Pozycja_Menu().intValue());
            pozycjaZamowienia.setIlosc(quantity);
            pozycjaZamowienia.setCena(menuItem.getCena());
            pozycjeZamowieniaRepository.save(pozycjaZamowienia);
            zamowieniaService.addItemToOrder(zamowienie, pozycjaZamowienia);
        }
        return "redirect:/menu2";
    }

    @GetMapping("/cart")
    public String showCart(@ModelAttribute Zamowienia zamowienie, Model model, HttpSession session) {
        session.setAttribute("zamowienie", zamowienie);
        return "cart";
    }
}

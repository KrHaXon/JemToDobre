package com.JemToDobre.Controller;

import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.model.Pozycje_Zamowienia;
import com.JemToDobre.model.Zamowienia;
import com.JemToDobre.service.MenuItemService;
import com.JemToDobre.service.ZamowieniaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menu")
@SessionAttributes("zamowienie")
public class MenuController {
    private final MenuItemService menuItemService;
    private final ZamowieniaService zamowieniaService;

    public MenuController(MenuItemService menuItemService, ZamowieniaService zamowieniaService) {
        this.menuItemService = menuItemService;
        this.zamowieniaService = zamowieniaService;
    }

    @ModelAttribute("zamowienie")
    public Zamowienia zamowienie() {
        return zamowieniaService.createOrder();
    }

    @GetMapping
    public String showMenu(Model model) {
        model.addAttribute("menuItems", menuItemService.findAll());
        return "menu";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long menuItemId, @RequestParam int quantity, @ModelAttribute Zamowienia zamowienie) {
        Pozycje_Menu menuItem = menuItemService.findById(menuItemId);
        if (menuItem != null) {
            Pozycje_Zamowienia pozycjaZamowienia = new Pozycje_Zamowienia();
            pozycjaZamowienia.setID_Pozycji(menuItem.getID_Pozycja_Menu().intValue());
            pozycjaZamowienia.setIlosc(quantity);
            pozycjaZamowienia.setCena(menuItem.getCena());
            zamowieniaService.addItemToOrder(zamowienie, pozycjaZamowienia);
        }
        return "redirect:/menu";
    }

    @GetMapping("/cart")
    public String showCart(@ModelAttribute Zamowienia zamowienie, Model model) {
        model.addAttribute("zamowienie", zamowienie);
        return "cart";
    }
}

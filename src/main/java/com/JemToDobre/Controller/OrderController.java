package com.JemToDobre.Controller;

import com.JemToDobre.model.*;
import com.JemToDobre.repository.AdresRepository;
import com.JemToDobre.repository.PozycjeZamowieniaRepository;
import com.JemToDobre.repository.UzytkownicyRepository;
import com.JemToDobre.repository.ZamowieniaRepository;
import com.JemToDobre.service.PozycjeMenuService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class OrderController {
    @Autowired
    private AdresRepository adresRepository;
    @Autowired
    private ZamowieniaRepository zamowieniaRepository;

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
        session.setAttribute("totalPrice", totalPrice);
        //session.removeAttribute("cart"); // Usunięcie koszyka z sesji po złożeniu zamówienia
        return "orders";
    }
    @PostMapping("/orders-submit")
    public String accept(Model model, HttpSession session,
                         @RequestParam("city") String city,
                         @RequestParam("street") String street,
                         @RequestParam("house-number") Integer houseNumber,
                         @RequestParam("apartment-number") String apartmentNumberStr,
                         @RequestParam("postal-code") String postalCode,
                         @RequestParam("orderComments") String orderComments) {
        model.addAttribute("fr_city", city);
        model.addAttribute("fr_street", street);
        model.addAttribute("fr_house-number", houseNumber);

        Integer actualApartmentNumber = null;
        if (apartmentNumberStr != null && !apartmentNumberStr.isEmpty()) {
            try {
                actualApartmentNumber = Integer.parseInt(apartmentNumberStr);
            } catch (NumberFormatException e) {

            }
        }
        model.addAttribute("fr_apartment-number", actualApartmentNumber);
        model.addAttribute("fr_postal-code", postalCode);

        Adres adres = new Adres(street, houseNumber, actualApartmentNumber, city, postalCode);
        adresRepository.save(adres);

        Uzytkownicy user = (Uzytkownicy) session.getAttribute("loggedInUser");
        Integer temp;
        if(user == null)
        {
            temp = null;
        }
        else
        {
            temp = user.getID_Uzytkownik();
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        String status = new String("W trakcie");
        String dodatkoweinformacje = orderComments;
        String numerFaktury = String.format("%06d", new Random().nextInt(1000000));
        LocalDateTime currentplustree = LocalDateTime.now();

        List<Pozycje_Menu> cart = (List<Pozycje_Menu>) session.getAttribute("cart");

        System.out.println(currentDateTime);
        if (cart == null) {
            return "error";
        }
        for(int i = 0; i<cart.size();i++)
        {
            System.out.println(cart.get(i).getNazwa_Pozycji());
        }
        double Price =  (double)session.getAttribute("totalPrice");
        Zamowienia zamowienie = (Zamowienia)session.getAttribute("zamowienie");
        //List<Pozycje_Zamowienia> pozycjeZamowienia = (List<Pozycje_Zamowienia>)session.getAttribute("PozycjeZamowienia");
        //Zamowienia zamowienie = new Zamowienia(temp, adres.getID_Adres(), currentDateTime, status, dodatkoweinformacje, numerFaktury, currentplustree, cart, Price);
        zamowienie.setID_Uzytkownik(temp);
        zamowienie.setID_Adres(adres.getID_Adres());
        zamowienie.setData_Zamowienia(currentDateTime);
        zamowienie.setStatus_Zamowienia(status);
        zamowienie.setDodatkowe_Informacje(dodatkoweinformacje);
        zamowienie.setNr_Faktury(numerFaktury);
        zamowienie.setData_Realizacji(currentplustree);
        zamowienie.setLacznaCena(Price);
        zamowieniaRepository.save(zamowienie);
        return "redirect:/";
    }
}

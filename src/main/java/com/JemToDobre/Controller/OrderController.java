package com.JemToDobre.Controller;

import com.JemToDobre.model.*;
import com.JemToDobre.repository.AdresRepository;
import com.JemToDobre.repository.PozycjeZamowieniaRepository;
import com.JemToDobre.repository.UzytkownicyRepository;
import com.JemToDobre.repository.ZamowieniaRepository;
import com.JemToDobre.resources.EmailMessage;
import com.JemToDobre.service.EmailSenderService;
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
    @Autowired
    private PozycjeZamowieniaRepository pozycjeZamowieniaRepository;
    @Autowired
    private EmailSenderService emailSenderService;

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
        return "orders";
    }

    @PostMapping("/orders-submit")
    public String accept(Model model, HttpSession session,
                         @RequestParam("city") String city,
                         @RequestParam("street") String street,
                         @RequestParam("house-number") Integer houseNumber,
                         @RequestParam("apartment-number") String apartmentNumberStr,
                         @RequestParam("postal-code") String postalCode,
                         @RequestParam("orderComments") String orderComments,
                         @RequestParam("orderOptions") String orderOptions,
                         @RequestParam("paymentMethod") String paymentMethod){
        model.addAttribute("fr_city", city);
        model.addAttribute("fr_street", street);
        model.addAttribute("fr_house-number", houseNumber);

        Integer actualApartmentNumber = null;
        if (apartmentNumberStr != null && !apartmentNumberStr.isEmpty()) {
            try {
                actualApartmentNumber = Integer.parseInt(apartmentNumberStr);
            } catch (NumberFormatException e) {
                // Obsłuż błąd konwersji numeru mieszkania
            }
        }
        model.addAttribute("fr_apartment-number", actualApartmentNumber);
        model.addAttribute("fr_postal-code", postalCode);

        Adres adres = new Adres(street, houseNumber, actualApartmentNumber, city, postalCode);
        adresRepository.save(adres);

        Uzytkownicy user = (Uzytkownicy) session.getAttribute("loggedInUser");
        Integer temp = (user == null) ? null : user.getID_Uzytkownik();

        LocalDateTime currentDateTime = LocalDateTime.now();
        String status = "W trakcie";
        String dodatkoweInformacje = orderComments;
        String numerFaktury = String.format("%06d", new Random().nextInt(1000000));
        LocalDateTime currentplustree = LocalDateTime.now();

        List<Pozycje_Menu> cart = (List<Pozycje_Menu>) session.getAttribute("cart");
        if (cart == null) {
            return "error";
        }

        double totalPrice = (double) session.getAttribute("totalPrice");

        Zamowienia zamowienie = new Zamowienia(temp, adres.getID_Adres(), currentDateTime, status, dodatkoweInformacje, numerFaktury, currentplustree, new ArrayList<>(), totalPrice, orderOptions, paymentMethod);
        zamowieniaRepository.save(zamowienie);

        for (Pozycje_Menu pozycjaMenu : cart) {
            Pozycje_Zamowienia pozycjaZamowienia = new Pozycje_Zamowienia(zamowienie, pozycjaMenu.getID_Pozycja_Menu(), pozycjaMenu.getCena());
            pozycjeZamowieniaRepository.save(pozycjaZamowienia);
            zamowienie.addPozycjaZamowienia(pozycjaZamowienia);
        }

        zamowieniaRepository.save(zamowienie);
        session.removeAttribute("cart"); // Usunięcie koszyka z sesji po złożeniu zamówienia

        EmailMessage emailMessage = new EmailMessage("haftor2003@gmail.com", "Zamówienie", "Dziękujemy za zamówienie, zapraszamy ponownie!");
        emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());

        return "redirect:/";
    }
}

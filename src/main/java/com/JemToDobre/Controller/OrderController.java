package com.JemToDobre.Controller;

import com.JemToDobre.model.*;
import com.JemToDobre.repository.AdresRepository;
import com.JemToDobre.repository.PozycjeZamowieniaRepository;
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
    @Autowired
    PozycjeMenuService pozycjeMenuService;

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

    @PostMapping("/orders-restaurant")
    public String submitRestaurantOrder(@RequestParam("orderComments") String orderComments,
                                        @RequestParam("orderOptions") String orderOptions,
                                        @RequestParam("paymentMethod") String paymentMethod,
                                        HttpSession session) {
        System.out.println("HEEEEEJ");
        return processOrder(null, null, null, null, null, orderComments, orderOptions, paymentMethod, session);
    }

    @PostMapping("/orders-takeaway")
    public String submitTakeawayOrder(@RequestParam("orderComments") String orderComments,
                                      @RequestParam("orderOptions") String orderOptions,
                                      @RequestParam("paymentMethod") String paymentMethod,
                                      HttpSession session) {
        return processOrder(null, null, null, null, null, orderComments, orderOptions, paymentMethod, session);
    }

    @PostMapping("/orders-delivery")
    public String submitDeliveryOrder(@RequestParam("city") String city,
                                      @RequestParam("street") String street,
                                      @RequestParam("house-number") Integer houseNumber,
                                      @RequestParam(value = "apartment-number", required = false) String apartmentNumberStr,
                                      @RequestParam("postal-code") String postalCode,
                                      @RequestParam("orderComments") String orderComments,
                                      @RequestParam("orderOptions") String orderOptions,
                                      @RequestParam("paymentMethod") String paymentMethod,
                                      HttpSession session) {
        return processOrder(city, street, houseNumber, apartmentNumberStr, postalCode, orderComments, orderOptions, paymentMethod, session);
    }

    private String processOrder(String city, String street, Integer houseNumber, String apartmentNumberStr, String postalCode,
                                String orderComments, String orderOptions, String paymentMethod, HttpSession session) {
        Integer actualApartmentNumber = null;
        if (apartmentNumberStr != null && !apartmentNumberStr.isEmpty()) {
            try {
                actualApartmentNumber = Integer.parseInt(apartmentNumberStr);
            } catch (NumberFormatException e) {
                // Obsłuż błąd konwersji numeru mieszkania
            }
        }

        Adres adres = null;
        if (orderOptions.equals("Na Dowóz")) {
            adres = new Adres(street, houseNumber, actualApartmentNumber, city, postalCode);
            adresRepository.save(adres);
        }

        Uzytkownicy user = (Uzytkownicy) session.getAttribute("loggedInUser");
        Integer userId = (user == null) ? null : user.getID_Uzytkownik();

        LocalDateTime currentDateTime = LocalDateTime.now();
        String status = "W trakcie";
        String numerFaktury = String.format("%06d", new Random().nextInt(1000000));
        LocalDateTime deliveryTime = LocalDateTime.now();

        List<Pozycje_Menu> cart = (List<Pozycje_Menu>) session.getAttribute("cart");
        if (cart == null) {
            return "error";
        }

        double basePrice = (double) session.getAttribute("totalPrice");
        double additionalFee = 0;
        if (orderOptions.equals("Na Dowóz")) {
            additionalFee = 20;
        } else if (orderOptions.equals("Na Wynos")) {
            additionalFee = 5;
        }
        double totalPrice = basePrice + additionalFee;

        Zamowienia zamowienie = new Zamowienia(userId, (adres != null) ? adres.getID_Adres() : null, currentDateTime, status, orderComments, numerFaktury, deliveryTime, new ArrayList<>(), totalPrice, orderOptions, paymentMethod);
        zamowieniaRepository.save(zamowienie);

        for (Pozycje_Menu pozycjaMenu : cart) {
            Pozycje_Zamowienia pozycjaZamowienia = new Pozycje_Zamowienia(zamowienie, pozycjaMenu.getID_Pozycja_Menu(), pozycjaMenu.getCena());
            pozycjeZamowieniaRepository.save(pozycjaZamowienia);
            zamowienie.addPozycjaZamowienia(pozycjaZamowienia);
        }

        zamowieniaRepository.save(zamowienie);
        session.removeAttribute("cart");

        String emailContent = generateEmailContent(cart, totalPrice, additionalFee);

        EmailMessage emailMessage = new EmailMessage(user.getEmail(), "Potwierdzenie Zamówienia", emailContent);
        emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());

        return "redirect:/";
    }

    private String generateEmailContent(List<Pozycje_Menu> cart, double totalPrice, double additionalFee) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dziękujemy za złożenie zamówienia!\n\n");
        emailContent.append("Twoje zamówienie:\n");

        for (Pozycje_Menu item : cart) {
            emailContent.append("- ").append(item.getNazwa_Pozycji()).append(": ").append(item.getCena()).append(" PLN\n");
        }

        emailContent.append("\nOpłata dodatkowa: ").append(additionalFee).append(" PLN\n");
        emailContent.append("Łączna cena: ").append(totalPrice).append(" PLN\n\n");
        emailContent.append("Dziękujemy za zamówienie, zapraszamy ponownie!");

        return emailContent.toString();
    }

}

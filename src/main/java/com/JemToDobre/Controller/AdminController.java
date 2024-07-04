package com.JemToDobre.Controller;

import com.JemToDobre.Util.ImageUtils;
import com.JemToDobre.model.Alergeny;
import com.JemToDobre.model.Kategoria_Menu;
import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.model.Pozycje_Menu_Alergeny;
import com.JemToDobre.repository.AlergenRepository;
import com.JemToDobre.repository.KategoriaMenuRepository;
import com.JemToDobre.repository.PozycjeMenuRepository;
import com.JemToDobre.service.AlergenService;
import com.JemToDobre.service.KategoriaMenuService;
import com.JemToDobre.service.PozycjeMenuAlergenyService;
import com.JemToDobre.service.PozycjeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AlergenService alergenService;

    @Autowired
    private KategoriaMenuService kategoriaMenuService;

    @Autowired
    private PozycjeMenuService pozycjeMenuService;

    @Autowired
    private PozycjeMenuAlergenyService pozycjeMenuAlergenyService;

    private final AlergenRepository alergenRepository;
    private final PozycjeMenuRepository pozycjeMenuRepository;
    private final KategoriaMenuRepository kategoriaMenuRepository;

    @Autowired
    public AdminController(AlergenRepository alergenRepository, PozycjeMenuRepository pozycjeMenuRepository, KategoriaMenuRepository kategoriaMenuRepository) {
        this.alergenRepository = alergenRepository;
        this.pozycjeMenuRepository = pozycjeMenuRepository;
        this.kategoriaMenuRepository = kategoriaMenuRepository;
    }

    // Alergeny
    @GetMapping("/alergeny")
    public String listAlergeny(Model model) {
        model.addAttribute("alergeny", alergenService.findAll());
        return "admin/alergen_list";
    }

    @GetMapping("/alergen/new")
    public String newAlergen(Model model) {
        model.addAttribute("alergen", new Alergeny());
        return "admin/alergen_form";
    }

    @PostMapping("/alergen")
    public String saveAlergen(@RequestParam("nazwa") String nazwa,
                              @RequestParam("opis") String opis,
                              @RequestParam("typ") String typ,
                              @RequestParam("zrodlo") String zrodlo,
                              Model model) {
        model.addAttribute("Nazwa_Alergenu", nazwa);
        model.addAttribute("Opis_Alergenu", opis);
        model.addAttribute("Typ_Alergenu", typ);
        model.addAttribute("Zrodlo_Alergenu", zrodlo);
        Alergeny alergeny = new Alergeny(nazwa, opis, typ, zrodlo);
        alergenRepository.save(alergeny);
        //alergenService.save(alergen);
        return "redirect:/admin/alergeny";
    }

    @GetMapping("/alergen/edit/{id}")
    public String editAlergen(@PathVariable Integer id, Model model) {
        Optional<Alergeny> alergen = alergenService.findById(id);
        if (alergen.isPresent()) {
            model.addAttribute("alergen", alergen.get());
            return "admin/alergen_edit_form"; // Szablon Thymeleaf dla formularza edycji alergenu
        } else {
            return "redirect:/admin/alergeny"; // Przekierowanie, jeśli alergen o danym ID nie istnieje
        }
    }

    // Metoda do obsługi edycji alergenu po przesłaniu formularza
    @PostMapping("/alergen/edit")
    public String processEditAlergen(@ModelAttribute Alergeny alergen) {
        alergenService.save(alergen);
        return "redirect:/admin/alergeny"; // Przekierowanie na listę alergenów po zapisie
    }

    @GetMapping("/alergen/delete/{id}")
    public String deleteAlergen(@PathVariable Integer id) {
        alergenService.deleteById(id);
        return "redirect:/admin/alergeny";
    }

    // Kategorie
    @GetMapping("/kategorie")
    public String listKategorie(Model model) {
        model.addAttribute("kategorie", kategoriaMenuService.findAll());
        return "admin/kategoria_list";
    }

    @GetMapping("/kategoria/new")
    public String newKategoria(Model model) {
        model.addAttribute("kategoria", new Kategoria_Menu());
        return "admin/kategoria_form";
    }

    @PostMapping("/kategoria")
    public String saveKategoria(@RequestParam("nazwa") String nazwa, Model model) {
        Kategoria_Menu kategoria = new Kategoria_Menu();
        kategoria.setNazwa_Kategorii(nazwa);
        kategoriaMenuService.save(kategoria);
        return "redirect:/admin/kategorie";
    }

    @GetMapping("/kategoria/edit/{id}")
    public String editKategoria(@PathVariable Integer id, Model model) {
        Optional<Kategoria_Menu> kategoria = Optional.ofNullable(kategoriaMenuService.findById(id));
        if (kategoria.isPresent()) {
            model.addAttribute("kategoria", kategoria.get());
            return "admin/kategoria_edit_form"; // Szablon Thymeleaf dla formularza edycji kategorii
        } else {
            return "redirect:/admin/kategorie"; // Przekierowanie, jeśli kategoria o danym ID nie istnieje
        }
    }

    // Metoda do obsługi edycji kategorii po przesłaniu formularza
    @PostMapping("/kategoria/edit")
    public String processEditKategoria(@ModelAttribute Kategoria_Menu kategoria) {
        kategoriaMenuService.save(kategoria);
        return "redirect:/admin/kategorie"; // Przekierowanie na listę kategorii po zapisie
    }

    @GetMapping("/kategoria/delete/{id}")
    public String deleteKategoria(@PathVariable Integer id) {
        kategoriaMenuService.deleteById(id);
        return "redirect:/admin/kategorie";
    }

    // Pozycje Menu
    @GetMapping("/pozycje")
    public String getAllPozycje(Model model) {
        List<Pozycje_Menu> pozycje = pozycjeMenuService.findAll();

        // Dodaj alergeny do każdej pozycji menu
        for (Pozycje_Menu pozycja : pozycje) {
            List<Pozycje_Menu_Alergeny> alergeny = pozycjeMenuAlergenyService.findByPozycjaMenu(pozycja);
            pozycja.setAlergeny(alergeny.stream().map(Pozycje_Menu_Alergeny::getAlergen).collect(Collectors.toList()));
        }

        model.addAttribute("pozycje", pozycje);
        return "admin/pozycje_list";
    }

    @GetMapping("/pozycja/new")
    public String newPozycja(Model model) {
        model.addAttribute("pozycja", new Pozycje_Menu());
        model.addAttribute("kategorie", kategoriaMenuService.findAll());
        model.addAttribute("alergeny", alergenService.findAll());
        return "admin/pozycja_form";
    }

    @PostMapping("/pozycja")
    public String savePozycja(@RequestParam("nazwa") String nazwa,
                              @RequestParam("opis") String opis,
                              @RequestParam("cena") Double cena,
                              @RequestParam("skladniki") String skladniki,
                              @RequestParam("kategoria") Integer kategoriaId,
                              @RequestParam("alergeny") List<Integer> alergenyIds,
                              @RequestParam("zdjecie") MultipartFile zdjecie) throws IOException {

        Kategoria_Menu kategoria = kategoriaMenuService.findById(kategoriaId);
        List<Alergeny> alergeny = alergenService.findAllById(alergenyIds);

        Pozycje_Menu pozycja = new Pozycje_Menu(nazwa, opis, cena, skladniki, kategoria, Base64.getEncoder().encodeToString(zdjecie.getBytes()));
        pozycjeMenuService.save(pozycja);

        for (Alergeny alergen : alergeny) {
            Pozycje_Menu_Alergeny pozycjeMenuAlergeny = new Pozycje_Menu_Alergeny();
            pozycjeMenuAlergeny.setPozycjaMenu(pozycja);
            pozycjeMenuAlergeny.setAlergen(alergen);
            pozycjeMenuAlergenyService.save(pozycjeMenuAlergeny);
        }

        return "redirect:/admin/pozycje";
    }

    @GetMapping("/pozycja/edit/{id}")
    public String editPozycja(@PathVariable Integer id, Model model) {
        Pozycje_Menu pozycja = pozycjeMenuService.findById(id);
        if (pozycja != null) {
            model.addAttribute("pozycja", pozycja);
            model.addAttribute("kategorie", kategoriaMenuService.findAll());

            // Pobierz listę wszystkich alergenów
            List<Alergeny> wszystkieAlergeny = alergenService.findAll();

            // Pobierz listę alergenów przypisanych do pozycji menu
            List<Alergeny> alergenyPozycji = pozycjeMenuAlergenyService.findAlergenyByPozycjaMenu(pozycja);

            // Przekazuj obiekt z listą wszystkich alergenów i listą alergenów przypisanych do pozycji do widoku
            model.addAttribute("wszystkieAlergeny", wszystkieAlergeny);
            model.addAttribute("alergenyPozycji", alergenyPozycji);

            return "admin/pozycja_edit_form";
        } else {
            return "redirect:/admin/pozycje";
        }
    }

    @PostMapping("/pozycja/edit")
    public String processEditPozycja(@ModelAttribute("pozycja") Pozycje_Menu pozycja,
                                     @RequestParam("kategoria") Integer kategoriaId,
                                     @RequestParam("alergeny") List<Integer> alergenyIds,
                                     @RequestParam("zdjecie") MultipartFile zdjecie) throws IOException {

        Kategoria_Menu kategoria = kategoriaMenuService.findById(kategoriaId);
        pozycja.setKategoria(kategoria);

        // Zapisz zdjęcie, jeśli zostało przesłane
        if (!zdjecie.isEmpty()) {
            pozycja.setImageData(Base64.getEncoder().encodeToString(zdjecie.getBytes()));
        }

        // Przetwórz alergeny
        List<Alergeny> alergeny = alergenService.findAllById(alergenyIds);
        pozycja.setAlergeny(alergeny);

        pozycjeMenuService.save(pozycja);

        return "redirect:/admin/pozycje";
    }

    @GetMapping("/pozycja/delete/{id}")
    public String deletePozycja(@PathVariable Integer id) {
        pozycjeMenuService.deleteById(id);
        return "redirect:/admin/pozycje";
    }
}
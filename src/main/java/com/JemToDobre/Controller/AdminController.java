package com.JemToDobre.Controller;

import com.JemToDobre.model.Alergeny;
import com.JemToDobre.model.Kategoria_Menu;
import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.repository.AlergenRepository;
import com.JemToDobre.repository.KategoriaMenuRepository;
import com.JemToDobre.repository.PozycjeMenuRepository;
import com.JemToDobre.service.AlergenService;
import com.JemToDobre.service.KategoriaMenuService;
import com.JemToDobre.service.PozycjeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AlergenService alergenService;

    @Autowired
    private KategoriaMenuService kategoriaMenuService;

    @Autowired
    private PozycjeMenuService pozycjeMenuService;

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
            return "admin/alergen_form";
        } else {
            return "redirect:/admin/alergeny";
        }
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
            return "admin/kategoria_form";
        } else {
            return "redirect:/admin/kategorie";
        }
    }

    @GetMapping("/kategoria/delete/{id}")
    public String deleteKategoria(@PathVariable Integer id) {
        kategoriaMenuService.deleteById(id);
        return "redirect:/admin/kategorie";
    }

    // Pozycje Menu
    @GetMapping("/pozycje")
    public String listPozycje(Model model) {
        model.addAttribute("pozycje", pozycjeMenuService.findAll());
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
                              @RequestParam("alergen") Integer alergenId,
                              Model model) {

        Kategoria_Menu kategoria = kategoriaMenuService.findById(kategoriaId);
        Optional<Alergeny> optionalAlergen = alergenService.findById(alergenId);
        Alergeny alergen = optionalAlergen.orElse(null);

        model.addAttribute("Nazwa_Pozycji", nazwa);
        model.addAttribute("Opis", opis);
        model.addAttribute("Cena", cena);
        model.addAttribute("Skladniki", skladniki);
        model.addAttribute("Kategoria", kategoria);
        model.addAttribute("Alergen", alergen);

        Pozycje_Menu pozycja = new Pozycje_Menu(nazwa, opis, cena, skladniki, kategoria, alergen);
        pozycjeMenuRepository.save(pozycja);

        //System.out.println(pozycja);
        return "redirect:/admin/pozycje";

    }

    @GetMapping("/pozycja/edit/{id}")
    public String editPozycja(@PathVariable Integer id, Model model) {
        Optional<Pozycje_Menu> pozycja = Optional.ofNullable(pozycjeMenuService.findById(id));
        if (pozycja.isPresent()) {
            model.addAttribute("pozycja", pozycja.get());
            model.addAttribute("kategorie", kategoriaMenuService.findAll());
            model.addAttribute("alergeny", alergenService.findAll());
            return "admin/pozycja_form";
        } else {
            return "redirect:/admin/pozycje";
        }
    }

    @GetMapping("/pozycja/delete/{id}")
    public String deletePozycja(@PathVariable Integer id) {
        pozycjeMenuService.deleteById(id);
        return "redirect:/admin/pozycje";
    }
}

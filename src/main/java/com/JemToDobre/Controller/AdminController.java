package com.JemToDobre.Controller;

import com.JemToDobre.model.Alergeny;
import com.JemToDobre.model.Kategoria_Menu;
import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.repository.AlergenRepository;
import com.JemToDobre.service.AlergenService;
import com.JemToDobre.service.KategoriaMenuService;
import com.JemToDobre.service.PozycjeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AlergenRepository alergenRepository;
    public AdminController(AlergenRepository alergenRepository) {this.alergenRepository = alergenRepository;}

    @Autowired
    private AlergenService alergenService;

    @Autowired
    private KategoriaMenuService kategoriaMenuService;

    @Autowired
    private PozycjeMenuService pozycjeMenuService;

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
    public String saveAlergen(@ModelAttribute Alergeny alergen) {
        alergenService.save(alergen);
        return "redirect:/admin/alergeny";
    }

    @GetMapping("/alergen/edit/{id}")
    public String editAlergen(@PathVariable Long id, Model model) {
        model.addAttribute("alergen", alergenService.findById(id));
        return "admin/alergen_form";
    }

    @GetMapping("/alergen/delete/{id}")
    public String deleteAlergen(@PathVariable Long id) {
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
    public String saveKategoria(@ModelAttribute Kategoria_Menu kategoria) {
        kategoriaMenuService.save(kategoria);
        return "redirect:/admin/kategorie";
    }

    @GetMapping("/kategoria/edit/{id}")
    public String editKategoria(@PathVariable Long id, Model model) {
        model.addAttribute("kategoria", kategoriaMenuService.findById(id));
        return "admin/kategoria_form";
    }

    @GetMapping("/kategoria/delete/{id}")
    public String deleteKategoria(@PathVariable Long id) {
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
    public String savePozycja(@ModelAttribute Pozycje_Menu pozycja) {
        pozycjeMenuService.save(pozycja);
        return "redirect:/admin/pozycje";
    }

    @GetMapping("/pozycja/edit/{id}")
    public String editPozycja(@PathVariable Long id, Model model) {
        model.addAttribute("pozycja", pozycjeMenuService.findById(id));
        model.addAttribute("kategorie", kategoriaMenuService.findAll());
        model.addAttribute("alergeny", alergenService.findAll());
        return "admin/pozycja_form";
    }

    @GetMapping("/pozycja/delete/{id}")
    public String deletePozycja(@PathVariable Long id) {
        pozycjeMenuService.deleteById(id);
        return "redirect:/admin/pozycje";
    }
}

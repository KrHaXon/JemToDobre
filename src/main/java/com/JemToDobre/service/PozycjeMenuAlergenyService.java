package com.JemToDobre.service;

import com.JemToDobre.model.Alergeny;
import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.model.Pozycje_Menu_Alergeny;
import com.JemToDobre.repository.PozycjeMenuAlergenyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PozycjeMenuAlergenyService {
    @Autowired
    private PozycjeMenuAlergenyRepository pozycjeMenuAlergenyRepository;

    public void save(Pozycje_Menu_Alergeny pozycjeMenuAlergeny) {
        pozycjeMenuAlergenyRepository.save(pozycjeMenuAlergeny);
    }

    public void deleteByPozycjaMenu(Pozycje_Menu pozycjaMenu) {
        pozycjeMenuAlergenyRepository.deleteByPozycjaMenu(pozycjaMenu);
    }

    public List<Pozycje_Menu_Alergeny> findByPozycjaMenu(Pozycje_Menu pozycjaMenu) {
        return pozycjeMenuAlergenyRepository.findByPozycjaMenu(pozycjaMenu);
    }

    public List<Alergeny> findAlergenyByPozycjaMenu(Pozycje_Menu pozycjaMenu) {
        List<Pozycje_Menu_Alergeny> pozycjeMenuAlergeny = pozycjeMenuAlergenyRepository.findByPozycjaMenu(pozycjaMenu);
        return pozycjeMenuAlergeny.stream()
                .map(Pozycje_Menu_Alergeny::getAlergen)
                .collect(Collectors.toList());
    }
}


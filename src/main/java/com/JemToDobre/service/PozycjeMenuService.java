package com.JemToDobre.service;

import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.repository.PozycjeMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PozycjeMenuService {
    @Autowired
    private PozycjeMenuRepository pozycjeMenuRepository;

    public List<Pozycje_Menu> findAll() {
        return pozycjeMenuRepository.findAll();
    }

    public Pozycje_Menu findById(Long id) {
        return pozycjeMenuRepository.findById(id).orElse(null);
    }

    public void save(Pozycje_Menu pozycjeMenu) {
        pozycjeMenuRepository.save(pozycjeMenu);
    }

    public void deleteById(Long id) {
        pozycjeMenuRepository.deleteById(id);
    }
}

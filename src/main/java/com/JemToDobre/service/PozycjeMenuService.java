package com.JemToDobre.service;

import com.JemToDobre.Util.ImageUtils;
import com.JemToDobre.model.Kategoria_Menu;
import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.repository.PozycjeMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PozycjeMenuService {
    @Autowired
    private PozycjeMenuRepository pozycjeMenuRepository;

    public List<Pozycje_Menu> findAll() {
        return pozycjeMenuRepository.findAll();
    }

    public Pozycje_Menu findById(Integer id) {
        return pozycjeMenuRepository.findById(id).orElse(null);
    }

    public void save(Pozycje_Menu pozycjeMenu){
        pozycjeMenuRepository.save(pozycjeMenu);
    }

    public void deleteById(Integer id) {
        pozycjeMenuRepository.deleteById(id);
    }

    public List<Pozycje_Menu> findByKategoria(Kategoria_Menu kategoria) {
        return pozycjeMenuRepository.findByKategoria(kategoria);
    }
}

package com.JemToDobre.service;

import com.JemToDobre.model.Kategoria_Menu;
import com.JemToDobre.repository.KategoriaMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategoriaMenuService {
    @Autowired
    private KategoriaMenuRepository kategoriaMenuRepository;

    public List<Kategoria_Menu> findAll() {
        return kategoriaMenuRepository.findAll();
    }

    public Kategoria_Menu findById(Long id) {
        return kategoriaMenuRepository.findById(id).orElse(null);
    }

    public void save(Kategoria_Menu kategoriaMenu) {
        kategoriaMenuRepository.save(kategoriaMenu);
    }

    public void deleteById(Long id) {
        kategoriaMenuRepository.deleteById(id);
    }
}

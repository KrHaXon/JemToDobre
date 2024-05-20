package com.JemToDobre.service;

import com.JemToDobre.model.Alergeny;
import com.JemToDobre.repository.AlergenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlergenService {
    @Autowired
    private AlergenRepository alergenRepository;

    public List<Alergeny> findAll() {
        return alergenRepository.findAll();
    }

    public Alergeny findById(Long id) {
        return alergenRepository.findById(id).orElse(null);
    }

    public void save(Alergeny alergen) {
        alergenRepository.save(alergen);
    }

    public void deleteById(Long id) {
        alergenRepository.deleteById(id);
    }
}

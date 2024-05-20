package com.JemToDobre.service;

import com.JemToDobre.model.Alergeny;
import com.JemToDobre.repository.AlergenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlergenService {

    private final AlergenRepository alergenRepository;

    @Autowired
    public AlergenService(AlergenRepository alergenRepository) {
        this.alergenRepository = alergenRepository;
    }

    public List<Alergeny> findAll() {
        return alergenRepository.findAll();
    }

    public Alergeny findById(Long id) {
        Optional<Alergeny> alergenOptional = alergenRepository.findById(id);
        return alergenOptional.orElse(null);
    }

    public void save(Alergeny alergen) {
        alergenRepository.save(alergen);
    }

    public void deleteById(Long id) {
        alergenRepository.deleteById(id);
    }
}
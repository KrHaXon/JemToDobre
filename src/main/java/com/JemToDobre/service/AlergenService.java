package com.JemToDobre.service;

import com.JemToDobre.model.Alergeny;
import com.JemToDobre.repository.AlergenRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public List<Alergeny> findAll() {
        return alergenRepository.findAll();
    }

    @Transactional
    public Optional<Alergeny> findById(Integer id) {
        return alergenRepository.findById(Integer.valueOf(id));
    }

    @Transactional
    public Alergeny save(Alergeny alergen) {
        return alergenRepository.save(alergen);
    }

    @Transactional
    public void deleteById(Integer id) {
        alergenRepository.deleteById(id);
    }
}

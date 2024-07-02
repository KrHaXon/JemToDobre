package com.JemToDobre.service;

import com.JemToDobre.model.Adres;
import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.repository.AdresRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdresService {
    private final AdresRepository adresRepository;
    @Autowired
    public AdresService(AdresRepository adresRepository)
    {
        this.adresRepository = adresRepository;
    }
    @Transactional
    public Adres saveAdres(Adres adres) {
        return adresRepository.save(adres);
    }
}

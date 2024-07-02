package com.JemToDobre.service;

import com.JemToDobre.model.Pozycje_Zamowienia;
import com.JemToDobre.repository.PozycjeZamowieniaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PozycjeZamowienService {
    private final PozycjeZamowieniaRepository pozycjeZamowieniaRepository;
    @Autowired
    public PozycjeZamowienService(PozycjeZamowieniaRepository pozycjeZamowieniaRepository)
    {
        this.pozycjeZamowieniaRepository = pozycjeZamowieniaRepository;
    }

}

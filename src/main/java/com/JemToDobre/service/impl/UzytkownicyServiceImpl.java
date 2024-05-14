package com.JemToDobre.service.impl;

import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.repository.UzytkownicyRepository;
import com.JemToDobre.service.UzytkownicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UzytkownicyServiceImpl implements UzytkownicyService {
    @Autowired
    UzytkownicyRepository uzytkownicyRepository;
    @Override
    public Uzytkownicy create(Uzytkownicy uzytkownicy) {
        return null;
    }
}

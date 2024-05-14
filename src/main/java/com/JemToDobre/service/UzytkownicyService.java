package com.JemToDobre.service;

import com.JemToDobre.model.Uzytkownicy;
import org.springframework.stereotype.Service;

@Service
public interface UzytkownicyService {
    public Uzytkownicy create(Uzytkownicy uzytkownicy);
}

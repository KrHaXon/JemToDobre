package com.JemToDobre.repository;

import com.JemToDobre.model.Pozycje_Zamowienia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PozycjeZamowieniaRepository extends JpaRepository<Pozycje_Zamowienia, Long> {
}

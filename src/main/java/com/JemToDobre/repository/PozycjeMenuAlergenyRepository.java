package com.JemToDobre.repository;

import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.model.Pozycje_Menu_Alergeny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PozycjeMenuAlergenyRepository extends JpaRepository<Pozycje_Menu_Alergeny, Long> {
    void deleteByPozycjaMenu(Pozycje_Menu pozycjaMenu);
    List<Pozycje_Menu_Alergeny> findByPozycjaMenu(Pozycje_Menu pozycjaMenu);
}
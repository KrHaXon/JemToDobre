package com.JemToDobre.repository;

import com.JemToDobre.model.Kategoria_Menu;
import com.JemToDobre.model.Pozycje_Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PozycjeMenuRepository extends JpaRepository<Pozycje_Menu, Integer> {
    List<Pozycje_Menu> findByKategoria(Kategoria_Menu kategoria);
}

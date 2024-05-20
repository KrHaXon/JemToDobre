package com.JemToDobre.repository;

import com.JemToDobre.model.Pozycje_Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PozycjeMenuRepository extends JpaRepository<Pozycje_Menu, Long> {
}

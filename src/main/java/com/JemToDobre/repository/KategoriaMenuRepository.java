package com.JemToDobre.repository;

import com.JemToDobre.model.Kategoria_Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriaMenuRepository extends JpaRepository<Kategoria_Menu, Integer> {
}

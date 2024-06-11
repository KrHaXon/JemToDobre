package com.JemToDobre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.JemToDobre.model.Pozycje_Menu;

@Repository
public interface CartRepository extends JpaRepository<Pozycje_Menu, Integer> {
}
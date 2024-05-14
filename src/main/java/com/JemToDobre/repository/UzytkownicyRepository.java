package com.JemToDobre.repository;

import com.JemToDobre.model.Uzytkownicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UzytkownicyRepository extends JpaRepository<Uzytkownicy, Long> {
}

package com.JemToDobre.repository;

import com.JemToDobre.model.Zamowienia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZamowieniaRepository extends JpaRepository<Zamowienia, Long> {
}
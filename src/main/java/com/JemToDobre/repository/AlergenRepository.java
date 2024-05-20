package com.JemToDobre.repository;

import com.JemToDobre.model.Alergeny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlergenRepository extends JpaRepository<Alergeny, Integer> {
}
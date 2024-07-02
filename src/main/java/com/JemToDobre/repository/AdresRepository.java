package com.JemToDobre.repository;

import com.JemToDobre.model.Adres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface AdresRepository extends JpaRepository<Adres, Integer> {

}

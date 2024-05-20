package com.JemToDobre.repository;

import com.JemToDobre.model.Uzytkownicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UzytkownicyRepository extends JpaRepository<Uzytkownicy, Integer> {
    @Query("Select u from Uzytkownicy u where u.Nazwa_Uzytkownika=:Nazwa_Uzytkownika")
    public Optional<Uzytkownicy> findUserByUsername(@Param("Nazwa_Uzytkownika")String username);
    @Query("Select u from Uzytkownicy u where u.Email=:Email")
    public Optional<Uzytkownicy> findByEmail(@Param("Email")String Email);

    // public Optional<Uzytkownicy> findUserByUsername(String username);
    //  public Optional<Uzytkownicy> findUserByEmail(String email);
}
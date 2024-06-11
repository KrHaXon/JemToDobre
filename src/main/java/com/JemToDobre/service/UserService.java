package com.JemToDobre.service;

import com.JemToDobre.model.Uzytkownicy;
import com.JemToDobre.repository.UzytkownicyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UzytkownicyRepository userRepository;

    @Autowired
    public UserService(UzytkownicyRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<Uzytkownicy> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public Optional<Uzytkownicy> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public boolean emailExists(String email) {
        return findUserByEmail(email).isPresent();
    }

    public boolean usernameExists(String username) {
        return findUserByUsername(username).isPresent();
    }

    @Transactional
    public Uzytkownicy saveUser(Uzytkownicy user) {
        return userRepository.save(user);
    }
}

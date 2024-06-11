package com.JemToDobre.service;

import org.springframework.stereotype.Service;
import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.repository.CartRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Pozycje_Menu> getAllItemsInCart() {
        return cartRepository.findAll();
    }

    public Optional<Pozycje_Menu> getItemById(Integer id) {
        return cartRepository.findById(id);
    }

}
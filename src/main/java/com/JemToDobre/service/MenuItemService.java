package com.JemToDobre.service;

import com.JemToDobre.model.Pozycje_Menu;
import com.JemToDobre.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<Pozycje_Menu> findAll() {
        return menuItemRepository.findAll();
    }

    public Pozycje_Menu findById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }
}

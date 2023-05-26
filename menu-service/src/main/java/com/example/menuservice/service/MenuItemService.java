package com.example.menuservice.service;


import com.example.menuservice.dto.MenuItemDTO;
import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    public void addItem(MenuItemDTO dto) {
        MenuItem newItem = MenuItem.builder()
                .menuItemNumber(UUID.randomUUID().toString())
                .price(dto.getPrice())
                .name(dto.getName())
                .build();
        menuItemRepository.save(newItem);
    }
    public void removeItem(String id) {
        MenuItem itemToRemove = menuItemRepository.findById(id).orElseThrow(() -> new RuntimeException("this item isn't available"));
        menuItemRepository.delete(itemToRemove);
    }

    public List<MenuItem> getFullMenu() {
        List<MenuItem> menu = menuItemRepository.findAll();
        menu.sort(Comparator.comparing(MenuItem::getPrice));
        return menu;
    }




}

package com.example.menuservice.controller;


import com.example.menuservice.dto.MenuItemDTO;
import com.example.menuservice.entity.MenuItem;
import com.example.menuservice.service.MenuItemService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewMenuItem(@RequestBody MenuItemDTO dto) {
        menuItemService.addItem(dto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void removeNewMenuItem(@PathVariable() String id) {
        menuItemService.removeItem(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItem> getFullMenu() {
        return menuItemService.getFullMenu();
    }

}

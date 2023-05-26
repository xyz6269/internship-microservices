package com.example.kitchenservice.controller;

import com.example.kitchenservice.DTO.OrderDto;
import com.example.kitchenservice.entity.Order;
import com.example.kitchenservice.service.OrderService;
import com.example.kitchenservice.service.OrdersToPrepareService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitchen")
@RequiredArgsConstructor
public class KitchenController {

    private final OrderService orderService;
    private final OrdersToPrepareService toPrepareService;

    @PostMapping("/add-order")
    public void getOrders(@RequestBody OrderDto dto) {
        orderService.createOrder(dto);
    }

    @PostMapping("/prepare-orders")
    public void prepareOrders() {
        toPrepareService.prepareOrders();
    }

    @GetMapping("/queue-orders/{id}")
    public List<Order> queUpdate(@PathVariable Long id) {
        return toPrepareService.getPrepQueue(id);
    }
}

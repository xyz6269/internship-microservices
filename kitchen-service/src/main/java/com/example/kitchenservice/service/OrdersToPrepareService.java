package com.example.kitchenservice.service;

import com.example.kitchenservice.entity.Order;
import com.example.kitchenservice.entity.OrdersToPrepare;
import com.example.kitchenservice.repository.ItemRepository;
import com.example.kitchenservice.repository.OrderRepository;
import com.example.kitchenservice.repository.OrdersToPrepareRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersToPrepareService {

    private final OrdersToPrepareRepository ordersToPrepareRepository;
    private final OrderRepository orderRepository;

    public void prepareOrders() {
        OrdersToPrepare ordersToPrepare = new OrdersToPrepare();
        List<Order> orderList = orderRepository.findAll();
        log.info(orderList.toString());
        ordersToPrepare.setOrderList(orderList);
        ordersToPrepare.getOrderList().sort(Comparator.comparing(Order::getOrderAmount));
        log.info(ordersToPrepare.getOrderList().toString());
        ordersToPrepareRepository.save(ordersToPrepare);
    }

    public List<Order> getPrepQueue(Long id) {
        return ordersToPrepareRepository.findById(id).orElseThrow(() -> new RuntimeException("empty Queue")).getOrderList();
    }
}

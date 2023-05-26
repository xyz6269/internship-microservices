package com.example.orderservice.repository;


import com.example.orderservice.entity.Order;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order ,Long> {
    Optional<Order> findOrderByCustomerEmail(String email);
}

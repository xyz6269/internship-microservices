package com.example.kitchenservice.repository;


import com.example.kitchenservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order ,Long> {
    Optional<Order> findByCustomerEmail(String email);
}

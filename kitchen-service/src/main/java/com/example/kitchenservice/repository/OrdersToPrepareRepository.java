package com.example.kitchenservice.repository;

import com.example.kitchenservice.entity.Order;
import com.example.kitchenservice.entity.OrdersToPrepare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersToPrepareRepository extends JpaRepository<OrdersToPrepare ,Long> {
}
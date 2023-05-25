package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Basket;
import com.example.springsecurityapplication.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Set<Order>> findOrdersByPersonId(int personId);
}

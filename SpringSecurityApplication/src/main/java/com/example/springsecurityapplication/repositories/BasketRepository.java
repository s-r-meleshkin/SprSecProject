package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository  extends JpaRepository<Basket, Integer> {


}

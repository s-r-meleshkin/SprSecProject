package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Service
public class OrderService {
private  final  OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Person person) {
        Order order = new Order();
        order.addProducts(person.getBasket().getProducts());
        order.setPerson(person);
        order.setStatus("В обработке");
        order.setDateTime(LocalDateTime.now());

        orderRepository.save(order);
        return order;
    }

    public Set<Order> getOrders(Person person) {
        return orderRepository.findOrdersByPersonId(person.getId()).orElseThrow();
    }
}

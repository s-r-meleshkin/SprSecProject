package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.services.BasketService;
import com.example.springsecurityapplication.services.OrderService;
import com.example.springsecurityapplication.services.PersonService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final PersonService personService;
    private final BasketService basketService;
    private final OrderService orderService;


    public OrderController(PersonService personService, BasketService basketService, OrderService orderService) {
        this.personService = personService;
        this.basketService = basketService;
        this.orderService = orderService;
    }

    @GetMapping("")
    public String getOrders(Model model){
        SecurityContext context = SecurityContextHolder.getContext();
        String login = context.getAuthentication().getName();
        Person person = personService.findByName(login);


        model.addAttribute("orders", orderService.getOrders(person));
        return "/user/orders";
    }

    @GetMapping("/create")
    public String createOrder(Model model){
        SecurityContext context = SecurityContextHolder.getContext();
        String login = context.getAuthentication().getName();
        Person person = personService.findByName(login);

        Order order = orderService.createOrder(person);
        person.clearBasket();


        model.addAttribute("orders", orderService.getOrders(person));
        return "/user/orders";
    }
}

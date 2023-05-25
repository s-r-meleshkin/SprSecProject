package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Basket;
import com.example.springsecurityapplication.models.Criteria;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.services.BasketService;
import com.example.springsecurityapplication.services.PersonService;
import com.example.springsecurityapplication.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class BasketController {
    private final BasketService basketService;

    private final ProductService productService;
    private final PersonService personService;

    public BasketController(BasketService basketService, ProductService productService, PersonService personService) {
        this.basketService = basketService;
        this.productService = productService;
        this.personService = personService;
    }

    @GetMapping("")
    public String getBasket(Model model){
        SecurityContext context = SecurityContextHolder.getContext();
        String login = context.getAuthentication().getName();
        Person person = personService.findByName(login);
        model.addAttribute("cart_product", person.getBasket().getProducts());
        model.addAttribute("price", person.getBasket().getPrice());
        return "/user/cart";
    }
//    @PostMapping("")
//    public String getBasket(@CurrentSecurityContext(expression = "authentication") Authentication authentication1, @RequestBody Product product){
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = (Authentication) context.getAuthentication().getCredentials();
//
//        basketService.addProduct(product);
//        return null;
//    }
    @PostMapping("/add/{id}")
    public String getBasket(Model model, @ModelAttribute("criteria") Criteria criteria,@PathVariable int id){
        SecurityContext context = SecurityContextHolder.getContext();
        String login = context.getAuthentication().getName();
        Person person =personService.findByName(login);
        List<Product> products = productService.filter(criteria);
        model.addAttribute("products", products);
        basketService.addProduct(person,id);
        return "/user/index";
    }

}

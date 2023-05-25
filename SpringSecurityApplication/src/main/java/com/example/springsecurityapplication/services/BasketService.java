package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Basket;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.BasketRepository;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;


    public BasketService(BasketRepository basketRepository, ProductService productService) {
        this.basketRepository = basketRepository;
        this.productService = productService;
    }
    public Basket createBasket(){
        Basket basket = new Basket();
     basketRepository.save(basket);
        return basket;
    }

    public Basket addProduct(Person person, int id){

        Product product  =productService.getProductId(id);
        Basket basket = person.getBasket();
        basket.addProduct(product);
        basketRepository.save(basket);
        return basket;
    }


}

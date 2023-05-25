package com.example.springsecurityapplication.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Basket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "basket_products",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    Set<Product> products = new LinkedHashSet<>();

    public Basket(int id,  Set<Product> products) {
        this.id = id;
        this.products = products;
    }

    public Basket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    public void addProduct(Product product){
        products.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        if (id != basket.id) return false;
        return Objects.equals(products, basket.products);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }

    public void clear() {
        products.clear();
    }

    public double getPrice() {
        double res=0;
        for(Product p: getProducts()){
            res+=p.getPrice();
        }
        return res;
    }
}

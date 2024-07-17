package org.example.springbootcart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.example.springbootcart.model.Product;
import org.example.springbootcart.model.Cart;
import org.example.springbootcart.service.CartService;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCartApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CartService cartService) {
        return (args) -> {
            Product product1 = new Product(1, "Product1", 10.99);
            Product product2 = new Product(2, "Product2", 20.99);
            Product product3 = new Product(3, "Product3", 30.99);

            Cart cart = new Cart();
            cart.setId(1);
            cart.setUserId(1001);
            cart.setDate("2024-07-17");
            cart.setProducts(Arrays.asList(product1, product2, product3));

            cartService.addCart(cart);
        };
    }
}
package org.example.springbootcart.controller;

import org.example.springbootcart.model.Cart;
import org.example.springbootcart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addCart(@RequestBody Cart cart) {
        cartService.addCart(cart);
    }

    @GetMapping("/all")
    public List<Cart> getCarts() {
        return cartService.getCarts();
    }

    @DeleteMapping("/remove/{id}")
    public void removeCart(@PathVariable double id) {
        cartService.removeCart(id);
    }
}

package org.example.springbootcart.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cart {
    private double id;
    private double userId;
    private String date;
    private List<Product> products;
}

package org.example.springbootcart.model;

public class Product {
    private double id;
    private String name;
    private double price;
    public Product() {
    }

    public Product(double id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

package org.example.springbootcart.service;

import org.example.springbootcart.model.Cart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private List<Cart> carts = new ArrayList<>();

    public void addCart(Cart cart) {
        carts.add(cart);
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void removeCart(double id) {
        carts.removeIf(cart -> cart.getId() == id);
    }
}

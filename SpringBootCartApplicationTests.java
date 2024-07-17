package org.example.springbootcart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootCartApplicationTests {

    @Test
    void contextLoads() {
    }

}

package org.example.springbootcart.controller;
import org.example.springbootcart.model.Item;
import org.example.springbootcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public void addItem(@RequestBody Item item) {
        cartService.addItem(item);
    }

    @GetMapping("/items")
    public List<Item> getItems() {
        return cartService.getItems();
    }

    @DeleteMapping("/remove/{id}")
    public void removeItem(@PathVariable int id) {
        cartService.removeItem(id);
    }
}

package org.example.springbootcart.model;

public class Item {
    private int id;
    private String name;
    private double price;

    public Item() {
    }

    public Item(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

import org.example.springbootcart.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void removeItem(int id) {
        items.removeIf(item -> item.getId() == id);
    }
}

package org.example.springbootcart;

import org.example.springbootcart.model.Item;
import org.example.springbootcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCartApplication implements CommandLineRunner {

    @Autowired
    private CartService cartService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCartApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cartService.addItem(new Item(1, "Item1", 10.99));
        cartService.addItem(new Item(2, "Item2", 20.99));
        cartService.addItem(new Item(3, "Item3", 30.99));
    }
}

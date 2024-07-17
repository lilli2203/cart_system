package org.example.springbootcart.controllers;

import org.example.springbootcart.modals.Cart;
import org.example.springbootcart.services.CartService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/carts/{id}")
    public Cart getSingleCart(@PathVariable("id") Long id) {
        return cartService.getSingleCart(id);
    }

    @GetMapping("/carts")
    public List<Cart> getAllCart() {
        return cartService.getAllCarts();
    }

    @GetMapping("/carts/{sDate}/{eDate}")
    public List<Cart> getCartsInDateRange(@PathVariable("sDate") String sDate, @PathVariable("eDate") String eDate) {
        return cartService.getCartsInDateRange(sDate, eDate);
    }

    @GetMapping("/carts/user/{id}")
    public List<Cart> getUserCarts(@PathVariable("id") Long id) {
        return cartService.getUserCarts(id);
    }

    @DeleteMapping("/carts/{id}")
    public void deleteCart(@PathVariable("id") Long id) {
        cartService.deleteCart(id);
    }

    @PostMapping("/carts")
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

    @PutMapping("/carts")
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }
}
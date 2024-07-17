package org.example.springbootcart.services;

import org.example.springbootcart.modals.Cart;

import java.util.List;

public interface CartService {
    public Cart getSingleCart(Long id);

    public List<Cart> getAllCarts();

    public List<Cart> getCartsInDateRange (String startDate, String endDate);

    public List<Cart> getUserCarts(Long id);

    public void deleteCart(Long id);

    public Cart createCart(Cart cart);

    public Cart updateCart(Cart cart);

    List<Cart> getAllProducts();

    Cart getCart(long id);

    List<Cart> limitedCarts(long limit);

    List<Cart> sortedCarts(String order);

    List<Cart> userCart(long userId);

    void addNewCart(Cart cart);
}
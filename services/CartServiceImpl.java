package org.example.springbootcart.services;

import org.example.springbootcart.dtos.CartRecieveDTO;
import org.example.springbootcart.modals.Cart;
import org.example.springbootcart.modals.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private RestTemplate restTemplate = new RestTemplate();

    public Cart getSingleCart(Long id) {
        CartRecieveDTO fakeStoreCartServiceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/" + id,
                CartRecieveDTO.class
        );
        Cart cart = new Cart();
        cart.setId(fakeStoreCartServiceDto.getId());
        cart.setUserId(fakeStoreCartServiceDto.getUserId());
        cart.setDate(fakeStoreCartServiceDto.getDate());
        List<Product> products = new ArrayList<>();
        for (HashMap<String, Integer> ele : fakeStoreCartServiceDto.getProducts()) {
            Product product = new Product();
            product.setProductId(ele.get("productId"));
            product.setQuantity(ele.get("quantity"));
            products.add(product);
        }
        cart.setProducts(products);
        System.out.println("Cart fetched successfully!!!");
        return cart;
    }

    public List<Cart> getAllCarts() {
        CartRecieveDTO[] fakeStoreCartServiceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/",
                CartRecieveDTO[].class
        );
        List<Cart> ans = new ArrayList<>();
        for (CartRecieveDTO carts : fakeStoreCartServiceDto) {
            Cart cart = new Cart();
            cart.setId(carts.getId());
            cart.setUserId(carts.getUserId());
            cart.setDate(carts.getDate());
            List<Product> products = new ArrayList<>();
            for (HashMap<String, Integer> ele : carts.getProducts()) {
                Product product = new Product();
                product.setProductId(ele.get("productId"));
                product.setQuantity(ele.get("quantity"));
                products.add(product);
            }
            cart.setProducts(products);
            ans.add(cart);
        }
        System.out.println("All carts fetched successfully!!!");
        return ans;
    }

    public List<Cart> getCartsInDateRange(String startDate, String endDate) {
        CartRecieveDTO[] fakeStoreCartServiceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts?startdate="+startDate+"&enddate="+endDate,
                CartRecieveDTO[].class
        );
        List<Cart> ans = new ArrayList<>();
        for (CartRecieveDTO carts : fakeStoreCartServiceDto) {
            Cart cart = new Cart();
            cart.setId(carts.getId());
            cart.setUserId(carts.getUserId());
            cart.setDate(carts.getDate());
            List<Product> products = new ArrayList<>();
            for (HashMap<String, Integer> ele : carts.getProducts()) {
                Product product = new Product();
                product.setProductId(ele.get("productId"));
                product.setQuantity(ele.get("quantity"));
                products.add(product);
            }
            cart.setProducts(products);
            ans.add(cart);
        }
        System.out.println("All carts in date range "+startDate+" to "+endDate+" fetched successfully!!!");
        return ans;
    }

    public List<Cart> getUserCarts(Long id) {
        CartRecieveDTO[] fakeStoreCartServiceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/user/" + id,
                CartRecieveDTO[].class
        );
        List<Cart> ans = new ArrayList<>();
        for (CartRecieveDTO carts : fakeStoreCartServiceDto) {
            Cart cart = new Cart();
            cart.setId(carts.getId());
            cart.setUserId(carts.getUserId());
            cart.setDate(carts.getDate());
            List<Product> products = new ArrayList<>();
            for (HashMap<String, Integer> ele : carts.getProducts()) {
                Product product = new Product();
                product.setProductId(ele.get("productId"));
                product.setQuantity(ele.get("quantity"));
                products.add(product);
            }
            cart.setProducts(products);
            ans.add(cart);
        }
        System.out.println("User's cart fetched successfully!!!");
        return ans;
    }

    public void deleteCart(Long id) {
        restTemplate.delete("https://fakestoreapi.com/carts/" + id);
        System.out.println("Cart deleted successfully!!!");
    }

    public Cart createCart(Cart cart) {
        CartRecieveDTO storeCart = new CartRecieveDTO();
        storeCart.setId(cart.getId());
        storeCart.setUserId(cart.getUserId());
        storeCart.setDate(cart.getDate());
        HashMap<String, Integer>[] products = new HashMap[cart.getProducts().size()];
        for (int i = 0; i < cart.getProducts().size(); i++) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("productId", (int)cart.getProducts().get(i).getProductId());
            map.put("quantity", (int)cart.getProducts().get(i).getQuantity());
            products[i] = map;
        }

        restTemplate.postForObject(
                "https://fakestoreapi.com/carts",
                storeCart,
                CartRecieveDTO.class
        );
        System.out.println("Cart created successfully!!!");
        return cart;
    }

    public Cart updateCart(Cart cart) {
        CartRecieveDTO storeCart = new CartRecieveDTO();
        storeCart.setId(cart.getId());
        storeCart.setUserId(cart.getUserId());
        storeCart.setDate(cart.getDate());
        HashMap<String, Integer>[] products = new HashMap[cart.getProducts().size()];
        for (int i = 0; i < cart.getProducts().size(); i++) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("productId", (int)cart.getProducts().get(i).getProductId());
            map.put("quantity", (int)cart.getProducts().get(i).getQuantity());
            products[i] = map;
        }

        restTemplate.put(
                "https://fakestoreapi.com/carts/" + cart.getId(),
                storeCart
        );
        System.out.println("Cart updated successfully!!!");
        return cart;
    }

    @Override
    public List<Cart> getAllProducts() {
        return null;
    }

    @Override
    public Cart getCart(long id) {
        return null;
    }

    @Override
    public List<Cart> limitedCarts(long limit) {
        return null;
    }

    @Override
    public List<Cart> sortedCarts(String order) {
        return null;
    }

    @Override
    public List<Cart> userCart(long userId) {
        return null;
    }

    @Override
    public void addNewCart(Cart cart) {

    }

}
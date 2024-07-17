package org.example.springbootcart.modals;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Product {
    private long productId;  
    private String productName;  
    private double quantity;
    private double price;  
    private String description; 

    public Product(long productId, String productName, double quantity, double price, String description) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public void updateQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void updatePrice(double price) {
        this.price = price;
    }

    public String getProductDetails() {
        return "Product ID: " + productId +
               "\nProduct Name: " + productName +
               "\nQuantity: " + quantity +
               "\nPrice: " + price +
               "\nDescription: " + description;
    }
}

@Getter
@Setter
class ProductCatalog {
    private List<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(long productId) {
        products.removeIf(product -> product.getProductId() == productId);
    }

    public Product findProductById(long productId) {
        return products.stream()
                .filter(product -> product.getProductId() == productId)
                .findFirst()
                .orElse(null);
    }

    public String listAllProducts() {
        StringBuilder productList = new StringBuilder();
        for (Product product : products) {
            productList.append(product.getProductDetails()).append("\n\n");
        }
        return productList.toString();
    }
}

@Getter
@Setter
class ShoppingCart {
    private List<Product> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }
    public void addToCart(Product product) {
        cartItems.add(product);
    }
    public void removeFromCart(long productId) {
        cartItems.removeIf(product -> product.getProductId() == productId);
    }
    public double getTotalPrice() {
        return cartItems.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public String listCartItems() {
        StringBuilder cartList = new StringBuilder();
        for (Product product : cartItems) {
            cartList.append(product.getProductDetails()).append("\n\n");
        }
        return cartList.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        ShoppingCart cart = new ShoppingCart();

        Product product1 = new Product(1, "Laptop", 10, 999.99, "High-performance laptop");
        Product product2 = new Product(2, "Phone", 20, 499.99, "Latest model smartphone");

        catalog.addProduct(product1);
        catalog.addProduct(product2);

        cart.addToCart(product1);
        cart.addToCart(product2);

        System.out.println("Catalog:\n" + catalog.listAllProducts());
        System.out.println("Cart:\n" + cart.listCartItems());
        System.out.println("Total Cart Price: $" + cart.getTotalPrice());

        cart.removeFromCart(1);
        System.out.println("Cart after removal:\n" + cart.listCartItems());
        System.out.println("Total Cart Price after removal: $" + cart.getTotalPrice());
    }
}

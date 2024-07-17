package org.example.springbootcart.modals;

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
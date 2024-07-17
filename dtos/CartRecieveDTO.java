package org.example.springbootcart.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.springbootcart.modals.Product;

import java.util.HashMap;

@Getter
@Setter
public class CartRecieveDTO{
    private double id;
    private double userId;
    private String date;
    private HashMap<String, Integer>[] products;
}
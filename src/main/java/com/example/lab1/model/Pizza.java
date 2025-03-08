package com.example.lab1.model;

import java.util.List;

public class Pizza {
    private Long id;
    private String name;
    private String size;
    private List<String> toppings;
    private double price;

    public Pizza() {}

    public Pizza(Long id, String name, String size, List<String> toppings, double price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.toppings = toppings;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    public List<String> getToppings() { return toppings; }
    public void setToppings(List<String> toppings) { this.toppings = toppings; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
} 
package com.example.lab1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;
import java.util.List;

@Data
@Document(collection = "pizzas")
public class Pizza {
    @Id
    private String id;
    private String name;
    private String size;
    private List<String> toppings;
    private double price;
    
    @Field("isSpecial")
    private boolean isSpecial;
    private int rating;

    public Pizza() {
        this.isSpecial = false;
        this.rating = 0;
    }

    public Pizza(String name, String size, List<String> toppings, double price, boolean isSpecial) {
        this.name = name;
        this.size = size;
        this.toppings = toppings;
        this.price = price;
        this.isSpecial = isSpecial;
        this.rating = 0;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
} 
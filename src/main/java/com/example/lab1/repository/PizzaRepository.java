package com.example.lab1.repository;

import com.example.lab1.model.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PizzaRepository extends MongoRepository<Pizza, String> {
    List<Pizza> findBySize(String size);
    List<Pizza> findByPriceLessThan(double price);
    List<Pizza> findByIsSpecialTrue();
} 
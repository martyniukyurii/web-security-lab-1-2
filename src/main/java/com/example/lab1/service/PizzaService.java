package com.example.lab1.service;

import com.example.lab1.model.Pizza;
import com.example.lab1.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> getSpecialPizzas() {
        return pizzaRepository.findByIsSpecialTrue();
    }

    public Pizza getPizza(String id) {
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza not found with id: " + id));
    }

    public Pizza addPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(String id, Pizza pizza) {
        Pizza existingPizza = getPizza(id);
        existingPizza.setName(pizza.getName());
        existingPizza.setSize(pizza.getSize());
        existingPizza.setToppings(pizza.getToppings());
        existingPizza.setPrice(pizza.getPrice());
        existingPizza.setSpecial(pizza.isSpecial());
        existingPizza.setRating(pizza.getRating());
        return pizzaRepository.save(existingPizza);
    }

    public void deletePizza(String id) {
        pizzaRepository.deleteById(id);
    }

    public List<Pizza> getPizzasBySize(String size) {
        return pizzaRepository.findBySize(size);
    }

    public List<Pizza> getPizzasByPrice(double price) {
        return pizzaRepository.findByPriceLessThan(price);
    }

    public Pizza updatePizzaRating(String id, int rating) {
        Pizza pizza = getPizza(id);
        pizza.setRating(rating);
        return pizzaRepository.save(pizza);
    }
} 
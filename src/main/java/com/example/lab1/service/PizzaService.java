package com.example.lab1.service;

import com.example.lab1.model.Pizza;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PizzaService {
    private final Map<Long, Pizza> pizzas = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public PizzaService() {
        // Додамо кілька піц для прикладу
        List<String> margheritaToppings = new ArrayList<>();
        margheritaToppings.add("Tomato Sauce");
        margheritaToppings.add("Mozzarella");
        margheritaToppings.add("Basil");
        
        List<String> pepperoniToppings = new ArrayList<>();
        pepperoniToppings.add("Tomato Sauce");
        pepperoniToppings.add("Mozzarella");
        pepperoniToppings.add("Pepperoni");

        addPizza(new Pizza(counter.incrementAndGet(), "Margherita", "Medium", margheritaToppings, 199.99));
        addPizza(new Pizza(counter.incrementAndGet(), "Pepperoni", "Large", pepperoniToppings, 249.99));
    }

    public List<Pizza> getAllPizzas() {
        return new ArrayList<>(pizzas.values());
    }

    public Pizza getPizza(Long id) {
        return pizzas.get(id);
    }

    public Pizza addPizza(Pizza pizza) {
        if (pizza.getId() == null) {
            pizza.setId(counter.incrementAndGet());
        }
        pizzas.put(pizza.getId(), pizza);
        return pizza;
    }

    public Pizza updatePizza(Long id, Pizza pizza) {
        if (pizzas.containsKey(id)) {
            pizza.setId(id);
            pizzas.put(id, pizza);
            return pizza;
        }
        return null;
    }

    public boolean deletePizza(Long id) {
        return pizzas.remove(id) != null;
    }

    public List<Pizza> getPizzasBySize(String size) {
        return pizzas.values().stream()
                .filter(p -> p.getSize().equalsIgnoreCase(size))
                .toList();
    }

    public List<Pizza> getPizzasUnderPrice(double maxPrice) {
        return pizzas.values().stream()
                .filter(p -> p.getPrice() <= maxPrice)
                .toList();
    }
} 
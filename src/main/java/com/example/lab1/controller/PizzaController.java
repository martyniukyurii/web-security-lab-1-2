package com.example.lab1.controller;

import com.example.lab1.model.Pizza;
import com.example.lab1.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizza(@PathVariable Long id) {
        Pizza pizza = pizzaService.getPizza(id);
        if (pizza != null) {
            return ResponseEntity.ok(pizza);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Pizza addPizza(@RequestBody Pizza pizza) {
        return pizzaService.addPizza(pizza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable Long id, @RequestBody Pizza pizza) {
        Pizza updatedPizza = pizzaService.updatePizza(id, pizza);
        if (updatedPizza != null) {
            return ResponseEntity.ok(updatedPizza);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable Long id) {
        if (pizzaService.deletePizza(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/size/{size}")
    public List<Pizza> getPizzasBySize(@PathVariable String size) {
        return pizzaService.getPizzasBySize(size);
    }

    @GetMapping("/price")
    public List<Pizza> getPizzasUnderPrice(@RequestParam double maxPrice) {
        return pizzaService.getPizzasUnderPrice(maxPrice);
    }
} 
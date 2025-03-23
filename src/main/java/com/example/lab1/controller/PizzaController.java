package com.example.lab1.controller;

import com.example.lab1.model.Pizza;
import com.example.lab1.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    // Публічні ендпоінти (без автентифікації)
    @GetMapping("/public/list")
    public ResponseEntity<Map<String, Object>> getPublicPizzas() {
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "data", pizzas
        ));
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<Map<String, Object>> getPublicPizza(@PathVariable String id) {
        Pizza pizza = pizzaService.getPizza(id);
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "data", pizza
        ));
    }

    // Ендпоінти для користувачів (тільки перегляд)
    @GetMapping("/user/list")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<String, Object>> getUserPizzas() {
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "data", pizzas
        ));
    }

    // Ендпоінти для адміністраторів
    @PostMapping("/admin/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> addAdminPizza(@RequestBody Pizza pizza) {
        if (pizza.isSpecial()) {
            return ResponseEntity.status(403).body(Map.of(
                "status", 403,
                "error", "Адміністратор не може створювати спеціальні піци"
            ));
        }
        Pizza savedPizza = pizzaService.addPizza(pizza);
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "data", savedPizza
        ));
    }

    @PutMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> updateAdminPizza(@PathVariable String id, @RequestBody Pizza pizza) {
        Pizza existingPizza = pizzaService.getPizza(id);
        if (existingPizza.isSpecial() || pizza.isSpecial()) {
            return ResponseEntity.status(403).body(Map.of(
                "status", 403,
                "error", "Адміністратор не може редагувати спеціальні піци"
            ));
        }
        Pizza updatedPizza = pizzaService.updatePizza(id, pizza);
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "data", updatedPizza
        ));
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> deleteAdminPizza(@PathVariable String id) {
        Pizza existingPizza = pizzaService.getPizza(id);
        if (existingPizza.isSpecial()) {
            return ResponseEntity.status(403).body(Map.of(
                "status", 403,
                "error", "Адміністратор не може видаляти спеціальні піци"
            ));
        }
        pizzaService.deletePizza(id);
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "message", "Піцу успішно видалено"
        ));
    }

    // Ендпоінти для суперадміністраторів
    @GetMapping("/superadmin/special")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Map<String, Object>> getSpecialPizzas() {
        List<Pizza> specialPizzas = pizzaService.getSpecialPizzas();
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "data", specialPizzas
        ));
    }

    @PostMapping("/superadmin/add")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Map<String, Object>> addSuperAdminPizza(@RequestBody Pizza pizza) {
        Pizza savedPizza = pizzaService.addPizza(pizza);
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "data", savedPizza
        ));
    }

    @PutMapping("/superadmin/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Map<String, Object>> updateSuperAdminPizza(@PathVariable String id, @RequestBody Pizza pizza) {
        Pizza updatedPizza = pizzaService.updatePizza(id, pizza);
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "data", updatedPizza
        ));
    }

    @DeleteMapping("/superadmin/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Map<String, Object>> deleteSuperAdminPizza(@PathVariable String id) {
        pizzaService.deletePizza(id);
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "message", "Піцу успішно видалено"
        ));
    }

    // Загальні ендпоінти (потребують автентифікації)
    @GetMapping("/size/{size}")
    public ResponseEntity<Map<String, Object>> getPizzasBySize(@PathVariable String size) {
        List<Pizza> pizzas = pizzaService.getPizzasBySize(size);
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "data", pizzas
        ));
    }

    @GetMapping("/price")
    public ResponseEntity<Map<String, Object>> getPizzasByPrice(@RequestParam double maxPrice) {
        List<Pizza> pizzas = pizzaService.getPizzasByPrice(maxPrice);
        return ResponseEntity.ok(Map.of(
            "status", 200,
            "data", pizzas
        ));
    }
} 
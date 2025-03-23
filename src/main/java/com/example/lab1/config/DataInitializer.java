package com.example.lab1.config;

import com.example.lab1.model.Pizza;
import com.example.lab1.model.User;
import com.example.lab1.repository.PizzaRepository;
import com.example.lab1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Створюємо користувачів
        if (userRepository.count() == 0) {
            List<User> defaultUsers = Arrays.asList(
                new User("user", passwordEncoder.encode("user123"), "USER"),
                new User("admin", passwordEncoder.encode("admin123"), "ADMIN"),
                new User("superadmin", passwordEncoder.encode("superadmin123"), "SUPERADMIN")
            );
            userRepository.saveAll(defaultUsers);
        }

        // Створюємо базові піци
        if (pizzaRepository.count() == 0) {
            List<Pizza> defaultPizzas = Arrays.asList(
                new Pizza("Маргарита", "Велика", Arrays.asList("томатний соус", "моцарела", "базилік"), 199.99, false),
                new Pizza("Пепероні", "Велика", Arrays.asList("томатний соус", "моцарела", "пепероні"), 249.99, false),
                new Pizza("Гавайська", "Велика", Arrays.asList("томатний соус", "моцарела", "курка", "ананас"), 279.99, false),
                new Pizza("BBQ Курка", "Велика", Arrays.asList("BBQ соус", "моцарела", "курка", "цибуля"), 299.99, false),
                new Pizza("Вегетаріанська", "Велика", Arrays.asList("томатний соус", "моцарела", "гриби", "перець", "цибуля"), 259.99, false)
            );
            pizzaRepository.saveAll(defaultPizzas);
        }
    }
} 
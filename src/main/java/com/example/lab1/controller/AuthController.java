package com.example.lab1.controller;

import com.example.lab1.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestParam String username, @RequestParam String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        if (userDetails != null && userDetails.getPassword().equals("{noop}" + password)) {
            String token = jwtService.generateToken(username);
            return ResponseEntity.ok(token);
        }
        
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Boolean>> validateToken(@RequestParam String token, @RequestParam String username) {
        boolean isValid = jwtService.validateToken(token, username);
        return ResponseEntity.ok(Map.of("valid", isValid));
    }
} 
package com.example.demo.user.controller;

import com.example.demo.user.dto.RegisterSuggestions;
import com.example.demo.user.dto.UpdateUserRequest;
import com.example.demo.user.dto.UserRequest;
import com.example.demo.user.dto.UserResponse;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.demo.user.entity.User;
import com.example.demo.user.Service.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users") // rota base para todos os endpoints
public class UserController {

    private final UserService service;


    // ========== GET ==========

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe() {
        return ResponseEntity.ok(service.getMe());
    }


    @GetMapping("/validate-username")
    public ResponseEntity<RegisterSuggestions> validateUsername(
            @RequestParam String userName
    ) {
        return ResponseEntity.ok(service.validateUsername(userName));
    }

    @GetMapping("/validate-email")
    public ResponseEntity<RegisterSuggestions> validateEmail(
            @RequestParam String email
    ) {
        return ResponseEntity.ok(service.validateEmail(email));
    }

    // ========== POST ==========

    @PostMapping("/new")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(request));
    }

    // ========== DELETE ==========

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser() {
        service.deleteUser();
        return ResponseEntity.noContent().build();
    }

    // ========== PUT ==========

    @PutMapping("/me")
    public ResponseEntity<UserResponse> uptadeUser(@Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(service.updateUser(request));
    }
}

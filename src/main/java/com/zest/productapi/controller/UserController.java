package com.zest.productapi.controller;

import com.zest.productapi.dto.UserRegisterRequest;
import com.zest.productapi.dto.UserRegisterResponse;
import com.zest.productapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(
            @Valid @RequestBody UserRegisterRequest request) {

        UserRegisterResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }
}
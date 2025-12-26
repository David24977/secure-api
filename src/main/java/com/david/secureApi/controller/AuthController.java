package com.david.secureApi.controller;

import com.david.secureApi.dto.UserRequestDto;
import com.david.secureApi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }

}


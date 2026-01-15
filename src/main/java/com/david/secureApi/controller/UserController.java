package com.david.secureApi.controller;

import com.david.secureApi.dto.UserAdminUpdateDto;
import com.david.secureApi.dto.UserRequestDto;
import com.david.secureApi.dto.UserResponseDto;
import com.david.secureApi.dto.UserUpdateDto;
import com.david.secureApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // =========================
    // CONSULTAS
    // =========================

    // Listar todos los usuarios (USER y ADMIN)
    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    // Obtener usuario por ID (USER y ADMIN)
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    // =========================
    // USUARIO NORMAL
    // =========================

    // Crear usuario (registro)
    @PostMapping
    public UserResponseDto createUser(
            @Valid @RequestBody UserRequestDto dto) {
        return userService.createUser(dto);
    }

    // Update parcial del propio usuario
    @PatchMapping("/{id}")
    public UserResponseDto updateUser(
            @PathVariable UUID id,
            @RequestBody UserUpdateDto dto) {
        return userService.updatePartialUser(id, dto);
    }

    // Borrar usuario (puede ser propio o controlado por seguridad)
    @DeleteMapping("/{id}")
    public UserResponseDto deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }

    // =========================
    // ADMIN
    // =========================

    // Update ADMIN (rol / enabled)
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/admin/{id}")
    public UserResponseDto adminUpdateUser(
            @PathVariable UUID id,
            @RequestBody UserAdminUpdateDto dto) {
        return userService.adminUpdateUser(id, dto);
    }
}


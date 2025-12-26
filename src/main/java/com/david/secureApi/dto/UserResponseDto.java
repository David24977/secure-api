package com.david.secureApi.dto;

import com.david.secureApi.model.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDto(
        UUID id, String username, Role role, Boolean enabled, LocalDateTime created
) {
}

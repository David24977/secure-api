package com.david.secureApi.dto;

import com.david.secureApi.model.Role;

public record UserAdminUpdateDto(Role role, Boolean enabled) {
}

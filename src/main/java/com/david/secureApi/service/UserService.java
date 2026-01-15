package com.david.secureApi.service;

import com.david.secureApi.dto.UserAdminUpdateDto;
import com.david.secureApi.dto.UserRequestDto;
import com.david.secureApi.dto.UserResponseDto;
import com.david.secureApi.dto.UserUpdateDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserResponseDto> getUsers();

    UserResponseDto getUser(UUID id);

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updatePartialUser(UUID id, UserUpdateDto userUpdateDto);

    UserResponseDto deleteUser(UUID id);

    UserResponseDto adminUpdateUser(UUID id, UserAdminUpdateDto userAdminUpdateDto);
}

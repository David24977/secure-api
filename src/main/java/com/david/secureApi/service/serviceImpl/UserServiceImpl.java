package com.david.secureApi.service.serviceImpl;

import com.david.secureApi.dto.UserAdminUpdateDto;
import com.david.secureApi.dto.UserRequestDto;
import com.david.secureApi.dto.UserResponseDto;
import com.david.secureApi.dto.UserUpdateDto;
import com.david.secureApi.model.User;
import com.david.secureApi.repository.UserRepository;
import com.david.secureApi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public UserResponseDto getUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        return mapToDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = new User();

        user.setUsername(userRequestDto.username());
        user.setPassword(userRequestDto.password());

        userRepository.save(user);
        return mapToDto(user);
    }

    @Override
    public UserResponseDto updatePartialUser(UUID id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if(userUpdateDto.username() != null){
            user.setUsername(userUpdateDto.username());
        }
        if(userUpdateDto.password() != null ){
            user.setPassword(user.getPassword());
        }
        userRepository.save(user);
        return mapToDto(user);
    }

    @Override
    public UserResponseDto deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        userRepository.delete(user);
        return mapToDto(user);

    }

    @Override
    public UserResponseDto adminUpdateUser(UUID id, UserAdminUpdateDto userAdminUpdateDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        user.setRole(userAdminUpdateDto.role());
        user.setEnabled(userAdminUpdateDto.enabled());

        userRepository.save(user);

        return mapToDto(user);
    }

    private UserResponseDto mapToDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getEnabled(),
                user.getCreatedAt()
        );

    }
}

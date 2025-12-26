package com.david.secureApi.service;

import com.david.secureApi.dto.UserRequestDto;

public interface AuthService {

    String login(UserRequestDto request);
}

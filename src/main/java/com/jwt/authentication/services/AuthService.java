package com.jwt.authentication.services;

import com.jwt.authentication.models.AuthResponse;
import com.jwt.authentication.models.LoginRequest;
import com.jwt.authentication.models.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}

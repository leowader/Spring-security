package com.jwt.authentication.controller;

import com.jwt.authentication.models.AuthResponse;
import com.jwt.authentication.models.LoginRequest;
import com.jwt.authentication.models.RegisterRequest;
import com.jwt.authentication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="/api/auth")
public class AuthController {
    @Autowired
    private  AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
       return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }
}

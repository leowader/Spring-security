package com.jwt.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GretingController {

    @GetMapping("/hola")
    public String greeting() {
        return "Hello World";
    }
}

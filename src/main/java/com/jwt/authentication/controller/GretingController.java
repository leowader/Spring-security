package com.jwt.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/greting")
public class GretingController {
    @GetMapping("/holaPublico")
    public String greeting() {
        return "Hello World";
    }
    @GetMapping("/helloProtegido")
    public String greetingProtected() {
        return "Hello World protected";
    }
}
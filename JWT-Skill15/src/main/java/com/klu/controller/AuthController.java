package com.klu.controller;

import com.klu.model.User;
import com.klu.security.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private Util jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return jwtUtil.generateToken(user.getUsername());
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "working";
    }
}
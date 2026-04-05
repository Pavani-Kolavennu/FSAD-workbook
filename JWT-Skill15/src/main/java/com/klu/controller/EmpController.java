package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.klu.model.User;
import com.klu.service.UserService;

@RestController
@RequestMapping("/employee")
public class EmpController {

	@Autowired
    private UserService service;
	
    @GetMapping("/profile")
    public User profile(Authentication auth) {
    	return service.getUser(auth.getName());
    }
}
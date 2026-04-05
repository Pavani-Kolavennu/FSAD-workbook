package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.service.UserService;
import com.klu.model.User;


@RestController
@RequestMapping("/admin")
public class AdminController {

	    @Autowired
	    private UserService service;

	    @PostMapping("/add")
	    public User add(@RequestBody User user) {
	        return service.saveUser(user);
	    }

	    @DeleteMapping("/delete/{id}")
	    public String delete(@PathVariable int id) {
	        service.deleteUser(id);
	        return "Deleted Successfully";
	    }
	}
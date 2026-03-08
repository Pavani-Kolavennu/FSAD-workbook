package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Student;
import com.klu.service.StudentService;
import com.klu.exception.InvalidInputException;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Long id) {

        try 
        {
        	return service.getStudentById(id);
        }
        catch(NumberFormatException e) 
        {
            throw new InvalidInputException("Invalid ID format. ID must be a number.");
        }
    }
}
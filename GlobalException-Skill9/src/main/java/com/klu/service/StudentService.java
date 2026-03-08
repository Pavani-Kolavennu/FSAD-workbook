package com.klu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Student;
import com.klu.repo.StudentRepo;
import com.klu.exception.StudentNotFoundException;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;

    public Student getStudentById(Long id) {

        Optional<Student> student = repo.findById(id);

        if(student.isEmpty()) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }

        return student.get();
    }
}
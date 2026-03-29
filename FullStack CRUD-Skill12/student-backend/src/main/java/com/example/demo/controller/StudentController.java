package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/students")
public class StudentController 
{
    @Autowired
    private StudentService service;

    // CREATE
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) 
    {
        return ResponseEntity.ok(service.createStudent(student));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents()
    {
        return ResponseEntity.ok(service.getAllStudents());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) 
    {
        Student student = service.getStudentById(id);
        if (student != null)
            return ResponseEntity.ok(student);
        else
            return ResponseEntity.notFound().build();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student)
    {
        Student updated = service.updateStudent(id, student);
        if (updated != null)
            return ResponseEntity.ok(updated);
        else
            return ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id)
    {
        String result = service.deleteStudent(id);
        if (result.equals("Deleted"))
            return ResponseEntity.ok("Student deleted successfully");
        else
            return ResponseEntity.notFound().build();
    }

    // SEARCH
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudent(
            @RequestParam String name, 
            @RequestParam String course) 
    {
        return ResponseEntity.ok(service.searchStudent(name, course));
    }
}
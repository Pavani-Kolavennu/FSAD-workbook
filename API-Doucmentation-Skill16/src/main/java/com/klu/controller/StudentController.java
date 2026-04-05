package com.klu.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.klu.model.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.klu.service.StudentService;
@Tag(name = "Student API", description = "CRUD operations for Student Management")
@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService service;
    @Operation(summary = "Add a new student")
    @ApiResponse(responseCode = "200", description = "Student created successfully")
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(service.createStudent(student));
    }
    @Operation(summary = "Get all students")
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(service.getAllStudents());
    }
    @Operation(summary = "Get student by ID")
    @ApiResponse(responseCode = "404", description = "Student not found")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id)  {
        Student student = service.getStudentById(id);
        if (student != null)
            return ResponseEntity.ok(student);
        else
            return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Update student details")
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student updated = service.updateStudent(id, student);
        if (updated != null)
            return ResponseEntity.ok(updated);
        else
            return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Delete a student")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        String result = service.deleteStudent(id);
        if (result.equals("Deleted"))
            return ResponseEntity.ok("Student deleted successfully");
        else
            return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Search students by name and course")
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudent(@RequestParam String name, @RequestParam String course){
        return ResponseEntity.ok(service.searchStudent(name, course));
    }
}
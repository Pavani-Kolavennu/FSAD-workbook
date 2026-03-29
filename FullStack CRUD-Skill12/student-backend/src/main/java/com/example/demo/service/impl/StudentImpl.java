package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.StudentService;

@Service
public class StudentImpl implements StudentService 
{
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student createStudent(Student student) 
    {
        return studentRepo.save(student);
    }

    @Override
    public Student getStudentById(int id) 
    {
        return studentRepo.findById(id).orElse(null);
    }

    @Override
    public List<Student> getAllStudents() 
    {
        return studentRepo.findAll();
    }

    @Override
    public Student updateStudent(int id, Student student) 
    {
        if (studentRepo.existsById(id)) 
        {
            student.setId(id);
            return studentRepo.save(student);
        }
        return null;
    }

    @Override
    public String deleteStudent(int id) 
    {
        if (studentRepo.existsById(id)) 
        {
            studentRepo.deleteById(id);
            return "Deleted";
        }
        return "Not Found";
    }

    @Override
    public List<Student> searchStudent(String name, String course) 
    {
        return studentRepo.findByNameIgnoreCaseAndCourseIgnoreCase(name, course);
    }
}
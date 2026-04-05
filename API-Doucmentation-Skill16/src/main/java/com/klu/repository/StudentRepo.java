package com.klu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> 
{
    // Custom search method
    List<Student> findByNameIgnoreCaseAndCourseIgnoreCase(String name, String course);
}

package com.example.testapp.repository;

import java.util.List;

import com.example.testapp.model.Course;
import com.example.testapp.model.Professor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMasterRepository extends JpaRepository<Course, Integer> {
    List<Course> findByProfessor(Professor professor);
}

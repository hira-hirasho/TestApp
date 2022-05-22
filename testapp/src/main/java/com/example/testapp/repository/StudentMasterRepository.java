package com.example.testapp.repository;

import com.example.testapp.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMasterRepository extends JpaRepository<Student, Integer> {
    Student findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);
}

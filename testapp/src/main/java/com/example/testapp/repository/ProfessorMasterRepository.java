package com.example.testapp.repository;

import com.example.testapp.model.Professor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorMasterRepository extends JpaRepository<Professor, Integer> {
    Professor findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);
}
